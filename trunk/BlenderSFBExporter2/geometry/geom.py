#This file containes simple representations of all the geometric elements

from funchelps import *

###############################################################################
# Helpers
###############################################################################

class Mesh:
    """Basic mesh representations"""
    def __init__(self, verts, curves, faces):
        self.verts = verts
        self.curves = curves
        self.faces = faces
    def update_indexes(self):
        #Update faces
        new_faces = [f for f in self.faces if f != None]

        #Update curves, need to correct faces
        nones = [i for i, _ in enumerate(self.curves) if _ == None]
        def translation(index):
            temp = filter(lambda x: x < index, nones)
            return index - len(list(temp))
        for face in new_faces:
            for_all_apply(translation, face)
        self.faces = new_faces
        new_curves = [c for c in self.curves if c != None]

        #Update vertices, need to correct curves
        nones = [i for i, _ in enumerate(self.verts) if _ == None]
        def translation(index):
            temp = filter(lambda x: x < index, nones)
            return index - len(list(temp))
        for curve in new_curves:
            for_all_apply(translation, curve)
        self.curves = new_curves
        self.verts = [v for v in self.verts if v != None]

    def __str__(self):
        return str(self.verts) + str(self.edges) + str(self.faces)


###############################################################################
# Patches
###############################################################################

from itertools import chain

class Face(list):
    """This patch represents a second degree quad at the moment"""
    def __init__(self, verts):
        return list.__init__(self, verts)
    def get_curves_iter(self, mesh):
        return (mesh.curves[i] for i in self)
    def get_edges_iter(self, mesh):
        return filter(lambda x: x.to_edge(), self.get_curves_iter())
    def get_edge_verts_iter(self, mesh):
        return chain(e.get_verts_iter() for e in self.get_edges_iter())
    def get_all_verts_iter(self, mesh):
        return chain(c.get_verts_iter() for c in self.get_curves_iter())
    def __sample2D__(self, mesh, u, v):
        cs = [mesh.curves[i].to_edge() for i in self]
        cs = sort_curves(cs)
        A,B,C,D = (mesh.verts[c[0]] for c in cs)
        u1 = inter_linear(A, B, u)
        u2 = inter_linear(D, C, u)
        return inter_linear(u1, u2, v)
    def __str__(self):
        return stringify_list(self)

class Bezier2Patch(Face):
    """This patch represents a second degree interpolating bezier patch"""
    def __init__(self, verts):
        assert(len(verts) == 4)
        return Face.__init__(self, verts)
    def __sample2D__(self, mesh, u, v):
        cs = [mesh.curves[i] for i in self]
        cs = sort_curves(cs)
        A,B,C,D = (mesh.verts[c[0]] for c in cs)
        AB,BC,CD,DA = (mesh.verts[c[1]] for c in cs)
        ABCD = 0.5 * (AB+BC+BC+CD)- 0.25 * (A+B+C+D)
        c1 = inter_interp_bezier2(A, AB, B, u)
        c2 = inter_interp_bezier2(DA, ABCD, BC, u)
        c3 = inter_interp_bezier2(D, CD, C, u)
        return inter_interp_bezier2(c1, c2, c3, v)


###############################################################################
# Misc functions on meshes
###############################################################################

def get_limiting_verts(mesh, fi):
    '''Return the vertexes limiting a face'''
    edges = (mesh.curves[i].to_edge() for i in mesh.faces[fi])
    return set(sum(edges, []))

def remove_face(mesh, i):
    mesh.faces[i] = None

def remove_curve(mesh, i):
    mesh.curves[i] = None

def remove_vertex(mesh, i):
    mesh.vertex[i] = None

def get_adj_edges(mesh, v_index):
    '''Returnts the edges connected to the given index'''
    edges = list(c.to_edge() for c in mesh.curves)
    return [i for i, e in enumerate(edges) if v_index in e]

def from_to(mesh, v_index, e_index):
    verts = mesh.curves[e_index].to_edge()
    assert(v_index in verts)
    return verts[0] if v_index == verts[1] else verts[1]

def get_adj_vertex(mesh, v_index):
    '''Returns the vertices connected to the given index'''
    adj_edges = get_adj_edges(mesh, v_index)
    return [from_to(mesh, v_index, e) for e in adj_edges]

def get_adj_faces(mesh, v_index):
    edges = get_adj_edges(mesh, v_index)
    return [i for i,f in enumerate(mesh.faces) if any([e in f for e in edges])]

def get_edge_direction(mesh, e_index):
    vi1,vi2 = mesh.edges[e_index]
    return mesh.verts[vi1] - mesh.verts[vi2]

def list_to_verts(l):
    return [Vertex(e) for e in l]

###############################################################################
# Estimation
###############################################################################


    
