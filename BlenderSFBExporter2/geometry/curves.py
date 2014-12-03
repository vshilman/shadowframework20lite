import utils

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
        return 'BaseCurve(func=%s, verts=(%s))' % (self.func.__name__, ','.join(list.__repr__(self)))

class SuperCurve:
    '''This class represents a collection of curves. It might be etherogeneus
    but the curves have to implement a curve method.'''
    def __init__(self, curves):
        self.curves = curves
    def t(self, t_val):
        normt = t_val * len(self.curves)
        curve_index = min(int(normt), len(self.curves) - 1)
        return self.curves[curve_index].t(normt - curve_index)
    def __repr__(self):
        return repr(self.curves)

class SubCurve:
    '''This class represent a subset of a curve starting and ending to different ts.'''
    def __init__(self, curve, start_t, end_t):
        assert(end_t > start_t), "Error: end_t has to be greater that start_t."
        self.curve= curve
        self.start_t = start_t
        self.end_t = end_t
    def t(self, t_val):
        return self.curve.t(utils.lint(self.start_t, self.end_t, t_val))

def __split_cpoints(cpoints, size):
    '''Split the cpoints to create a spline.'''
    if len(cpoints) <= size:
        return [cpoints]
    return [cpoints[:size]] + __split_cpoints(cpoints[size-1:], size)

def generate_spline(cpoints, func):
    '''Generate a spline from a series of control points and a function'''
    nargs = utils.get_number_arguments(func)
    result = []
    for cps in __split_cpoints(cpoints, nargs):
        if len(cps) == nargs:
            result.append(BaseCurve(cps, func))
        else:
            #TODO This is bad. This just fixes the number of verts, but it should be managed properly.
            added_verts = tuple([cps[-1]] * (nargs - len(cps)))
            result.append(BaseCurve(cps + added_verts, func))
    return SuperCurve(result)
    