import utils

def is_singular(v):
    '''Returns whether a vertex is singular: has three connections.
    TODO This implementation probably has to take into account more variables (like boundaries conditions).'''
    return len(list(v.link_edges)) == 3

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
    
    for vi in singular_verts_indexes:
        goals = list(singular_verts_indexes)
        goals.remove(vi)
        edges = explore_vert(singular_verts[vi], goals)
        for edge in edges:
            result.append(tuple(edge))
    
    # Remove the invalid edges (the duplicate ones).
    final_result = set([])
    for e in result:
        if e not in final_result and e[::-1] not in final_result:
            final_result.add(e)
    macro_edges = list(final_result)
    
    return final_result

def connected_component_index(vertexi, boundaries, mesh):
    '''Returns all the connected component: the ones that never cross boundaries.'''
    frontier = [vertexi]
    explored = set()
    
    while len(frontier) > 0:
        vi = frontier.pop(0)
        if vi in explored:
            continue
        if vi in boundaries: 
            explored.add(vi)
            continue
        explored.add(vi)
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

def __reorder_edges(l, item):
    if l == []:
        return l
    for elem in l:
        if elem[0] == item[-1]:
            return [elem] + __reorder_edges(utils.remove_from_list(l, elem), elem)
        elif elem[-1] == item[-1]:
            return [elem[::-1]] + __reorder_edges(utils.remove_from_list(l, elem), elem[::-1])

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

def split_patch(patch_edges, patch_verts, mesh):
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

#def split_patch_4(patch_edges, patch_verts, mesh):
    #halves = split_patch(patch_edges, patch_verts, mesh)
    #quarters1 = split_patch(halves[0])