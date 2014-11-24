'''This file provides static methods for the interaction with blender.'''

import geometry as geom

def convert_vert(bmv):
    '''Convert blender bmesh vertexes to our geometry.Vertex class.'''
    return geom.Vertex((bmv.co[0], bmv.co[1], bmv.co[2]))