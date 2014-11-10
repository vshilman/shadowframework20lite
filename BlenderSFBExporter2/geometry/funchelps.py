

from itertools import chain, dropwhile, tee
from decimal import Decimal, getcontext
from math import sqrt

def stringify_list(l):
    return list.__str__(l).replace('[','(').replace(']',')').replace(' ', '')

def is_scalar(n):
    return isinstance(n, (int, float, complex))

def pprint(o):
    """Pretty print an object"""
    print("%s: %s" % (o.__class__.__name__, str(o)))

def for_all_apply(f, l):
    '''Apply the function f to any element of the list l.'''
    for i in range(len(l)):
        l[i] = f(l[i])

#TODO Still something is wrong with this function
THRESHOLD = 0.01
def compare_float(f1, f2):
    getcontext().prec = 1
    d1 = getcontext().create_decimal_from_float(f1)
    d2 = getcontext().create_decimal_from_float(f2)
    return int(d1.compare(d2))

def verts_almost_equal(v1, v2):
    return round(v1 - v2, 7) == [0,0,0]

def compare_verts(v1, v2):
    return all(compare_float(c1, c2) == 0 for c1, c2 in zip(v1, v2))

def concat(lists):
    return sum(lists, [])

def dot(vec1, vec2):
    return sum(e1 * e2 for e1, e2 in zip(vec1, vec2))

def vec_product(vec1, vec2):
    assert(len(vec1) == len(vec2))
    return tuple(map(lambda x: x[0]*x[1], zip(vec1, vec2)))

def float_range(start = 0.0, stop = 1.0, step = 0.1):
    t = start
    while t <= stop:
        yield t
        t += step

def almost_equal_lists(set1, set2):
    '''Compare two lists of verts and retuns true if they are almost equal.'''
    assert(len(set1) == len(set2))
    if(set1 == set2):
        return True
    s1, s2 = list(set1), list(set2)
    for e1 in s1:
        for e2 in s2:
            if verts_almost_equal(e1, e2):
                s1.remove(e1)
                s2.remove(e2)
                return almost_equal_lists(s1, s2)
    return False

def are_sorted(curves):
    edges = [c.to_edge() for c in curves]
    t = zip(edges, edges[1:])
    return all(map(lambda x: x[0][-1] == x[1][0], t))

def sort_curves(cs, last=None):
    '''Returns the curves so they are in the correct order'''
    if not cs: return []
    if not last: return [cs[0]] + sort_curves(cs[1:], cs[0])
    it = chain(cs, map(lambda x: x.invert(), cs))
    curve = next(dropwhile(lambda x: x[0] != last[-1], it))
    cs.remove(curve)
    return [curve] + sort_curves(cs, curve)

def split_list(l, n):
    '''Split the list l into chunks with a size of maximum n. The first element
    of the list n equals the last of the list'''
    for i in range(0, len(l), n):
        start = max(0, i-1)
        end = i+n
        yield l[start:end]

def isqrt(val):
    return int(sqrt(val))