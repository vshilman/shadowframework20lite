'''
This function contains methods for computing errors.
'''

def module(v):
    '''Returns the module of the given vertex'''
    return v[0]**2 + v[1]**2 + v[2]**2

def simple_sq_error(verts1, verts2):
    '''Compare the ordered lists of verts.'''
    assert(len(verts1) == len(verts2)), "The two sequences of verts must have the same length."
    diffs = (verts1[i] - verts2[i] for i in range(len(verts1)))
    sq_diffs = (module(x) for x in diffs)
    return sum(sq_diffs)

def simple_max_error(verts1, verts2):
    assert(len(verts1) == len(verts2)), "The two sequences of verts must have the same length."
    diffs = (verts1[i] - verts2[i] for i in range(len(verts1)))
    sq_diffs = (module(x) for x in diffs)
    return max(sq_diffs)

def verts_sets_sq_error(verts, reference):
    '''Compare the current unordered set of verts to a reference. This is slow
    and is not commutative but doesn't require the verts to be ordered.'''
    errors = (min(module(v - vref) for v in verts) for vref in reference)
    return sum(errors)

def verts_max_error(verts, reference):
    '''Compare the verts with the reference and return the maximum error.'''
    errors = (min(module(v - vref) for v in verts) for vref in reference)
    return max(errors)