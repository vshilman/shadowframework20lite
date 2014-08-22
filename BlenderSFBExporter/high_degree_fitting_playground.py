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

from mymath import fit_bezier_patch, bezier_patch_2_tuple, bezier_patch_1_tuple
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
SFMeshGeometry001 = SFMeshGeometryData{
	primitivesArray = SFPrimitiveArrayData{
		primitive = Quad2PN
		// Positions Array
		primitiveData = SFVertexListDataUnit8{
			 vertices = %s
		}
		primitiveData += SFVertexListDataUnit8{
			 vertices = (0.0,0.0,1.0)
		}
		//indices
		elements = (0,1,2,3,4,5,6,7,8,0,0,0,0,0,0,0,0,0) 
	}
	firstElement=0
	lastElement=1
}
"""


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
    verts =  [v * 0.1 for v in verts]

    #curves = [convert_edge(e) for e in bm.edges]
    #faces =  [convert_face(f) for f in bm.faces]

    #print(verts)
    #print(curves)
    #print(faces)

    #my_mesh = Mesh(verts, curves, faces)

    #compute_high_level_mesh(my_mesh, 65)

    #edges =  [to_edge(c) for c in my_mesh.curves]

    #verts_string = ' '.join(map(str,  my_mesh.verts))
    #edges_string = ' '.join(map(lambda x: "(%s,%s,1)" % tuple(x), edges))
    #curves_string = ' '.join(map(str, my_mesh.curves))
    #faces_string = ' '.join(map(str, my_mesh.faces))

    #patchs = []
    #polygons_out = []

    #for p in polygons:
    #    patches.append(Polygon
    #    l = map(edges_tuples.index, p.edge_keys)
    #    polygons_out.append('(' + reduce(lambda x,y: "%s,%s" % (x,y), l) + ")")


    #Fitting of all the patch
    estimated_patch1 = [Vertex(x) for x in fit_bezier_patch(verts, bezier_patch_1_tuple)]
    estimated_patch2 = [Vertex(x) for x in fit_bezier_patch(verts, bezier_patch_2_tuple)]
    
    patch1 = mymath.compile_bezier_patch_1(*estimated_patch1)
    patch2 = mymath.compile_bezier_patch_2(*estimated_patch2)
    
    points_patch1 = mymath.sample_func_2D(patch1, 0.1)
    points_patch2 = mymath.sample_func_2D(patch2, 0.1)
    
    print(squared_error_verts(verts, points_patch1))
    print(squared_error_verts(verts, points_patch2))
    
    #Subdivide the patch into four subpatches
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
    
    sub_patches = [mymath.compile_bezier_patch_2(*cpoints) for cpoints in sub_patches_cpoints]
    sub_points = [mymath.sample_func_2D(sub_patch, 0.2) for sub_patch in sub_patches]
    
    points_patch3 = concat(sub_points)
    print(squared_error_verts(verts, points_patch3))
    
    #Write patch 2 to sfb file
    estimated_verts = (Vertex(p) for p in estimated_patch2)
    estimated_cpoints_string = ' '.join((str(v) for v in estimated_verts))
    #print(estimated_cpoints_string)

    final_string = template % (estimated_cpoints_string)
    f_out.write(final_string)


f_out.close()

