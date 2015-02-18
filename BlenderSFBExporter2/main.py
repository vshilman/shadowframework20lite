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

import numpy as np

# Matplotlib imports
import matplotlib as mpl
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
from mpl_toolkits.mplot3d.art3d import Poly3DCollection
from matplotlib.colors import colorConverter

import plot

# Initialize blender varialbles
context = bpy.context
scene = context.scene
objects = scene.objects

OUTPUT_DIR="/tmp/"
NAME="graph"

# Config parameters
BASE_MESH_TASSELLATE = 2
VIEW_INIT = (13, -45)

INDEX_SIZE = 2
VERTEX_SIZE = 12

def plot_bm_mesh(bm):
    verts_list = list(bm.verts)
    faces_list = list(bm.faces)
    verts_indexes = [v.index for v in verts_list]
    
    quads = [[blender.convert_vert(v) for v in f.verts] for f in faces_list]
    
    plot.plot_quads(quads, VIEW_INIT)

def randColor():
    return np.random.rand(3,1)

def plot_edges(skmacro_edges, sksingular_verts, verts_list):  
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.view_init(*VIEW_INIT)
    
    cc = lambda arg: colorConverter.to_rgba(arg, alpha=0.6)
    colors = ['r', 'g', 'b', 'y', 'm', 'c']
    
    for edge in skmacro_edges:
        verts = (tuple(verts_list[i] for i in edge))
        curve = geom.generate_spline(verts, mmath.interp_bezier_curve_2)
        ax.plot([p.x for p in verts], [p.y for p in verts], [p.z for p in verts], linewidth=3)
    
    verts = [blender.convert_vert(v) for v in sksingular_verts]
    ax.plot([v.x for v in verts], [v.y for v in verts], [v.z for v in verts], "o")
    
    plt.savefig(OUTPUT_DIR + NAME + "_edges.png", format = "png")
    plt.show()

def plot_final_mesh(patches, verts_list, scale_tassellation=1, old_patches=[]):
    if not old_patches:
        old_patches = patches
    
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.view_init(*VIEW_INIT)
    
    def darker(c): 
        return (c[0] * 0.5, c[1] * 0.5, c[2] * 0.5)
    
    def cc(arg):
        return colorConverter.to_rgba(arg, alpha=0.6)
    
    edge_color = lambda c: darker(cc(c))
    
    colors = [randColor() for patch in patches]
    
    for i, patch in enumerate(patches):
        curves = []
        for edge in patch:
            verts = (tuple(verts_list[i] for i in edge))
            curves.append(geom.generate_spline(verts, mmath.interp_bezier_curve_2))
    
        # We want to tassellate the patch so that it looks always good.
        polygon = geom.PolygonsNetQuad(curves)
        #print(len(old_patches[i][0]))
        u_tassellation = max(len(old_patches[i][0]) / scale_tassellation, 1)
        v_tassellation = max(len(old_patches[i][1]) / scale_tassellation, 1)
        
        #print(int(u_tassellation), int(v_tassellation))
        
        quads = list(geom.sample_patch_quads_samples_uv(polygon, int(u_tassellation), int(v_tassellation)))
        
        c1_points = list(geom.sample_curve_samples(curves[0], 10))
        c2_points = list(geom.sample_curve_samples(curves[1], 10))
        c3_points = list(geom.sample_curve_samples(curves[2], 10))
        c4_points = list(geom.sample_curve_samples(curves[3], 10))

        # Print only the curves
        ax.plot([p.x for p in c1_points], [p.y for p in c1_points], [p.z for p in c1_points], color="black", linewidth=0)
        ax.plot([p.x for p in c2_points], [p.y for p in c2_points], [p.z for p in c2_points], color="black", linewidth=0)
        ax.plot([p.x for p in c3_points], [p.y for p in c3_points], [p.z for p in c3_points], color="black", linewidth=0)
        ax.plot([p.x for p in c4_points], [p.y for p in c4_points], [p.z for p in c4_points], color="black", linewidth=0)

        # Print as quads
        collection = Poly3DCollection(quads, facecolors=[cc(colors[i % len(colors)])], edgecolors=[edge_color(colors[i % len(colors)])])
        ax.add_collection3d(collection)
    
    plt.savefig(OUTPUT_DIR + NAME + "_final.png", format = "png")
    plt.show()

