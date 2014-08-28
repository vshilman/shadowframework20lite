import bpy
import imp
import bmesh
from functools import reduce
from random import shuffle, random
import functools
import itertools

output_path = "/home/swordfish/workspaces/SF20Research/SFTests/testsData/geometries/input/BlenderTest.sfb"
f_out = open(output_path, "wt")


#Managing import the blender way. Dirty hack to get access to files in the same dir.
import os, sys
blend_dir = os.path.dirname(bpy.data.filepath)
if blend_dir not in sys.path:
    sys.path.append(blend_dir)

from geometry import *
from funchelps import *

from mymath import fit_bezier_patch, bezier_patch_2_tuple, bezier_patch_1_tuple, interpolating_bezier_patch_2_tuple
import mymath
import numpy

#import tests
#imp.reload(tests)
#tests.test_all()

ENABLE_TESTS = False
VERBOSE_TEST = False

def to_edge(curve):
    '''Convert the given curve to the corresponding edge.'''
    return Edge([curve[0], curve[-1]])
    
def sample(mesh, curve, t):
    '''Sample the given curve of the given mesh at the point t. Curve has to implement __sample__.'''
    return curve.__sample__(mesh, t)


#Conversion methods
def convert_vertex(v):
    """Takes a blender vertex and converts it into our conveninent representation"""
    return Vertex(v.co)
def convert_edge(e):
    """Takes a blender edge and converts it into our convenient representation"""
    return Curve([e.verts[0].index, e.verts[0].index, e.verts[1].index])
def convert_face(f):
    return Face([e.index for e in f.edges])

template = """
SFMeshGeometry00%s = SFMeshGeometryData{
	primitivesArray = SFPrimitiveArrayData{
		primitive = Quad2PN
		// Positions Array
		primitiveData = SFVertexListDataUnit8{
			 vertices = %s
		}
		primitiveData += SFVertexListDataUnit8{
			 vertices = (0.0,1.0)
		}
		//indices
        %s
	}
	firstElement=0
	lastElement=1
}
"""

def patches_to_sfb(index, list_cpoints):
    cpoints = []
    elements = []
    
    for i, cpts in enumerate(list_cpoints):
        cpoints += cpts
        start = sum(map(len, elements)) // 2
        indexes = list(range(start, start + len(cpts))) + [0] * len(cpts)
        elements.append(indexes)
    
    cpoints_str = ' '.join(map(str, cpoints))
    elements_strs = [str(e).replace(' ','').replace('[','(').replace(']',')') for e in map(str, elements)]
    elements_prefixes = ["elements = "] + ["elements += "] * (len(elements_strs) - 1)
    elements_final = '\n'.join(''.join(e) for e in zip(elements_prefixes, elements_strs))
    return template % (index, cpoints_str, elements_final)

def convert_to_sf_points(cpoints):
    A,B,C,D,AB,BC,CD,DA,ABCD = cpoints
    return [A, AB, B, DA, ABCD, BC, D, CD, C]
    #return [A, DA, D, AB, ABCD, CD, B, BC, C]
    #return [A, DA, C, AB, ABCD, CD, ]

def high_degree_approximation(verts):
    '''Split the verts into four sets (separated on the average) and approximate each of them.'''
    avgx = numpy.mean([v[0] for v in verts])
    avgy = numpy.mean([v[1] for v in verts])
    
    f1 = lambda v: v[0] < avgx
    f2 = lambda v: v[1] < avgy
    
    sub_verts = [None] * 4
    sub_verts[0] = list(filter(lambda v: f1(v) and f2(v), verts))
    sub_verts[1] = list(filter(lambda v: f1(v) and not f2(v), verts))
    sub_verts[2] = list(filter(lambda v: not f1(v) and f2(v), verts))
    sub_verts[3] = list(filter(lambda v: not f1(v) and not f2(v), verts))
    
    sub_patches_cpoints = [0] * 4
    
    for i,vs in enumerate(sub_verts):
        sub_patches_cpoints[i] = [Vertex(x) for x in fit_bezier_patch(vs, bezier_patch_2_tuple)]
    
    return sub_patches_cpoints

context = bpy.context
scene = context.scene
objects = scene.objects


for obj in objects:
    #Blender console: read patch
    #scene = bpy.context.scene
    #patch = scene.objects["SurfPatch"]
    try:
        mesh = obj.to_mesh(scene, True, 'PREVIEW', calc_tessface=True)
    except RuntimeError:
        print("This mesh doesn't have a geometry!")
        continue
    
    bm = bmesh.new()
    bm.from_mesh(mesh)
    
    verts =  [convert_vertex(v) for v in bm.verts]
    
    #Scale the model
    verts =  [v for v in verts]
    
    #Approximate with degree 1 patch
    estimated_patch1 = [Vertex(x) for x in fit_bezier_patch(verts, bezier_patch_1_tuple)]
    patch1 = mymath.compile_bezier_patch_1(*estimated_patch1)
    points_patch1 = mymath.sample_func_2D(patch1, 0.1)
    print(squared_error_verts(verts, points_patch1))
    
    #Write patch 1 to file
    #final_string = patches_to_sfb(0, [estimated_patch1])
    #f_out.write(final_string)
    
    #Approximate with degree 2 patch
    estimated_patch2 = [Vertex(x) for x in fit_bezier_patch(verts, interpolating_bezier_patch_2_tuple)]
    patch2 = mymath.compile_interpolating_bezier_patch_2(*estimated_patch2)
    points_patch2 = mymath.sample_func_2D(patch2, 0.1)
    print(squared_error_verts(verts, points_patch2))
    
    #Write patch 2 to file
    #final_string = patches_to_sfb(0, [estimated_patch2])
    #f_out.write(final_string)
    
    #Approximate with high degree patches
    sub_patches_cpoints = high_degree_approximation(verts)
    #sub_patches_cpoints = list(map(convert_to_sf_points, sub_patches_cpoints))
    
    final_string = patches_to_sfb(0, sub_patches_cpoints)
    f_out.write(final_string)
    
    sub_patches = [mymath.compile_bezier_patch_2(*cpoints) for cpoints in sub_patches_cpoints]
    sub_points = [mymath.sample_func_2D(sub_patch, 0.2) for sub_patch in sub_patches]
    
    for i in range(4):
        cpoints = sub_patches_cpoints[i]
        cverts = convert_to_sf_points([Vertex(cp) for cp in cpoints])
    
    points_patch3 = concat(sub_points)
    print(squared_error_verts(verts, points_patch3))
    
    #Write patch 2 to sfb file
    #estimated_verts = (Vertex(p) for p in estimated_patch2)
    #estimated_cpoints_string = ' '.join((str(v) for v in estimated_verts))
    #print(estimated_cpoints_string)

    #final_string = patch_to_sfb(1, estimated_cpoints_string)
    #f_out.write(final_string)


f_out.close()

