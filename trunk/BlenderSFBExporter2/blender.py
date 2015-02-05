'''This file provides static functions for various interaction with blender.'''

import bpy
import bmesh
import geometry as geom

def convert_vert(bmv):
    '''Convert blender bmesh vertexes to our geometry.Vertex class.'''
    return geom.Vertex((bmv.co[0], bmv.co[1], bmv.co[2]))

def convert_mesh(mesh):
    '''Takes a general blender mesh and convert it into its BMesh representation'''
    bm = bmesh.new()
    bm.from_mesh(mesh)
    return bm