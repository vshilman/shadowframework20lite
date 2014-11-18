import bpy
import bmesh

# Managing import the blender way. Dirty hack to get access to files in the same dir.
import os, sys
blend_dir = os.path.dirname(bpy.data.filepath)
if blend_dir not in sys.path:
    sys.path.append(blend_dir)

# Initialize blender varialbles
context = bpy.context
scene = context.scene
objects = scene.objects

def is_singular(v):
    '''Returns whether a vertex is singular: has three connections'''
    return len(list(v.link_edges)) == 3

def contains_one(s1, s2):
    '''Retuns true if at least one element of s1 is in s2'''
    return any(elem in s2 for elem in s1)

def follow_edge_ind(bmesh, v_index, e_index, goals):
    '''Start from verts and follow the edge until a another is found'''
    edge = bmesh.edges[e_index]
    connected_faces = list(edge.link_faces)
    print(connected_faces)

def edges_get_faces(e):
    faces = e.link_faces
    return [f.index for f in faces]

def vert_get_neighbors(v):
    edges = v.link_edges
    neighbors = [e.other_vert(v) for e in edges]
    return [v.index for v in neighbors]

#def follow_edge(vert, edge, goals, prev_faces = []):
    #if vert.index in goals:
        #return [vert]
    
    #prev_faces += [f.index for f in edge.link_faces]
    #next_vert = edge.other_vert(vert)
    
    #is_next = lambda x: not contains_one([f.index for f in x.link_faces], prev_faces)
    #next_edges = list(filter(is_next, list(next_vert.link_edges)))
    
    #assert len(next_edges) <= 1, "There has been an error. There are two possible directions."
    #return [vert] + follow_edge(next_vert, next_edges[0], goals, prev_faces)

def unpack_list1(l):
    '''Unpack the list if it only consists of 1 element.'''
    return l[0] if len(l) == 1 else l

def explore_vert(vert, goals, prev_faces=[]):
    if vert.index in goals:
        return [vert.index]
    result = []
    for edge in vert.link_edges:
        face_indexes = [f.index for f in edge.link_faces]
        if not contains_one(face_indexes, prev_faces):
            result += [[vert.index] + explore_vert(edge.other_vert(vert), goals, prev_faces + face_indexes)]
    return unpack_list1(result)

def discard_invalid(paths, goals):
    def is_valid(path):
        return path[0] in goals and path[-1] in goals
    return [p for p in paths if is_valid(p)]

#def explore(v_index, bmesh, goals, prev_verts=[], path=[]):
    #'''Explore the mesh until the goal has been found.'''
    ## Base case
    
    ##Recursive call    
    #for edge in list(v.link_edges):
        #dest = filter(lambda x: x.index , edge.verts)

for obj in objects:
    try:
        mesh = obj.to_mesh(scene, True, 'PREVIEW', calc_tessface=True)
    except RuntimeError:
        print("This mesh doesn't have a geometry!")
        continue
    
    # Convert to bm mesh
    bm = bmesh.new()
    bm.from_mesh(mesh)
        
    verts_list = list(bm.verts)
    faces_list = list(bm.faces)
    
    # Compute the singular vertices
    singular_verts = list(filter(is_singular, verts_list))
    singular_verts_indexes = [s.index for s in singular_verts]
    
    goals = singular_verts_indexes
    
    v = singular_verts[0]
    e = v.link_edges[0]
    
    print("Analyzing v", v.index)
    print("Edge      e", e.index)
    print("Neighbors v", vert_get_neighbors(v))
    print("Destinations", goals)
    
    goals.remove(v.index)
    
    paths_from_v = explore_vert(v, goals)
    print(paths_from_v)
    paths_from_v = discard_invalid(paths_from_v, goals)
    
    
    # Find the edges of the patches composing it.
    #for vert in singular_verts:
        #goals.remove(vert.index)
        #print(follow_edge(vert, )


    # We need to build some loops over them
    #for v in singular_verts:
        #loops = list(v.link_edges)
        #print(loops)
    
    #print(faces_list)