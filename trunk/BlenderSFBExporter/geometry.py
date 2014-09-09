#This file containes simple representations of all the geometric elements

from funchelps import *

###############################################################################
# Helpers
###############################################################################

class Vertex(list):
    '''Basic vertex representation'''
    def __init__(self, vals):
        assert(len(vals) == 3)
        self.x = vals[0]
        self.y = vals[1]
        self.z = vals[2]
        return list.__init__(self, vals)
    def __neg__(self):
        return Vertex([-x for x in self])
    def __add__(self, other):
        if is_scalar(other): 
            other = Vertex([other] * 3)
        return Vertex([a+b for a,b in zip(self,other)])
    def __radd__(self, other): return self.__add__(other)
    def __sub__(self, other):
        return self + (-other)
    def __mul__(self, other):
        if is_scalar(other):
            return Vertex([other * x for x in self])
        raise NotImplementedError()
    def __rmul__(self, other): return self.__mul__(other)
    def __abs__(self): return Vertex([abs(x) for x in self])
    def __eq__(self, other):
        return list.__eq__(self, other) or compare_verts(self, other)
    def __str__(self): return stringify_list(self)
    def __round__(self, ndigits = 0): return Vertex([round(val, ndigits) for val in self])

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
# Interoplations
###############################################################################


from mymath import bezier_curve_n

#def inter_bezier2(A, AB, B, t):
    #u, um = t, 1-t
    #return A*um*um + 2*AB*u*um + B*u*u

def inter_bezier2(A, AB, B, t):
    return bezier_curve_n([A, AB, B])(t)

def inter_interp_bezier2(A, AB, B, t):
    AB2 = (4 * AB - A - B) * 0.5
    return inter_bezier2(A, AB2, B, t)


###############################################################################
# Curves
###############################################################################

class Curve(list):
    """This class represent a generic connection among some vertices."""
    def __init__(self, vals):
        return list.__init__(self, vals)    
    def invert(self):
        return Curve(self[::-1])
    def __eq__(self, other):
        return list.__eq__(self, other) or list.__eq__(self, other.invert())
    def __str__(self):
        return stringify_list(self)
    def to_edge(self):
        return Edge([self[0]] + [self[-1]])
    def get_verts_iter(self, mesh):
        return (mesh.verts[i] for i in self)

class Edge(Curve):
    '''This class represent a straight connection among exactly two verticies.'''
    def __init__(self, verts):
        return list.__init__(self, verts)
    def __sample__(self, mesh, t):
        assert(t >= 0 and t <= 1)
        v1, v2 = (mesh.verts[i] for i in self)
        return v1 * (1-t) + v2 * t
    def __str__(self):
        return stringify_list(self)

class Bezier2Curve(Curve):
    '''This class represent a second degree bezier curve.'''
    def __init__(self, vals):
        assert(len(vals) == 3)
        return Curve.__init__(self, vals)
    def __sample__(self, mesh, t):
        assert(t >= 0 and t <= 1)
        A, AB, B = (mesh.verts[i] for i in self)
        return inter_bezier2(A, AB, B, t)

class InterpolatingBezier2Curve(Curve):
    def __init__(self, vals):
        assert(len(vals) == 3)
        return Curve.__init__(self, vals)
    def __sample__(self, mesh, t):
        assert(t >= 0 and t <= 1)
        A, AB, B = (mesh.verts[i] for i in self)
        return inter_interp_bezier2(A, AB, B, t)

class Spline2Curve(Curve):
    '''This curve represent a spline formed by Bezier 2 curves.'''
    def __init__(self, vals):
        assert((len(vals) - 1) % 2 == 0)
        self.n_curves = (len(vals) - 1) // 2
        return Curve.__init__(self, vals)
    def __sample__(self, mesh, t):
        assert(t >= 0 and t <= 1)
        uniform_t = t * self.n_curves
        u = uniform_t % 1
        c = int(uniform_t // 1)
        A, AB, B = (x for x in mesh.verts[2*c:2*c+3])
        um = 1-u
        return A*um*um+AB*u*um*2+B*u*u

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
# Sampler
###############################################################################

def sample1Dmulti(curves, mesh, step = 0.1):
    '''Sample the iterable of curves at the given step'''
    assert(are_sorted(curves))
    ts = float_range(0.0, float(len(curves)), step * len(curves))
    return [curves[int(t)].__sample__(mesh, t % 1) for t in ts]

def sample2D(patch, mesh, step = 0.1):
    us = list(float_range(0.0, 1.0, step))
    vs = list(float_range(0.0, 1.0, step))
    return [patch.__sample2D__(mesh, u,v) for u in us for v in vs]

def sample1D(curve, mesh, step = 0.1):
    '''Sample the curve at the give step'''
    return (curve.__sample__(mesh, t) for t in float_range(0.0, 1.0, step))

###############################################################################
# Sampler
###############################################################################
from math import sqrt

def module(v):
    '''Returns the module of the given vertex'''
    return v[0]**2 + v[1]**2 + v[2]**2

def squared_error_verts(verts1, verts2):
    #TODO This can be surely optimized and probably exist something better.
    '''Compute the minimum distance among the two meshes'''
    errors = (min(module(v1 - v2) for v2 in verts2)
        for v1 in verts1)
    return sum(errors)

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


    
