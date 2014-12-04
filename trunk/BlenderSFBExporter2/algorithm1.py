import utils
import errors
import blender
import mymath as mmath
import geometry as geom
import math
import itertools

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
    if avoid(vert.index):
        return []
    if vert.index in goals:
        return [vert.index]
    result = []
    for edge in vert.link_edges:
        face_indexes = [f.index for f in edge.link_faces]
        if not utils.contains_one(face_indexes, prev_faces):
            result += [[vert.index] + explore_vert(edge.other_vert(vert), goals, prev_faces + face_indexes, avoid)]
    return __unpack_list1(result)

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
        if is_valid(e):
            final_result.add(e)
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
    new_edge1 = list(filter(lambda x: x[0] in e1 and x[-1] in e3, new_edges1))[0]
    new_edge1 = tuple(new_edge1)
            
    vi_0 = new_edge1[0]
    vi_F = new_edge1[-1]
    
    new_edges2 = explore_vert(v2, e4, avoid=lambda x: x not in patch_verts)
    new_edge2 = list(filter(lambda x: x[0] in e2 and x[-1] in e4, new_edges2))[0]
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
    '''Returns a skeleton mesh, made of big quads on the main edges'''
    verts_list = list(bm.verts)
    faces_list = list(bm.faces)
    verts_indexes = [v.index for v in verts_list]
    
    singular_verts = find_singular_vertices(verts_list)
    singular_verts_indexes = [v.index for v in singular_verts]
    
    for p in singular_verts_indexes:
        pr("SINGULAR_VERTS", p)
    
    macro_edges = compute_macro_edges(singular_verts)
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
    
    # We need to filter the patches: only the ones with four are good
    
    #for p in filter(lambda x: len(x) != 4, patches):
    #    print("asdfasdf", p)
    
    #patches = list(filter(lambda x: len(x) == 4, patches))
    
    ordered_patches = []
    for i, part in enumerate(patches):
        ordered_patch = reorder_patch_edges(part)
        if ordered_patch:
            ordered_patches.append(ordered_patch)
    patches = ordered_patches
    
    return patches
    

def run(bm):
    '''Run the algorithm on the given bmesh (blender mesh), 
    returns the patches (TODO in a data structure we still need to decide)'''
    verts_list = list(bm.verts)
    faces_list = list(bm.faces)
    verts_indexes = [v.index for v in verts_list]
    
    # Compute the singular vertices
    singular_verts = find_singular_vertices(verts_list)
    
    # Compute the macro edges: the long edges (which be the borders of the patches).
    macro_edges = compute_macro_edges(singular_verts)

    # Now we need to understand which vertex belong to which face. We use the connected components approach.
    boundaries = set(sum(macro_edges, ()))
    
    patch_verts_attribution = partition_mesh_vertices(verts_indexes, boundaries, bm)

    # Now we need to understand which superedges belong to which face.
    patches = compute_patch_edges(patch_verts_attribution, macro_edges)
    
    # We now need to reorder the vertices of each face so that we can build a spline on them.
    for i, part in enumerate(patches):
        patches[i] = reorder_patch_edges(part)
    
    THRESHOLD = 0.10000
    MIN_VERTS = 5
    
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
        
        pr("N. PATCHES IN FRONTIER:",len(require_improvement))
        pr("CURRENT PATCH", current_patch)
        pr("CURRENT POINTS", current_attr)
        
        if can_simplify(current_attr) and compute_patch_error(current_patch, bm, current_attr) > THRESHOLD:
            pr("ERROR", compute_patch_error(current_patch, bm, current_attr))
            new_patches = split_patch_4(current_patch, current_attr, bm)
            require_improvement += new_patches
            boundaries = set(sum(sum(result + require_improvement, []),()))
            partitions = partition_mesh_vertices(verts_indexes, boundaries, bm)
            temp = compute_patch_verts_attribution(partitions, result + require_improvement)
            require_improvement_verts = temp[len(result):] #We need to exclude the current correct results.
        else:
            result += [current_patch]
    return result