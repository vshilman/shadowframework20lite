import numpy as np
import scipy.optimize as opt
from math import sqrt
import utils

import inspect

VERBOSE = False

def __fit_bezier_curve_funcs(points, bezier_funcs, p0s=[None,None,None]):
    '''This functions fits the points with one function for each dimension.
    it is not exported to be used.'''
    ts = np.linspace(0.0, 1.0, len(points))

    pointsx = np.array([p[0] for p in points])
    pointsy = np.array([p[1] for p in points])
    pointsz = np.array([p[2] for p in points])

    error_msgs = [None] * 3

    ##Actual fitting
    xs, box, infodict, error_msgs[0], ier = opt.curve_fit(bezier_funcs[0], ts, pointsx, p0s[0], full_output = True)
    ys, boy, infodict, error_msgs[1], ier = opt.curve_fit(bezier_funcs[1], ts, pointsy, p0s[1], full_output = True)
    zs, boz, infodict, error_msgs[2], ier = opt.curve_fit(bezier_funcs[2], ts, pointsz, p0s[2], full_output = True)

    # Error checking
    if VERBOSE:
        for e in error_msgs:
            if e:
                print(e)

    return list(zip(xs, ys, zs))

def fit_bezier_curve(points, bezier_func, p0=None):
    '''Fit the selected points with given bezier function.'''
    return __fit_bezier_curve_funcs(points, [bezier_func] * 3, [p0, p0, p0])
    
def split_list(l, n):
    '''Split the list l into chunks with a size of maximum n. The first element
    of the list n equals the last of the list'''
    for i in range(0, len(l), n):
        start = max(0, i-1)
        end = i+n
        yield l[start:end]

def fit_bezier_spline(points, bezier_func, n):
    '''Fit the current points with a spline made by the following bezier function.
    n representes the number of bezier function to be used.'''
    
    n_params = utils.get_number_arguments(bezier_func) - 2
    chunks = list(split_list(points, len(points) // n))
            
    cpoints = []
    for chunk in chunks:
        # We force the passage from the first and last point to grant continuity.
        first_knot = tuple(chunk[0])
        last_knot  = tuple(chunk[-1])
        shadow_functions = [lambda t, *params: bezier_func(t, *((first_knot[0],) + params + (last_knot[0],))),
                            lambda t, *params: bezier_func(t, *((first_knot[1],) + params + (last_knot[1],))),
                            lambda t, *params: bezier_func(t, *((first_knot[2],) + params + (last_knot[2],)))]
        estimated_cpoints = __fit_bezier_curve_funcs(chunk, shadow_functions, [[1.0] * n_params, [1.0] * n_params, [1.0] * n_params])
        cpoints += [[first_knot] + estimated_cpoints + [last_knot]]
    
    return cpoints
    

def __fit_bezier_patch_funcs(points, bezier_funcs, p0=None):
    """Fit bezier patch. Assumes x y z functions are different"""
    #TODO Maybe there is a better way but at the moment we stick with this.
    us = np.linspace(0.0, 1.0, sqrt(len(points)))
    vs = np.linspace(0.0, 1.0, sqrt(len(points)))
    uvpoints = [(u,v) for u in us for v in vs]

    uvs = np.array([[p[0] for p in uvpoints], [p[1] for p in uvpoints]])

    pointsx = np.array([p[0] for p in points])
    pointsy = np.array([p[1] for p in points])
    pointsz = np.array([p[2] for p in points])

    ##Actual fitting
    xs, pcovx = opt.curve_fit(bezier_funcs[0], uvs, pointsx, p0)
    ys, pcovy = opt.curve_fit(bezier_funcs[1], uvs, pointsy, p0)
    zs, pcovz = opt.curve_fit(bezier_funcs[2], uvs, pointsz, p0)

    #temp = list(map(lambda x: np.sqrt(np.diag(x)), [xs, ys, zs]))

    return list(zip(xs, ys, zs))

def fit_bezier_patch(points, bezier_func, p0=None):
    '''Fit the following points with the following function. p0 allows definition of inital values'''
    return __fit_bezier_patch_funcs(points, [bezier_func, bezier_func, bezier_func], p0)