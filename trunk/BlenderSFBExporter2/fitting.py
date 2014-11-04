import numpy as np
import scipy.optimize as opt
from math import sqrt

def fit_bezier_curve(points, bezier_func):
    '''Fit the selected points with '''
    ts = np.linspace(0.0, 1.0, len(points) - 1)

    pointsx = np.array([p[0] for p in points])
    pointsy = np.array([p[1] for p in points])
    pointsz = np.array([p[2] for p in points])

    ##Actual fitting
    xs, box = opt.curve_fit(bezier_func, ts, pointsx)
    ys, boy = opt.curve_fit(bezier_func, ts, pointsy)
    zs, boz = opt.curve_fit(bezier_func, ts, pointsz)

    return list(zip(xs, ys, zs))

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
    chunks = split_list(points, len(points) // n)
    return (fit_bezier_curve(chunk, bezier_func) for chunk in chunks)
    

def _fit_bezier_patch_funcs(points, bezier_funcs, p0=None):
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
    return _fit_bezier_patch_funcs(points, [bezier_func, bezier_func, bezier_func], p0)