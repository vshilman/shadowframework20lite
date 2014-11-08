"""
This file contains the python classes of the basic geometric elements.
"""

import numpy as np

def stringify_list(l):
    return list.__str__(l).replace('[','(').replace(']',')').replace(' ', '')

class Vertex(np.ndarray):
    '''Basic vertex representation. Vertex are immutable.'''
    @property
    def x(self):
        return self[0]
    @property
    def y(self):
        return self[1]
    @property
    def z(self):
        return self[2]
    def __new__(cls, vals):
        assert(len(vals) == 3), "Vertex object requires exactly three parameters."
        return np.array(vals).view(cls)
    def __eq__(self, other):
        return np.allclose(self, other)
    def __str__(self): 
        return "(%s)" % ','.join(str(x) for x in self)
    def __round__(self, ndigits = 0): 
        return Vertex([round(val, ndigits) for val in self])
        