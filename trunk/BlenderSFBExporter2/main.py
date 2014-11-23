import bpy
import bmesh
import functools as func

# Managing import the blender way. Dirty hack to get access to files in the same dir.
import os, sys
blend_dir = os.path.dirname(bpy.data.filepath)
if blend_dir not in sys.path:
    sys.path.append(blend_dir)

# Imports of out modules
import geometry as geom
import mymath as mmath

# Matplotlib imports
import matplotlib as mpl
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
from mpl_toolkits.mplot3d.art3d import Poly3DCollection

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

#def follow_edge_ind(bmesh, v_index, e_index, goals):
    #'''Start from verts and follow the edge until a another is found'''
    #edge = bmesh.edges[e_index]
    #connected_faces = list(edge.link_faces)
    #print(connected_faces)

def edges_get_faces(e):
    faces = e.link_faces
    return [f.index for f in faces]

def vert_get_neighbors(v):
    edges = v.link_edges
    neighbors = [e.other_vert(v) for e in edges]
    return [v.index for v in neighbors]

def unpack_list1(l):
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
        if not contains_one(face_indexes, prev_faces):
            result += [[vert.index] + explore_vert(edge.other_vert(vert), goals, prev_faces + face_indexes, avoid)]
    return unpack_list1(result)

def discard_invalid(paths, goals):
    def is_valid(path):
        return path[0] in goals and path[-1] in goals
    return [p for p in paths if is_valid(p)]

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
    verts_indexes = [v.index for v in verts_list]
        
    # Compute the singular vertices
    singular_verts = list(filter(is_singular, verts_list))
    singular_verts_indexes = [s.index for s in singular_verts]
    
    # Compute the superedges: the long edges which should be the borders of the patches.
    super_edges = []
    
    for vi in singular_verts_indexes:
        goals = list(singular_verts_indexes)
        goals.remove(vi)
        edges = explore_vert(verts_list[vi], goals)
        for edge in edges:
            super_edges.append(tuple(edge))

    # Getting rid of the duplicate edges (counting the ones walked in opposite directions).
    final_super_egdes = set([])
    for e in super_edges:
        if e not in final_super_egdes and e[::-1] not in final_super_egdes:
            final_super_egdes.add(e)
    super_edges = list(final_super_egdes)

    # Now we need to understand which vertex belong to which face. We use the connected components approach.
    boundaries = set(sum(final_super_egdes, ()))
    
    def explore(vertexi, boundaries, mesh):
        '''Returns all the connected component'''
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
        '''Partition the mesh verts into sets that never cross the given borders.'''
        # TODO? Beware the corner of the patches are not included in the patches_verts.
        # Now we need to understand which superedges belong to which face.
        inner_verts = list(set(verts) - set(boundaries))
        result = []
        while len(inner_verts) > 0:
            vi = inner_verts.pop(0)
            partition = explore(vi, boundaries, mesh)
            for e in partition:
                if e in inner_verts:
                    inner_verts.remove(e)
            result.append(frozenset(partition))
        return result
    
    patches_verts = partition_mesh_vertices(verts_indexes, boundaries, bm)
    
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
    
    patches = compute_patch_edges(patches_verts, super_edges)
    
    # We now need to reorder the vertices of each face so that we can build a spline on them.
    def first(seq, cond):
        '''Returns the first element of seq which satisfies cond.'''
        return next(x for x in seq if cond(x))
    
    def remove_from_list(l, elem):
        temp = list(l)
        temp.remove(elem)
        return temp
    
    #TODO This doesn't manage the error case
    def reorder_list(l, item):
        if l == []:
            return l
        for elem in l:
            if elem[0] == item[-1]:
                return [elem] + reorder_list(remove_from_list(l, elem), elem)
            elif elem[-1] == item[-1]:
                return [elem[::-1]] + reorder_list(remove_from_list(l, elem), elem[::-1])
    
    for i, part in enumerate(patches):
        patches[i] = [part[0]] + reorder_list(list(part[1:]), part[0])

    # Lets create and draw the patches
    def convert_vert(bmv):
        return geom.Vertex((bmv.co[0], bmv.co[1], bmv.co[2]))
    
    
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    
    # TODO At the moment we are splitting only on one of the two edges.
    def split_patch(patch_edges, patch_verts, mesh):
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
    
    # Split all the patches!
    new_patches = []
    for i, patch in enumerate(patches):
        new_patches += split_patch(patch, patches_verts[i], bm)
    patches = new_patches
     
    print(len(patches))
     
    # Plot the meshes.
    for patch in patches:
        curves = []
        for edge in patch:
            verts = (tuple((convert_vert(bm.verts[i]) for i in edge)))
            curves.append(geom.generate_spline(verts, mmath.interp_bezier_curve_2))
    
        polygon = geom.PolygonsNetQuad(curves)
        #points = list(geom.sample_patch_samples(polygon, 25))
        quads = list(geom.sample_patch_quads_samples(polygon, 5))
        
        c1_points = list(geom.sample_curve_samples(curves[0], 10))
        c2_points = list(geom.sample_curve_samples(curves[1], 10))
        c3_points = list(geom.sample_curve_samples(curves[2], 10))
        c4_points = list(geom.sample_curve_samples(curves[3], 10))
        
        ax.plot([p.x for p in c1_points], [p.y for p in c1_points], [p.z for p in c1_points])
        ax.plot([p.x for p in c2_points], [p.y for p in c2_points], [p.z for p in c2_points])
        ax.plot([p.x for p in c3_points], [p.y for p in c3_points], [p.z for p in c3_points])
        ax.plot([p.x for p in c4_points], [p.y for p in c4_points], [p.z for p in c4_points])
        # Print as quads
        ax.add_collection3d(Poly3DCollection(quads))
        # Print as points
        #ax.plot([p.x for p in points], [p.y for p in points], [p.z for p in points], "o", label="Geometry points")
    
    plt.show()
