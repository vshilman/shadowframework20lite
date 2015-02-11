import utils
import errors
import blender
import mymath as mmath
import geometry as geom
import math
import itertools
import fitting as fit
import funchelps


import collections

VERBOSE = False

def pr(*s):
    if VERBOSE:
        print(*s)

def is_singular(v):
    '''Returns whether a vertex is singular: has three connections.
    TODO This implementation probably has to take into account more variables (like boundaries conditions).'''
    return len(list(v.link_faces)) != 4

def find_singular_vertices(verts):
    '''Extract vertex that should be corners of the macro patches.'''
    return list(filter(is_singular, verts))

def __unpack_list1(l):
    '''Unpack the list if it only consists of 1 element.'''
    return l[0] if len(l) == 1 else l

def compute_angle_blender(bA, bB, bC):
    '''Same as compute_angle but with blender edges.'''
    points = tuple([blender.convert_vert(v) for v in [bA, bB, bC]])
    return compute_angle(*points)

def compute_angle(A, B, C):
    '''Computer the angle between the vertices of the edges (A, B) and (A, C)'''
    AB, AC = B - A, C - A
    cos_angle = funchelps.dot(AB, AC) / (funchelps.module(AB) * funchelps.module(AC))
    return math.acos(cos_angle)

def split_list_triples(l):
    '''Returns contiguous couple of list.'''
    if len(l) < 3: return []
    return [l[0:3]] + split_list_triples(l[1:])

def split_circular_list_triples(l):
    '''Like split_list_triples but considering it circular'''
    return split_list_triples(l + l[0:2])

def is_right_angle(angle, threshold=0.3):
    '''Returns true if angle is a right angle. A percentual threshold can be specified.'''
    return abs(angle - math.pi * 0.5) < math.pi * 0.5 * threshold

def is_edge_flat(edge, threshold=0.15):
    '''Returns if the angle of the faces which share an edge is really low'''
    return abs(edge.calc_face_angle()) < math.pi * 0.5 * threshold

def are_edges_coplanar(c, e1, e2, e3, threshold=0.1):
    cross = lambda x,y: geom.Vertex(funchelps.cross(x,y))
    gc = blender.convert_vert(c)
    ge1 = blender.convert_vert(e1)
    ge2 = blender.convert_vert(e2)
    ge3 = blender.convert_vert(e3)
    return abs(funchelps.dot(cross(ge1 - gc, ge2 - gc), ge3 - gc)) < threshold

def is_edge_right_angle(c, e1, e2, e3):
    '''Retruns true if e2 is almost right angle with both e1 and e3. Edges are tuples of indexes.'''
    
    angle1 = compute_angle_blender(c, e1, e2)
    angle2 = compute_angle_blender(c, e2, e3)
    
    if c.index == 5662:
        print(angle1, angle2)
    
    return is_right_angle(angle1) and is_right_angle(angle2)


def explore_vert(vert, goals, prev_faces=[], avoid=(lambda x: False)):
    return _explore_vert(vert, goals, prev_faces, avoid=(lambda x: False), first_call=True)

def _explore_vert(vert, goals, prev_faces=[], avoid=(lambda x: False), prev_verts=set([]), first_call=False):
    if not first_call and vert.index in goals:
        return [[vert.index]]
    if avoid(vert.index) or vert.index in prev_verts:
    #if avoid(vert.index):
        return []
    
    subcalls = []
    edges = list(vert.link_edges)
    
    for i, edge in enumerate(edges):
        face_indexes = [f.index for f in edge.link_faces]
        if not utils.contains_one(face_indexes, prev_faces):
            new_prev_verts = prev_verts | set([vert.index])
            subcalls += _explore_vert(edge.other_vert(vert), goals, face_indexes, avoid, new_prev_verts)
    
    result = []
    for path in subcalls:
        result += [[vert.index] + path]
    return result


def compute_macro_edges(singular_verts):
    result = []
    singular_verts_indexes = [v.index for v in singular_verts]
    
    for i, vi in enumerate(singular_verts_indexes):
        goals = set(singular_verts_indexes)
        edges = explore_vert(singular_verts[i], goals)
        result.extend((tuple(edge) for edge in edges))
    
    # Remove the invalid edges (the duplicate ones).
    final_result = set([])
    is_valid = lambda e: e and e not in final_result and e[::-1] not in final_result and e[0] in singular_verts_indexes and e[-1] in singular_verts_indexes
    for e in result:
        try:
            if is_valid(e):
                final_result.add(e)
        except:
            pass
    
    return final_result

