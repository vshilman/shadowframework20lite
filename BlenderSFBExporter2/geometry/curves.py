from .utils import lint

"""
This class contains representations of curves.
"""

class BaseCurve(list):
    '''Base representation of a curve. You can set the verts and the function you want.'''
    def __init__(self, vals, func):
        self.func = func
        return list.__init__(self, vals)
    def t(self, t_val):
        assert(t_val >= 0 and t_val <= 1.0), "t parameter has to be between 0.0 and 1.0"
        return self.func(t_val, *self)
    def __repr__(self):
        return 'BaseCurve(func=%s, verts=(%s))' % (self.func.__name__, ','.join(self))

class SuperCurve(list):
    '''This class represents a collection of curves. It might be etherogeneus
    but the curves have to implement a curve method.'''
    def __init__(self, curves):
        self.curves = curves
    def t(self, t_val):
        normt = t_val * len(self.curves)
        curve_index = min(int(normt), len(self.curves) - 1)
        return self.curves[curve_index].t(normt - curve_index)

class SubCurve:
    '''This class represent a subset of a curve starting and ending to different ts.'''
    def __init__(self, curve, start_t, end_t):
        assert(end_t > start_t), "Error: end_t has to be greater that start_t."
        self.curve= curve
        self.start_t = start_t
        self.end_t = end_t
    def t(self, t_val):
        return self.curve.t(lint(self.start_t, self.end_t, t_val))
