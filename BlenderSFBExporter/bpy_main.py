import bpy
import imp
import bmesh
from functools import reduce
from random import shuffle, random
import functools
import itertools

output_path = "/home/swordfish/workspaces/grafica/SF20Research/SFTests/testsData/netGeometries/input/BlenderTest.sfb"
f_out = open(output_path, "wt")


#Managing import the blender way. Dirty hack to get access to files in the same dir.
import os, sys
blend_dir = os.path.dirname(bpy.data.filepath)
if blend_dir not in sys.path:
    sys.path.append(blend_dir)

from geometry import *
from funchelps import *

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
SFPolygonsNetData01Positions as SFVertexListData16{
	vertices = %s
}

SFPolygonsNetData01 as SFPolygonsNetData{
	blocks = P
	verticesSize = %s
	valuesData == SFPolygonsNetData01Positions
	edgesIndices = %s
	polygonsIndices = %s
	guides = SFCurvesGuideData{
		curveModel = SFBasisSpline2Data
		indices = %s
	}
}
"""


context = bpy.context
scene = context.scene
objects = scene.objects

def deform(mesh):
    '''Test function to modify the geometry using curves.
       Moves the control point of the bezier curve.'''
    for i, curve in enumerate(mesh.curves):
        print(mesh.curves)
        v1 = mesh.verts[curve[0]]
        v2 = mesh.verts[curve[2]]
        rnd = lambda: (random() - 0.5) * 0.1
        vm = (v1 + v2) * 0.5 + Vertex((rnd(), rnd(), rnd()))
        mesh.verts.append(vm)
        mesh.curves[i] = Curve2([curve[0], mesh.verts.index(vm), curve[2]])

def merge_curves(mesh, ci1, ci2, middle_point):
    #TODO this will be much more complex
    c1, c2 = sort_curves([mesh.curves[ci1], mesh.curves[ci2]])
    return Curve((c1[0], c1[0], c2[-1]))
    
def compute_high_level_mesh(mesh, v_index):
    neighborsi = get_adj_vertex(mesh, v_index)
    edgesi = get_adj_edges(mesh, v_index)
    facesi = get_adj_faces(mesh, v_index)
    
    temp = reduce(lambda x,y: x+y, (get_adj_vertex(mesh,v) for v in neighborsi))
    verts_count = [(temp.count(v), v) for v in set(temp)]
    corners = [vi for c, vi in verts_count if c == 2]
    
    #Compute new edges
    new_curves = []
    for middle_point in neighborsi:
        n_edges_i = get_adj_edges(mesh, middle_point)
        e1, e2 = tuple([ei for ei in n_edges_i if any([c in mesh.curves[ei] for c in corners])])
        new_curve = merge_curves(mesh, e1, e2, middle_point)
        new_curves.append(new_curve)
    
    for ei in edgesi: remove_curve(mesh, ei)
    for fi in facesi: remove_face(mesh, fi)
    new_curves = sort_curves(new_curves)
    mesh.curves.extend(new_curves)
    newface = Face([mesh.curves.index(x) for x in new_curves])
    mesh.faces.append(newface)
    
    mesh.update_indexes()
    

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
    verts =  [v * 0.5 for v in verts]

    curves = [convert_edge(e) for e in bm.edges]
    faces =  [convert_face(f) for f in bm.faces]

    #print(verts)
    #print(curves)
    #print(faces)

    my_mesh = Mesh(verts, curves, faces)

    #compute_high_level_mesh(my_mesh, 65)

    edges =  [to_edge(c) for c in my_mesh.curves]

    verts_string = ' '.join(map(str,  my_mesh.verts))
    edges_string = ' '.join(map(lambda x: "(%s,%s,1)" % tuple(x), edges))
    curves_string = ' '.join(map(str, my_mesh.curves))
    faces_string = ' '.join(map(str, my_mesh.faces))

    patchs = []
    polygons_out = []

    #for p in polygons:
    #    patches.append(Polygon
    #    l = map(edges_tuples.index, p.edge_keys)
    #    polygons_out.append('(' + reduce(lambda x,y: "%s,%s" % (x,y), l) + ")")

    final_string = template % (verts_string,
                               len(verts),
                               edges_string,
                               faces_string,
                               curves_string)
    f_out.write(final_string)


f_out.close()