def connected_component_index(vertexi, boundaries, mesh):
    '''Returns all the connected component: the ones that never cross boundaries.'''
    frontier = [vertexi]
    explored = set()
    
    while len(frontier) > 0:
        vi = frontier.pop(0)
        if vi in explored:
            continue
        explored.add(vi)
        if vi in boundaries:
            continue
        v = mesh.verts[vi]
        frontier += [e.other_vert(v).index for e in v.link_edges]
    
    return explored

def partition_mesh_vertices(verts, boundaries, mesh):
    '''Partition the mesh verts into sets that never cross the given borders.
    The corners of the patch will not belong.'''
    inner_verts = list(set(verts) - set(boundaries))
    result = []
    while len(inner_verts) > 0:
        vi = inner_verts.pop(0)
        partition = connected_component_index(vi, boundaries, mesh)
        for e in partition:
            if e in inner_verts:
                inner_verts.remove(e)
        result.append(frozenset(partition))
    return result

def compute_patch_verts_attribution(partitions, patches):
    '''Understand which verts below to which patch.'''
    
    def is_valid_patch(patch):
        return all(len(edge) > 2 for edge in patch)
    
    def is_edge_included(edge, partition):
        inner_edge = set(edge[1:-1])
        return inner_edge.issubset(partition)
    
    result = [None] * len(patches)
    for i, patch in enumerate(patches):
        for part in partitions:
            if is_valid_patch(patch) and all(is_edge_included(edge, part) for edge in patch):
                result[i] = part
    return result

def __reorder_edges(l, item):
    if not l:
        return []
    for elem in l:
        if elem[0] == item[-1]:
            return [elem] + __reorder_edges(utils.remove_from_list(l, elem), elem)
        elif elem[-1] == item[-1]:
            return [elem[::-1]] + __reorder_edges(utils.remove_from_list(l, elem), elem[::-1])
    return []

def compute_patch_edges(faces_verts, all_edges):
    '''Compute which edges belong to which face'''
    patches = []
    for partition in faces_verts:
        current_edges = []
        for edge in all_edges:
            if any((pp in partition) for pp in edge[1:-1]): # Do not check the corners
                current_edges.append(edge)
        patches.append(current_edges)
    return patches

def reorder_patch_edges(l):
    '''Reorder the edges, so that two adjacent edges, will have a shared vertex.
        Returns [] if this is not possible.'''
    if len(l) <= 1:
        return l
    result = [l[0]] + __reorder_edges(l[1:], l[0])
    if len(result) != len(l) or result[0][0] != result[-1][-1]:
        print(l)
        print("Warning: reorder_edges couldn't find the proper order")
        return []
    return result


def rotate_patch(patch):
    '''Rotate the edges of the patch. This might be used to split the patch into the other directions.'''
    return [patch[-1]] + patch[:3]

