'''
In this file we have basic implementations of generic patches.
'''

class BasePatch(list):
    '''Base representation of a patch. You can set the verts and the function you want.'''
    def __init__(self, vals, func):
        self.func = func
        return list.__init__(self, vals)
    def uv(self, u, v):
        assert(u >= 0 and u <= 1.0), "u parameter has to be between 0.0 and 1.0"
        assert(v >= 0 and v <= 1.0), "v parameter has to be between 0.0 and 1.0"
        return self.func(u, v, *self)
    def __repr__(self):
        return 'Patch(func=%s, verts=(%s))' % (self.func.__name__, ','.join(self))

class PolygonsNetQuad(list):
    def __init__(self, funcs):
        # Check that the polygon is valid
        assert len(funcs) == 4, "PolygonsNetQuad needs exaclty 4 curves."
        assert funcs[0].t(1) == funcs[1].t(0)
        assert funcs[1].t(1) == funcs[2].t(0)
        assert funcs[2].t(1) == funcs[3].t(0)
        assert funcs[3].t(1) == funcs[0].t(0)
        self.funcs = funcs
        self.vs = (funcs[0].t(0), funcs[1].t(0), funcs[2].t(0), funcs[3].t(0))
        return list.__init__(self, funcs)
    def uv(self, u, v):
        assert(u >= 0 and u <= 1.0), "u parameter has to be between 0.0 and 1.0"
        assert(v >= 0 and v <= 1.0), "v parameter has to be between 0.0 and 1.0"
        es1 = self.funcs[0].t(u)
        es2 = self.funcs[1].t(v)
        es3 = self.funcs[2].t(1-u)
        es4 = self.funcs[3].t(1-v)
        
        v0, v1, v2, v3 = self.vs
        
        temp = es1 * (1-v) + es2 * u + es3 * v + es4 * (1-u)
        temp += - (1-u) * (1-v) * v0 - (1-v) * u * v1 - u * v * v2 - (1-u) * v * v3
        
        return temp
    def __repr__(self):
        return 'PolygonsNetQuad(verts=(%s))' % self.vs
        
