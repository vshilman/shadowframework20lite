import numpy
from scipy.misc import comb
from funchelps import *
from itertools import product
import inspect #Used to retrieve the number of parameters of a function
from random import randint

###############################################################################
# Function constructors
###############################################################################

def __bernstein_poly(i, n):
    '''Returns the bernstein poly of degree n in position i.'''
    return lambda t: comb(n, i) * ((1-t)**(n - i)) * (t)**i

def bernstein_poly(n):
    return [__bernstein_poly(i, n-1) for i in range(n)]

def bernstein_poly_2D(n):
    return [(__bernstein_poly(i, n-1), __bernstein_poly(j, n-1)) for i in range(n) for j in range(n)]

def bezier_curve_n(params):
    '''Returns a bezier curve with degree len(params) using those controls points.'''
    poly = list(zip(bernstein_poly(len(params)), params))
    return lambda t: sum(Pi * Bi(t) for Bi, Pi in poly)

def bezier_patch_n(params):
    poly = list(zip(bernstein_poly_2D(isqrt(len(params))), params))
    return lambda u,v: sum(Pi * Bi[0](u) * Bi[1](v) for Bi, Pi in poly)

###############################################################################
# Bezier function
###############################################################################

def bezier_curve_1(t, A, B):
    return (1-t) * A + t * B

def bezier_curve_2(t, A, AB, B):
    return (1-t)**2 * A + 2*(1-t)*t * AB + t**2 * B

def bezier_curve_3(t, A, B, C, D):
    return (1-t)**3 * A + 3*(1-t)**2 * t * B + 3*(1-t)*t**2 * C + t**3 * D

def bezier_patch_1(u, v, A, B, C, D):
    um=1-u
    vm=1-v
    return vm*um*A+vm*u*B+v*um*D+u*v*C

def bezier_patch_1_tuple(uv, A, B, C, D):
    return bezier_patch_1(uv[0], uv[1], A, B, C, D)

def bezier_patch_2(u, v, A,B,C,D,AB,BC,CD,DA,ABCD):
    um=1-u
    vm=1-v
    return vm*vm*um*um*A+2*vm*vm*um*u*AB+vm*vm*u*u*B+2*v*vm*um*um*DA+4*v*vm*um*u*ABCD+2*v*vm*u*u*BC+v*v*um*um*D+2*v*v*um*u*CD+v*v*u*u*C

def bezier_patch_2_tuple(uv, A,B,C,D,AB,BC,CD,DA,ABCD):
    return bezier_patch_2(uv[0], uv[1], A,B,C,D,AB,BC,CD,DA,ABCD)


###############################################################################
# Interpolating bezier functions
###############################################################################

def interpolating_bezier_patch_2(u, v, A,B,C,D,AB,BC,CD,DA,ABCD):
    um=1.0-u
    vm=1.0-v
    Acf = um*um*vm*vm-um*u*vm*vm-vm*v*um*um-0.5*um*u*vm*v
    Bcf = u*u*vm*vm-um*u*vm*vm-vm*v*u*u-0.5*um*u*vm*v
    Ccf = u*u*v*v-um*u*v*v-vm*v*u*u-0.5*um*u*vm*v
    Dcf = um*um*v*v-um*u*v*v-vm*v*um*um-0.5*um*u*vm*v
    ABcf = 4.0*um*u*vm*vm-um*u*vm*v
    BCcf = 4.0*vm*v*u*u-um*u*vm*v
    CDcf = 4.0*um*u*v*v-um*u*vm*v
    DAcf = 4.0*vm*v*um*um-um*u*vm*v
    ABCDcf = 10.0*um*u*vm*v
    return (A*Acf)+(B*Bcf)+(C*Ccf)+(D*Dcf)+(AB*ABcf)+(BC*BCcf)+(CD*CDcf)+(DA*DAcf)+(ABCD*ABCDcf)