def split_patch_2(patch_edges, patch_verts, mesh):
    '''Split the current patch between the first and the third edge.'''
    assert len(patch_edges) == 4, "We only deal with rectangular patch"
    e1, e2, e3, e4 = patch_edges
    v1 = mesh.verts[(e1[len(e1) // 2])]
    
    new_edges = explore_vert(v1, e2, avoid=lambda x: x not in patch_verts)
    new_edge = list(filter(lambda x: x[0] in e1 and x[-1] in e3, new_edges))[0]
    new_edge = tuple(new_edge)
            
    vi_0 = new_edge[0]
    vi_F = new_edge[-1]
    
    patch1 = [e1[e1.index(vi_0):], e2, e3[:e3.index(vi_F)+1], new_edge[::-1]]
    patch2 = [new_edge, e3[e3.index(vi_F):], e4, e1[:e1.index(vi_0)+1]]
    
    return [patch1, patch2]

# TODO There is probalby a way to refactor this.
def split_patch_4(patch_edges, patch_verts, mesh):
    '''Split the current patch between the first and the third edge.'''
    assert len(patch_edges) == 4, "We only deal with rectangular patch"
    e1, e2, e3, e4 = patch_edges
    
    v1 = mesh.verts[(e1[len(e1) // 2])]
    v2 = mesh.verts[(e2[len(e2) // 2])]
    
    new_edges1 = explore_vert(v1, e3, avoid=lambda x:x not in patch_verts)
    new_edge1 = sorted(list(filter(lambda x: x[0] in e1 and x[-1] in e3, new_edges1)), key=len)[0]
    new_edge1 = tuple(new_edge1)
        
    vi_0 = new_edge1[0]
    vi_F = new_edge1[-1]
    
    new_edges2 = explore_vert(v2, e4, avoid=lambda x: x not in patch_verts)
    new_edge2 = sorted(list(filter(lambda x: x[0] in e2 and x[-1] in e4, new_edges2)), key=len)[0]
    new_edge2 = tuple(new_edge2)
    
    vj_0 = new_edge2[0]
    vj_F = new_edge2[-1]
    
    vm = list(set(new_edge1) & set(new_edge2))[0]
    
    patch1 = [e1[e1.index(vi_0):], e2[:e2.index(vj_0)+1], new_edge2[:new_edge2.index(vm)+1], new_edge1[:new_edge1.index(vm)+1][::-1]]
    patch2 = [new_edge2[:new_edge2.index(vm)+1][::-1], e2[e2.index(vj_0):], e3[:e3.index(vi_F)+1], new_edge1[new_edge1.index(vm):new_edge1.index(vi_F)+1][::-1]]
    patch3 = [e1[:e1.index(vi_0)+1], new_edge1[:new_edge1.index(vm)+1], new_edge2[new_edge2.index(vm):], e4[e4.index(vj_F):]]
    patch4 = [new_edge2[new_edge2.index(vm):][::-1], new_edge1[new_edge1.index(vm):], e3[e3.index(vi_F):], e4[:e4.index(vj_F)+1]]
    
    return [patch1, patch2, patch3, patch4]


def create_function_from_patch(mesh, patch): #TODO This should be customized
    curves = []
    for edge in patch:
        verts = (tuple((blender.convert_vert(mesh.verts[i]) for i in edge)))
        curves.append(geom.generate_spline(verts, mmath.interp_bezier_curve_2))
    return geom.PolygonsNetQuad(curves)
    return curves

def compute_patch_error(patch, bm, patch_verts_attribution):
    patch_func = create_function_from_patch(bm, patch)
    #patch_func_points = list(geom.sample_patch_samples(patch_func, math.sqrt(10 * len(patch_verts_attribution)) // 1))
    patch_func_points = list(geom.sample_patch_samples_uv(patch_func, len(patch[0]), len(patch[1])))
    patch_verts = [blender.convert_vert(bm.verts[vi]) for vi in patch_verts_attribution]
    return errors.verts_max_error(patch_func_points, patch_verts)

def compute_error(patches, bm, patch_verts_attribution):
    result = []
    verts_list = list(bm.verts)
    
    for i, patch in enumerate(patches):
        result.append(compute_patch_error(patch, bm, patch_verts_attribution[i]))
    
    return sum(result)

import random

def intersect_point(edge1, edge2):
    shared_points = set(edge1[1:-1]) & set(edge2[1:-1])
    return shared_points.pop() if len(shared_points) > 0 else None

def check_and_split(edge1, edge2):
    '''If two edges share a vertex (not counting the extremes) split them in four.'''
    p = intersect_point(edge1, edge2)
    if p:
        return [edge1[:edge1.index(p)+1], edge1[edge1.index(p):], edge2[:edge2.index(p)+1], edge2[edge2.index(p):]]
    return [edge1, edge2]

def filter_macro_edges(macro_edges):
    '''It happens that a singular vertex has only one edge connected to it. This has to be removed 
    Atm this only checks for loops.'''
    singular_verts = set(sum(macro_edges,()))
    singular_verts_counts = collections.defaultdict(lambda: 0)
    for edge in macro_edges:
        singular_verts_counts[edge[0]] += 1
        singular_verts_counts[edge[-1]] += 1
    
    remove_verts = []
    for key, val in singular_verts_counts.items():
        if val == 1:
            remove_verts.append(key)
    
    result = []
    for edge in macro_edges:
        if edge[0] not in remove_verts and edge[-1] not in remove_verts:
            result.append(edge)
        else:
            print("Removing spurious edge.", edge)
    
    return result

def compute_valence(edge, verts_list):
    '''Compute the valence of the current edge'''
    first_valence = len(verts_list[edge[0]].link_edges)
    last_valence = len(verts_list[edge[-1]].link_edges)
    return max(first_valence, last_valence)

def is_strong_edge(edge, verts_list):
    return compute_valence(edge, verts_list) == 3

def is_weak_edge(edge, verts_list):
    return not is_strong_edge(edge, verts_list)

def add_all_discard_intersections(edges, result=set()):
    frontier = set(edges)
    
    while len(frontier) > 0:
        current = frontier.pop(0)
        intersection = False
        for edge in set(result):
            if intersect_point(current, edge):
                intersection = True
                break
        
        if intersection:
            continue
        
        result.add(current)
    return result

def add_all_split_intersections(edges, result=set([])):
    frontier = set(edges)
    while len(frontier) > 0:
        current = frontier.pop()

        intersection = False
        for edge in set(frontier):
            if intersect_point(current, edge):
                frontier -= set([edge])
                frontier |= set(check_and_split(edge, current))
                intersection = True
                break
        
        if not intersection:
            result.add(current)
    return result

def split_intersecting(macro_edges, verts_list):
    '''Split the macro-edges which are intersecting.'''
    result = set()
    
    edge_valence = lambda x: compute_valence(x, verts_list)
    strong = lambda x: is_strong_edge(x, verts_list)
    weak = lambda x: is_weak_edge(x, verts_list)
    
    strong_edges = list(filter(strong, macro_edges))
    weak_edges = list(filter(weak, macro_edges))
    
    # Add all the strong edges. Splitting them if necessary.
    add_all_split_intersections(strong_edges, result)
    
    # Discard all the weak edges which intersect strong edges
    #for wedge in weak_edges:
        #intersections_points = [intersect_point(wedge, sedge) for sedge in strong_edges]
        #intersections_points = [p for p in intersections_points if p]
        #if len(intersections_points) == 0:
            #result.add(wedge)
    
    # Take only the first part of the weak edges
    for wedge in weak_edges:
        intersections_points = [intersect_point(wedge,sedge) for sedge in strong_edges]
        intersections_points = [p for p in intersections_points if p]
        intersections_indexes = [wedge.index(point) for point in intersections_points]
        if intersections_indexes:
            min_index, max_index = min(intersections_indexes), max(intersections_indexes)
            result.add(wedge[:min_index+1])
            result.add(wedge[max_index:])
            
            added = set()
            removed = set()
            
            for edge in result:
                if wedge[min_index] in edge[1:-1]:
                    removed.add(edge)
                    added.add(edge[:edge.index(wedge[min_index])+1])
                    added.add(edge[edge.index(wedge[min_index]):])
                
                if wedge[max_index] in edge[1:-1]:
                    removed.add(edge)
                    added.add(edge[:edge.index(wedge[max_index])+1])
                    added.add(edge[edge.index(wedge[max_index]):])
            
            for edge in removed:
                result.remove(edge)
            for edge in added:
                result.add(edge)
            
        else:
            result.add(wedge)

    result = add_all_split_intersections(result)
    return result

def remove_intersections(macro_edges, singular_verts_indexes):
    '''Remove the intersected vertices'''
    new_edges = sorted(macro_edges, key=len)
    result = []
    for e in new_edges:
        inner_verts = set(sum(result, ())) - set(singular_verts_indexes)
        if len(set(e) & inner_verts) == 0:
            result.append(e)
    return result

import sys

def compute_edges_angle(e1, e2, verts_list):
    '''Compute the angle between the two edges.'''
    assert e1[-1] == e2[0], "No correspondences among margins"
    vm = verts_list[e1[-1]]
    vp = verts_list[e1[-2]]
    vn = verts_list[e2[1]]
    
    return compute_angle_blender(vm, vp, vn)

def are_mergeable(e1, e2, verts_list, threshold=0.3):
    '''Returns if the two edges are mergeable'''
    return abs(compute_edges_angle(e1, e2, verts_list) - math.pi) < threshold * math.pi

def merge_edges(e1, e2):
    '''Merge the two edges'''
    assert e1[-1] == e2[0], "No correspondences among margins"
    return e1 + e2[1:]

def extract_pairs(edges):
    '''Extact the couples from a list'''
    if len(edges) < 2:
        return []
    return [edges[0:2]] + extract_pairs(edges[1:])

def extract_pairs_circular(edges):
    '''Like extract_pairs but also take into accounts that the last is connected to the first.'''
    if not edges:
        return []
    return extract_pairs(edges + [edges[0]])

def first(f,l):
    '''Returns the first item of l which satisfiers f'''
    for e in l:
        if f(e):
            return e
    return None

def transform_patch_4(p, verts_list):
    '''Transform a patch to a four sided one by merging edges'''
    if len(p) == 4:
        return p
    
    patch = list(p)
    
    pairs = extract_pairs_circular(p)
    cond = lambda x: are_mergeable(x[0], x[1], verts_list)
    key_func = lambda x: abs(compute_edges_angle(x[0], x[1], verts_list) - math.pi)
    
    edges = sorted(pairs, key=key_func)[0]
    
    e1, e2 = edges
    
    if e1 == e2:
        return None
    
    patch.remove(e1)
    patch[patch.index(e2)] = merge_edges(e1, e2)
    return transform_patch_4(patch, verts_list)

def quadrangulate_patches(patches, verts_list):
    '''Returns the quadrangular representation of patches'''
    result = []
    for p in patches:
        temp = transform_patch_4(p, verts_list)
        if temp:
            result.append(temp)
    return result

def extract_base_mesh(bm):
    '''Compute the skeleton patches. To be refined in following steps.
    Retuns the singular points, the edges and the patches.'''
    verts_list = list(bm.verts)
    faces_list = list(bm.faces)
    verts_indexes = [v.index for v in verts_list]
    
    singular_verts = find_singular_vertices(verts_list)
    singular_verts_indexes = [v.index for v in singular_verts]
    
    for p in singular_verts_indexes:
        pr("SINGULAR_VERTS", p)
    
    macro_edges = compute_macro_edges(singular_verts)
    macro_edges = filter_macro_edges(macro_edges)
    macro_edges = split_intersecting(macro_edges, verts_list)
    #macro_edges = remove_intersections(macro_edges, singular_verts_indexes)
    
    for p in macro_edges:
        pr("MACRO_EDGES", p)
    
    boundaries = set(sum(macro_edges, ()))
    patch_verts_attribution = partition_mesh_vertices(verts_indexes, boundaries, bm)
    
    for p in patch_verts_attribution:
        pr("INNER_POINTS",p)
    
    patches = compute_patch_edges(patch_verts_attribution, macro_edges)    
    patches = [p for p in patches if len(p) >= 4]

    for p in patches:
        pr("PATCH", p)
    
    ordered_patches = []
    for i, part in enumerate(patches):
        ordered_patch = reorder_patch_edges(part)
        if ordered_patch:
            ordered_patches.append(ordered_patch)
    patches = quadrangulate_patches(ordered_patches, verts_list)
    
    return patches, macro_edges, singular_verts
    
def size_estimate(bm):
    '''Returns an estimate of the mesh size. '''
    verts_list = [blender.convert_vert(v) for v in bm.verts]
    
    def axis_length(index, vs):
        axis = [v[index] for v in vs]
        return max(axis) - min(axis)
    
    return max(axis_length(0, verts_list), axis_length(1, verts_list), axis_length(2, verts_list))

VERTEX_BYTE_SIZE = 12
INDEX_BYTE_SIZE = 2

def compute_input_mesh_size(bm):
    '''Compute the byte size of the input mesh.'''
    verts_list = list(bm.verts)
    faces_list = list(bm.faces)
    
    verts_size = VERTEX_BYTE_SIZE * len(verts_list)
    faces_size = INDEX_BYTE_SIZE * 3 * len(faces_list)
    
    return verts_size + faces_size

def compute_output_mesh_size(patches):
    '''Compute the byte size of the output mesh.'''
    edges = sum(patches, [])
    verts = set(sum(edges, ()))
    
    result = len(verts) * VERTEX_BYTE_SIZE
    
    for edge in edges:
        result += len(edge) * INDEX_BYTE_SIZE
    
    for patch in patches:
        result += len(patch) * INDEX_BYTE_SIZE
    
    return result

def run(bm):
    '''Run the algorithm on the given bmesh (blender mesh), 
    returns the patches (TODO in a data structure we still need to decide)'''
    verts_list = list(bm.verts)
    faces_list = list(bm.faces)
    verts_indexes = [v.index for v in verts_list]
    
    # Extract the skeleton mesh.
    patches, macro_edges, singular_verts = extract_base_mesh(bm)

    boundaries = set(sum(macro_edges, ()))

    # Now we need to understand which vertex belong to which face. We use the connected components approach.
    patch_verts_attribution = partition_mesh_vertices(verts_indexes, boundaries, bm)

    # Now we need to understand which superedges belong to which face.
    patches = compute_patch_edges(patch_verts_attribution, macro_edges)
    
    # We now need to reorder the vertices of each face so that we can build a spline on them.
    for i, part in enumerate(patches):
        patches[i] = reorder_patch_edges(part)
    
    # Filter out empty patches
    patches = [p for p in patches if p]
    
    patches = quadrangulate_patches(patches, verts_list)
    
    THRESHOLD = 1.0
    MIN_VERTS = 20
    
    threshold = THRESHOLD * size_estimate(bm)
    print("Using Threshold:", threshold)
    
    
    def can_simplify(patch_verts):
        '''Returns wheter the patch contains enough point to be simplified.'''
        return patch_verts and len(patch_verts) > MIN_VERTS
    
    # Now that we defined the patches we should iterate to improve the error.
    result = []
    require_improvement = list(patches)
    require_improvement_verts = list(patch_verts_attribution)
    while len(require_improvement) > 0:
        current_patch = require_improvement.pop(0)
        current_attr = require_improvement_verts.pop(0)
        
        print(len(require_improvement))
        
        pr("N. PATCHES IN FRONTIER:",len(require_improvement))
        pr("CURRENT PATCH", current_patch)
        pr("CURRENT POINTS", current_attr)
        
        #print(compute_patch_error(current_patch, bm, current_attr))
        
        if can_simplify(current_attr) and compute_patch_error(current_patch, bm, current_attr) > threshold:
            print("Splitting patch")
            #pr("ERROR", compute_patch_error(current_patch, bm, current_attr))
            new_patches = split_patch_4(current_patch, current_attr, bm)
            require_improvement += new_patches
            
            boundaries = set(sum(sum(result + require_improvement, []),()))
            partitions = partition_mesh_vertices(verts_indexes, boundaries, bm)
            
            #print(len(partitions), len(result + require_improvement))
            
            temp = compute_patch_verts_attribution(partitions, result + require_improvement)
            require_improvement_verts = temp[len(result):] #We need to exclude the current correct results.
        else:
            result += [current_patch]
    
    #We need to fit the patch edges with lower degree curves
    edges = set(sum(result, []))
    edges = sorted(edges, key=len)
    
    edge_correspondence = {}
    old_verts = [geom.Vertex((v.co.x, v.co.y, v.co.z)) for v in verts_list]
    
    # TODO Disable this. Returns the patches without the edge fitting.
    #return old_verts, result, old_verts, result
    
    # Output values
    new_verts = []
    new_edges = []
    new_patches = []
    
    SAMPLES = 20
    SPLINE_THRESHOLD = 0.05 * size_estimate(bm)
    THRESHOLD_DISTANCE = 0.005 * size_estimate(bm)
    
    edge_conversion = {}
    
    def edges_permutations(base_edges, ref_edge):
        '''Compute the possible permutations of base_edges which are valid wrt edge.'''
        result = []
        if ref_edge == ():
            return []
        if len(ref_edge) == 1:
            return [[ref_edge]]
        for edge in base_edges:
            if edge == ref_edge[0:len(edge)]:
                subcalls = edges_permutations(base_edges, ref_edge[len(edge)-1:])
                for call in subcalls:
                    result += [[edge] + call]
        
        return result
    
    def merge_all_edges(edges):
        if not edges:
            return []
        result = edges[0]
        for e in edges[1:]:
            result += e[1:]
        return tuple(result)
    
    #def is_composed(edge, prev_edges):
        #perms = itertools.permutations(prev_edges, 3)
        #for comb in filter(is_valid_combination, perms):
            #if edge == merge_all_edges(comb):
                #return merge_all_edges(comb)
        #return None
    
    def is_composed(edge, prev_edges):
        results = edges_permutations(prev_edges, edge)
        results = sorted(results, key=len)
        for res in results:
            if merge_all_edges(res) == edge:
                return res[:-1]
        return None
    
    for i,edge in enumerate(edges):
        print("Compressing edge:", i, "/", len(edges))
        if edge_conversion.get(edge):
            pass
        elif is_composed(edge, sorted(list(edge_conversion.keys()), key=len)):
            print("This is a combination of previous edges")
            edge_chunks = is_composed(edge, sorted(list(edge_conversion.keys()), key=len))
            transformed_edge_chunks = [edge_conversion[e] for e in edge_chunks]
            edge_conversion[edge] = merge_all_edges(transformed_edge_chunks)
            edge_conversion[edge[::-1]] = merge_all_edges(transformed_edge_chunks)[::-1]
        else:
            cpoints = tuple([old_verts[i] for i in edge])
            curve = geom.generate_spline(cpoints, mmath.interp_bezier_curve_2)
            curve_points = list(geom.sample_curve_samples(curve, 20))
            
            # TODO Check precision
            cpoints_chunks = fit.fit_bezier_spline(curve_points, mmath.interp_bezier_curve_2, 1)
            approximated_cpoints = [geom.Vertex(chunk) for chunk in cpoints_chunks[0]]
            
            # We need to use the original extremes control points to ensure continuity
            new_cpoints = [cpoints[0]] + approximated_cpoints[1:-1] + [cpoints[-1]]
            
            # Check the error and fallback to default spline if issues
            new_curve = geom.generate_spline(new_cpoints, mmath.interp_bezier_curve_2)
            new_curve_points = list(geom.sample_curve_samples(new_curve, 20))
            error = errors.simple_max_error(new_curve_points, curve_points)
            if error > SPLINE_THRESHOLD:
                print("Reverting to old verts")
                new_cpoints = cpoints
            
            new_verts += new_cpoints
            new_verts_indexes = tuple([new_verts.index(v) for v in new_cpoints])
            edge_conversion[edge] = new_verts_indexes
            edge_conversion[edge[::-1]] = new_verts_indexes[::-1]
    
    # Use the converted vertices instead of the original ones.
    final_patches = []
    for patch in result:
        final_patches += [[edge_conversion[e] for e in patch]]
    
    # TODO Remember to disable this.
    #return new_verts, final_patches
    
    def generate_convert(threshold_index, prev_index):
        def convert(index):
            if index < threshold_index:
                return index
            elif index == threshold_index:
                return prev_index
            else:
                return index - 1
        return convert
    
    def update_edge(e, convert):
        return tuple([convert(i) for i in e])
    
    def update_patch(p, convert):
        return [update_edge(e, convert) for e in p]
    
    def my_index(l, element):
        """Returns the element in the list, otherwise -1"""
        return l.index(element) if element in l else -1

    def my_index_closest(l, element):
        temp = list(l)
        mod = lambda x: x[0] * x[0] + x[1] * x[1] + x[2] * x[2]
        
        closest = sorted(temp, key=lambda x: mod(x - element))[0]
        
        return l.index(closest) if mod(element - closest) < THRESHOLD_DISTANCE else -1

    def recursive_max(l):
        if isinstance(l, (int, float)):
            return l
        return max((recursive_max(e) for e in l))
    
    # Filter out vertices which are not unique.
    
    final_verts = []
    for i, vert in enumerate(new_verts):
        index = my_index_closest(final_verts, vert) if final_verts else -1
        if index == -1:
            final_verts += [vert]
        else:
            # The element is not in the list. This means that we need to decrement all the indexes and replace i with index.
            convert_func = generate_convert(len(final_verts), index)
            for i, p in enumerate(final_patches):
                final_patches[i] = update_patch(p, convert_func)
    
    input_size = compute_input_mesh_size(bm)
    output_size = compute_output_mesh_size(final_patches)
    
    print("Statistics ------------------------------------------")
    print("Input Vertices:   ", len(verts_list))
    print("Input Faces:      ", len(faces_list))
    print("Output Vertices:  ", len(final_verts))
    print("Output Faces:     ", len(final_patches))
    print("Input Size:       ", input_size)
    print("Output Size:      ", output_size)
    print("Compression Ratio:", (input_size / output_size))
    print("-----------------------------------------------------")
    
    return old_verts, result, final_verts, final_patches
