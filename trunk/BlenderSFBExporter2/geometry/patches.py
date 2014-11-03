'''
In this file we have
'''

class BasePatch(list):
    '''Base representation of a curve. You can set the verts and the function you want.'''
    def __init__(self, vals, func):
        self.func = func
        return list.__init__(self, vals)
    def uv(self, u, v):
        assert(u >= 0 and u <= 1.0), "u parameter has to be between 0.0 and 1.0"
        assert(v >= 0 and v <= 1.0), "v parameter has to be between 0.0 and 1.0"
        return self.func(u, v, *self)
    def __repr__(self):
        return 'Patch(func=%s, verts=(%s))' % (self.func.__name__, ','.join(self))