def interpolating_bezier_patch_2_tuple(uv, A,B,C,D,AB,BC,CD,DA,ABCD):
    return interpolating_bezier_patch_2(uv[0], uv[1], A,B,C,D,AB,BC,CD,DA,ABCD)

###############################################################################
# Bezier function compilers
###############################################################################

def compile_bezier_curve_1(A, B):
    return lambda t: bezier_curve_1(t, A, B)

def compile_bezier_curve_2(A, AB, B):
    return lambda t: bezier_curve_2(t, A, AB, B)

def compile_bezier_curve_3(A,B,C,D):
    return lambda t: bezier_curve_3(t, A, B, C, D)

def compile_bezier_patch_1(A,B,C,D):
    return lambda u,v: bezier_patch_1(u, v, A, B, C, D)

def compile_bezier_patch_2(A,B,C,D,AB,BC,CD,DA,ABCD):
    return lambda u,v: bezier_patch_2(u, v, A,B,C,D,AB,BC,CD,DA,ABCD)

def compile_interpolating_bezier_patch_2(A,B,C,D,AB,BC,CD,DA,ABCD):
    return lambda u,v: interpolating_bezier_patch_2(u, v, A,B,C,D,AB,BC,CD,DA,ABCD)

def compile_bezier_curve(params):
    return globals()["compile_bezier_curve_" + str(len(params) - 1)](*params)

def compile_bezier_patch(params):
    #TODO Terrible solution
    degree = {4: 1, 9: 2}[len(params)]
    return globals()["compile_bezier_patch_" + str(degree)](*params)

def compile_interpolating_bezier_patch(params):
    #TODO Terrible solution
    degree = {4: 1, 9: 2}[len(params)]
    return globals()["compile_interpolating_bezier_patch_" + str(degree)](*params)

###############################################################################
# Sample functions
###############################################################################

def sample_func(f, step):
    '''Sample the given function with the given step. Returns a generator'''
    return (f(t) for t in float_range(0.0, 1.0, step))

def sample_func_2D(f, step):
    us = list(float_range(0.0, 1.0, step))
    vs = list(float_range(0.0, 1.0, step))
    return [f(u,v) for u in us for v in vs]

###############################################################################
# Fitting
###############################################################################

from geometry import Vertex
import scipy.optimize as opt

def fit_bezier_curve(points, bezier_func):
    ts = numpy.array(list(float_range(0.0, 1.0, 1.0 / (len(points) - 1))))

    pointsx = numpy.array([p[0] for p in points])
    pointsy = numpy.array([p[1] for p in points])
    pointsz = numpy.array([p[2] for p in points])

    ##Actual fitting
    xs, box = opt.curve_fit(bezier_func, ts, pointsx)
    ys, boy = opt.curve_fit(bezier_func, ts, pointsy)
    zs, boz = opt.curve_fit(bezier_func, ts, pointsz)

    return list(zip(xs, ys, zs))

def fit_bezier_patch(points, bezier_func):
    #TODO Maybe there is a better way but at the moment we stick with this.
    us = numpy.linspace(0.0, 1.0, sqrt(len(points)))
    vs = numpy.linspace(0.0, 1.0, sqrt(len(points)))
    uvpoints = [(u,v) for u in us for v in vs]
    #print(uvpoints)
    uvs = numpy.array([[p[0] for p in uvpoints], [p[1] for p in uvpoints]])

    pointsx = numpy.array([p[0] for p in points])
    pointsy = numpy.array([p[1] for p in points])
    pointsz = numpy.array([p[2] for p in points])

    n_params = len(inspect.getargspec(bezier_func).args) - 1

    ##Actual fitting
    xs, box = opt.curve_fit(bezier_func, uvs, pointsx, p0=[randint(10000,13212312)] * n_params)
    ys, boy = opt.curve_fit(bezier_func, uvs, pointsy, p0=[randint(10000,13212312)] * n_params)
    zs, boz = opt.curve_fit(bezier_func, uvs, pointsz, p0=[randint(10000,13212312)] * n_params)

    return list(zip(xs, ys, zs))