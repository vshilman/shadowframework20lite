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
import utils
import algorithm1 as alg
import blender

# Matplotlib imports
import matplotlib as mpl
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
from mpl_toolkits.mplot3d.art3d import Poly3DCollection

# Initialize blender varialbles
context = bpy.context
scene = context.scene
objects = scene.objects

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
    singular_verts = alg.find_singular_vertices(verts_list)
    
    # Compute the superedges: the long edges which should be the borders of the patches.
    macro_edges = alg.compute_macro_edges(singular_verts)

    # Now we need to understand which vertex belong to which face. We use the connected components approach.
    boundaries = set(sum(macro_edges, ()))
    
    patches_verts = alg.partition_mesh_vertices(verts_indexes, boundaries, bm)

    # Now we need to understand which superedges belong to which face.
    patches = alg.compute_patch_edges(patches_verts, macro_edges)
    
    # We now need to reorder the vertices of each face so that we can build a spline on them.
    for i, part in enumerate(patches):
        patches[i] = alg.reorder_patch_edges(part)
    
    # Split all the patches!
    new_patches = []
    for i, patch in enumerate(patches):
        new_patches += alg.split_patch(patch, patches_verts[i], bm)
    patches = new_patches
         
    # Plot the mesh.
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    
    for patch in patches:
        curves = []
        for edge in patch:
            verts = (tuple((blender.convert_vert(bm.verts[i]) for i in edge)))
            curves.append(geom.generate_spline(verts, mmath.interp_bezier_curve_2))
    
        polygon = geom.PolygonsNetQuad(curves)
        #points = list(geom.sample_patch_samples(polygon, 25))
        quads = list(geom.sample_patch_quads_samples(polygon, 20))
        
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
