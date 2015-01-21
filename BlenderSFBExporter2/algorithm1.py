import utils
import errors
import blender
import mymath as mmath
import geometry as geom
import math
import itertools
import fitting as fit

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

def explore_vert(vert, goals, prev_faces=[], avoid=(lambda x: False)):
    return _explore_vert(vert, goals, prev_faces, avoid=(lambda x: False))

def _explore_vert(vert, goals, prev_faces=[], avoid=(lambda x: False), prev_verts=set([])):
    if avoid(vert.index) or vert.index in prev_verts:
        return []
    if vert.index in goals:
        return [[vert.index]]
    subcalls = []
    for edge in vert.link_edges:
        face_indexes = [f.index for f in edge.link_faces]
        if not utils.contains_one(face_indexes, prev_faces):
            new_prev_verts = prev_verts | set([vert.index])
            subcalls += _explore_vert(edge.other_vert(vert), goals, prev_faces + face_indexes, avoid, new_prev_verts)
    
    result = []
    for path in subcalls:
        result += [[vert.index] + path]
    return result


def compute_macro_edges(singular_verts):
    result = []
    singular_verts_indexes = [v.index for v in singular_verts]
    
    for i, vi in enumerate(singular_verts_indexes):
        goals = set(singular_verts_indexes) - set([vi])
        edges = explore_vert(singular_verts[i], goals)
        result.extend((tuple(edge) for edge in edges))
    
    # Remove the invalid edges (the duplicate ones).
    final_result = set([])
    is_valid = lambda e: e not in final_result and e[::-1] not in final_result and e[0] in singular_verts_indexes and e[-1] in singular_verts_indexes
    for e in result:
        print(e)
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
    result = [None] * len(partitions)
    for i, patch in enumerate(patches):
        for part in partitions:
            if all(set(edge) & set(part) for edge in patch):
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
            if any((pp in edge) for pp in partition):
                current_edges.append(edge)
        patches.append(current_edges)
    return patches

def reorder_patch_edges(l):
    '''Reorder the edges, so that two adjacent edges, will have a shared vertex.
        Returns [] if this is not possible.'''
    assert len(l) > 1, "List has to be non empty."
    result = [l[0]] + __reorder_edges(l[1:], l[0])
    if len(result) != len(l) or result[0][0] != result[-1][-1]:
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
    patch_func_points = list(geom.sample_patch_samples(patch_func, math.sqrt(10 * len(patch_verts_attribution)) // 1))
    patch_verts = [blender.convert_vert(bm.verts[vi]) for vi in patch_verts_attribution]
    return errors.verts_sets_sq_error(patch_func_points, patch_verts)

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
    '''Remove the edges which are not meaningful, such as loops. 
    Atm this only checks for loops.'''
    return [e for e macro_edges if e[0] != e[-1]]

def split_intersecting(macro_edges):
    '''Split the macroedges which are intersecting.'''
    frontier = set(macro_edges)
    result = set()
    
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
    macro_edges = split_intersecting(macro_edges)
    #macro_edges = remove_intersections(macro_edges, singular_verts_indexes)
    
    for p in macro_edges:
        pr("MACRO_EDGES", p)
    
    boundaries = set(sum(macro_edges, ()))
    patch_verts_attribution = partition_mesh_vertices(verts_indexes, boundaries, bm)
    
    for p in patch_verts_attribution:
        pr("INNER_POINTS",p)
    
    patches = compute_patch_edges(patch_verts_attribution, macro_edges)
    
    for p in patches:
        pr("PATCH", p)
    
    ordered_patches = []
    for i, part in enumerate(patches):
        ordered_patch = reorder_patch_edges(part)
        if ordered_patch:
            ordered_patches.append(ordered_patch)
    patches = ordered_patches
    
    # TODO Current we simply get rid of all the non quadrangular patches.
    patches = [p for p in patches if len(p) == 4]
    
    return patches, macro_edges, singular_verts
    

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
    
    patches = [p for p in patches if len(p) == 4]
    
    #return patches
    
    THRESHOLD = 10.5
    MIN_VERTS = 10
    
    def can_simplify(patch_verts):
        '''Returns wheter the patch contains enough point to be simplified.'''
        return patch_verts and len(patch_verts) > MIN_VERTS
    
    # TODO Disable this
    # Returns all the pathces without improvements
    #return [geom.Vertex((v.co.x, v.co.y, v.co.z)) for v in verts_list], patches
    
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
        
        if can_simplify(current_attr) and compute_patch_error(current_patch, bm, current_attr) > THRESHOLD:
            #pr("ERROR", compute_patch_error(current_patch, bm, current_attr))
            new_patches = split_patch_4(current_patch, current_attr, bm)
            require_improvement += new_patches
            boundaries = set(sum(sum(result + require_improvement, []),()))
            partitions = partition_mesh_vertices(verts_indexes, boundaries, bm)
            temp = compute_patch_verts_attribution(partitions, result + require_improvement)
            require_improvement_verts = temp[len(result):] #We need to exclude the current correct results.
        else:
            result += [current_patch]
    
    #We need to fit the patch edges with lower degree curves
    edges = set(sum(result, []))
    edge_correspondence = {}
    old_verts = [geom.Vertex((v.co.x, v.co.y, v.co.z)) for v in verts_list]
    
    # Returns the patches without the edge fitting.
    #return old_verts, result    
    
    # Output values
    new_verts = []
    new_edges = []
    new_patches = []
    
    SAMPLES = 20
    THRESHOLD_DISTANCE = 0.001
    
    edge_conversion = {}
    
    for edge in edges:
        if edge_conversion.get(edge):
            pass
        elif edge_conversion.get(edge[::-1]):
            edge_conversion[edge] = edge_conversion.get(edge[::-1])[::-1]
        else:
            cpoints = tuple([old_verts[i] for i in edge])
            curve = geom.generate_spline(cpoints, mmath.interp_bezier_curve_2)
            curve_points = list(geom.sample_curve_samples(curve, 20))
            
            # TODO Check precision
            cpoints_chunks = fit.fit_bezier_spline(curve_points, mmath.interp_bezier_curve_2, 1)
            approximated_cpoints = [geom.Vertex(chunk) for chunk in cpoints_chunks[0]]
            
            # We need to use the original extremes control points to ensure continuity
            new_cpoints = [cpoints[0]] + approximated_cpoints[1:-1] + [cpoints[-1]]
            
            new_verts += new_cpoints
            new_verts_indexes = tuple([new_verts.index(v) for v in new_cpoints])
            edge_conversion[edge] = new_verts_indexes
    
    print("Old verts", len(verts_list))
    print("New verts", len(new_verts))
    
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
    
    print("Final verts", len(final_verts))
    return final_verts, final_patches