def plot_base_mesh(patches, verts_list):
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.view_init(*VIEW_INIT)
    
    def darker(c): 
        return (c[0] * 0.5, c[1] * 0.5, c[2] * 0.5)
    
    def cc(arg):
        return colorConverter.to_rgba(arg, alpha=0.6)
    
    edge_color = lambda c: darker(cc(c))
    
    colors = ['r', 'g', 'b', 'y', 'm', 'c']
    
    for i, patch in enumerate(patches):
        curves = []
        for edge in patch:
            verts = (tuple(verts_list[i] for i in edge))
            curves.append(geom.generate_spline(verts, mmath.interp_bezier_curve_2))
    
        # We want to tassellate the patch so that it looks always good.
        polygon = geom.PolygonsNetQuad(curves)        
        quads = list(geom.sample_patch_quads_samples_uv(polygon, 1, 1))
        
        c1_points = list(geom.sample_curve_samples(curves[0], 1))
        c2_points = list(geom.sample_curve_samples(curves[1], 1))
        c3_points = list(geom.sample_curve_samples(curves[2], 1))
        c4_points = list(geom.sample_curve_samples(curves[3], 1))

        # Print only the curves
        ax.plot([p.x for p in c1_points], [p.y for p in c1_points], [p.z for p in c1_points], color="black", linewidth=0)
        ax.plot([p.x for p in c2_points], [p.y for p in c2_points], [p.z for p in c2_points], color="black", linewidth=0)
        ax.plot([p.x for p in c3_points], [p.y for p in c3_points], [p.z for p in c3_points], color="black", linewidth=0)
        ax.plot([p.x for p in c4_points], [p.y for p in c4_points], [p.z for p in c4_points], color="black", linewidth=0)

        # Print as quads
        collection = Poly3DCollection(quads, facecolors=[cc(colors[i % len(colors)])], edgecolors=[edge_color(colors[i % len(colors)])])
        ax.add_collection3d(collection)
    
    plt.savefig(OUTPUT_DIR + NAME + "_base.png", format = "png")
    plt.show()

#def plot_final_mesh(patches, alg_verts):
    #fig = plt.figure()
    #ax = fig.add_subplot(111, projection='3d')
    #ax.view_init(*VIEW_INIT)

    #cc = lambda arg: colorConverter.to_rgba(arg, alpha=0.6)
    #colors = ['r', 'g', 'b', 'y', 'm', 'c']
    
    #for i, patch in enumerate(patches):
        #curves = []
        #for edge in patch:
            #verts = (tuple(alg_verts[i] for i in edge))
            #curves.append(geom.generate_spline(verts, mmath.interp_bezier_curve_2))
    
        #polygon = geom.PolygonsNetQuad(curves)
        ##points = list(geom.sample_patch_samples(polygon, 25))
        #quads = list(geom.sample_patch_quads_samples(polygon, 2))
        
        #c1_points = list(geom.sample_curve_samples(curves[0], 10))
        #c2_points = list(geom.sample_curve_samples(curves[1], 10))
        #c3_points = list(geom.sample_curve_samples(curves[2], 10))
        #c4_points = list(geom.sample_curve_samples(curves[3], 10))

        ## Print only the curves
        #ax.plot([p.x for p in c1_points], [p.y for p in c1_points], [p.z for p in c1_points], linewidth=0)
        #ax.plot([p.x for p in c2_points], [p.y for p in c2_points], [p.z for p in c2_points], linewidth=0)
        #ax.plot([p.x for p in c3_points], [p.y for p in c3_points], [p.z for p in c3_points], linewidth=0)
        #ax.plot([p.x for p in c4_points], [p.y for p in c4_points], [p.z for p in c4_points], linewidth=0)
        
        ## Print as quads
        #ax.add_collection3d(Poly3DCollection(quads, facecolors=[cc(colors[i % len(colors)])]))
    #plt.show()
    

for obj in objects:
    try:
        mesh = obj.to_mesh(scene, True, 'PREVIEW', calc_tessface=True)
    except RuntimeError:
        print("This mesh doesn't have a geometry!")
        continue
    
    bm = blender.convert_mesh(mesh)
    #plot_bm_mesh(bm)
    
    verts_list = [blender.convert_vert(v) for v in bm.verts]
    
    ## Extract skeleton mesh
    skpatches, skmacro_edges, sksingular_verts = alg.extract_base_mesh(bm)
    
    plot_edges(skmacro_edges, sksingular_verts, verts_list)
    
    #sys.exit(0)
    
    # Plot skeleton mesh
    plot_base_mesh(skpatches, verts_list)
    #sys.exit(0)

    # Run the full fledged algorithm
    old_verts, old_patches, alg_verts, alg_patches = alg.run(bm)
    
    plot_final_mesh(alg_patches, alg_verts, BASE_MESH_TASSELLATE, old_patches)
    #print(set(sum(alg_patches, [])))
