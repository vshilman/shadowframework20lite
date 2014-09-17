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

def interpolating_bezier_patch_4_4(u,v,A,AB,B,BC,C,CD,D,DA,ABCD, x1,x2,x3,x4,x5,x6 ,y1,y2,y3,y4,y5,y6, c1,c2,c3,c4):
    if u <= 0.5 and v <= 0.5:
        return interpolating_bezier_patch_2(u,v,A,AB,ABCD,DA,x1,y3,x3,y1,c1)
    elif u > 0.5 and v <= 0.5:
        return interpolating_bezier_patch_2(u,v,AB,B,BC,ABCD,x2,y5,x4,y3,c2)
    elif u <= 0.5 and v > 0.5:
        return interpolating_bezier_patch_2(u,v,DA,ABCD,CD,D,x3,y4,x5,y2,c3)
    else:
        return interpolating_bezier_patch_2(u,v,ABCD,BC,C,CD,x4,y6,x6,y4,c4)

def interpolating_bezier_patch_4_4(u,v,A,AB,B,BC,C,CD,D,DA,ABCD, y1,y2,y3,y4,y5,y6, x1,x2,x3,x4,x5,x6, c1,c2,c3,c4):
    if u <= 0.5 and v <= 0.5:
        return interpolating_bezier_patch_2(u,v,A,AB,ABCD,DA,x1,y3,x3,y1,c1)
    elif u > 0.5 and v <= 0.5:
        return interpolating_bezier_patch_2(u,v,AB,B,BC,ABCD,x2,y5,x4,y3,c2)
    elif u <= 0.5 and v > 0.5:
        return interpolating_bezier_patch_2(u,v,DA,ABCD,CD,D,x3,y4,x5,y2,c3)
    else:
        return interpolating_bezier_patch_2(u,v,ABCD,BC,C,CD,x4,y6,x6,y4,c4)

def interpolating_bezier_patch_2_tuple(uv, A,B,C,D,AB,BC,CD,DA,ABCD):
    return interpolating_bezier_patch_2(uv[0], uv[1], A,B,C,D,AB,BC,CD,DA,ABCD)

def interpolating_bezier_patch_4_4_tuple(uv,A,AB,B,BC,C,CD,D,DA,ABCD, x1,x2,x3,x4,x5,x6 ,y1,y2,y3,y4,y5,y6, c1,c2,c3,c4):
    vfunc = numpy.vectorize(interpolating_bezier_patch_4_4)
    return vfunc(uv[0],uv[1],A,AB,B,BC,C,CD,D,DA,ABCD, x1,x2,x3,x4,x5,x6 ,y1,y2,y3,y4,y5,y6, c1,c2,c3,c4)

###############################################################################
# Dynamic interpolating bezier patches
###############################################################################

def dynamic_interpolating_bezier_patches(u, v, *cpoints):
    n = sqrt(len(cpoints))
    linear_patches = (n-1) / 2
    
    cpoints_matrix = numpy.array(cpoints).reshape((n,n))
    
    u_patch_index = int(u * linear_patches)
    v_patch_index = int(v * linear_patches)
    
    A    = cpoints_matrix[u_patch_index    , v_patch_index    ]
    B    = cpoints_matrix[u_patch_index + 2, v_patch_index    ]
    C    = cpoints_matrix[u_patch_index + 2, v_patch_index + 2]
    D    = cpoints_matrix[u_patch_index    , v_patch_index + 2]
    AB   = cpoints_matrix[u_patch_index + 1, v_patch_index    ]
    BC   = cpoints_matrix[u_patch_index + 2, v_patch_index + 1]
    CD   = cpoints_matrix[u_patch_index + 1, v_patch_index + 2]
    DA   = cpoints_matrix[u_patch_index    , v_patch_index + 1]
    ABCD = cpoints_matrix[u_patch_index + 1, v_patch_index + 1]
    
    return interpolating_bezier_patch_2(u,v,A,B,C,D,AB,BC,CD,DA,ABCD)

def dynamic_interpolating_bezier_patches_tuple(uv, *points):
    vfunc = numpy.vectorize(dynamic_interpolating_bezier_patches)
    return vfunc(uv[0], uv[1], *points)

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

import scipy.optimize as opt
import geometry as geo

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

def _fit_bezier_patch_funcs(points, bezier_funcs, p0=None):
    """Fit bezier patch. Assumes x y z functions are different"""
    #TODO Maybe there is a better way but at the moment we stick with this.
    us = numpy.linspace(0.0, 1.0, sqrt(len(points)))
    vs = numpy.linspace(0.0, 1.0, sqrt(len(points)))
    uvpoints = [(u,v) for u in us for v in vs]

    uvs = numpy.array([[p[0] for p in uvpoints], [p[1] for p in uvpoints]])

    pointsx = numpy.array([p[0] for p in points])
    pointsy = numpy.array([p[1] for p in points])
    pointsz = numpy.array([p[2] for p in points])

    ##Actual fitting
    xs, pcovx = opt.curve_fit(bezier_funcs[0], uvs, pointsx, p0)
    ys, pcovy = opt.curve_fit(bezier_funcs[1], uvs, pointsy, p0)
    zs, pcovz = opt.curve_fit(bezier_funcs[2], uvs, pointsz, p0)

    #temp = list(map(lambda x: numpy.sqrt(numpy.diag(x)), [xs, ys, zs]))

    return list(zip(xs, ys, zs))

def fit_bezier_patch(points, bezier_func, p0=None):
    '''Fit the following points with the following function. p0 allows definition of inital values'''
    return _fit_bezier_patch_funcs(points, [bezier_func, bezier_func, bezier_func], p0)

def high_degree_fitting_n(n, points):
    """With n squared control points"""
    params = fit_bezier_patch(points, dynamic_interpolating_bezier_patches_tuple, [0.0] * n)
    return params


def high_degree_fitting2(points):
    params = fit_bezier_patch(points, dynamic_interpolating_bezier_patches_tuple, [0.0] * 25)
    A,x1,AB,x2,B,y1,c1,y3,c2,y5,DA,x3,ABCD,x4,BC,y2,c3,y4,c4,y6,D,x5,CD,x6,C = params
    return [[A,AB,ABCD,DA,x1,y3,x3,y1,c1],
            [AB,B,BC,ABCD,x2,y5,x4,y3,c2],
            [DA,ABCD,CD,D,x3,y4,x5,y2,c3],
            [ABCD,BC,C,CD,x4,y6,x6,y4,c4]]

#def high_degree_fitting2(points):
    #params = fit_bezier_patch(points, interpolating_bezier_patch_4_4_tuple, list(numpy.random.rand(25)))
    #A,AB,B,BC,C,CD,D,DA,ABCD, x1,x2,x3,x4,x5,x6 ,y1,y2,y3,y4,y5,y6, c1,c2,c3,c4 = params
    #return [[A,AB,ABCD,DA,x1,y3,x3,y1,c1],
            #[AB,B,BC,ABCD,x2,y5,x4,y3,c2],
            #[DA,ABCD,CD,D,x3,y4,x5,y2,c3],
            #[ABCD,BC,C,CD,x4,y6,x6,y4,c4]]

def high_degree_fitting(points):
    #TODO this sorta works but there are still some stitching problems.
    simple_fitting_verts = geo.list_to_verts(fit_bezier_patch(points, interpolating_bezier_patch_2_tuple))
    A,B,C,D,AB,BC,CD,DA,ABCD = simple_fitting_verts
    
    #Computer error in a magical way. Split the results into the four quads
    
    avgx = sum((v[0] for v in points)) / len(points)
    avgy = sum((v[1] for v in points)) / len(points)
    
    #Quad1
    q1_bezier_funcs = [
        lambda uv, a,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, a,AB[0],ABCD[0],DA[0],ab,bc,cd,da,abcd),
        lambda uv, a,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, a,AB[1],ABCD[1],DA[1],ab,bc,cd,da,abcd),
        lambda uv, a,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, a,AB[2],ABCD[2],DA[2],ab,bc,cd,da,abcd)
    ]
        
    q1_points = list(filter(lambda x: x[0] >= avgx and x[1] <= avgy, points))
    a, ab, bc, cd, da, abcd = geo.list_to_verts(_fit_bezier_patch_funcs(q1_points, q1_bezier_funcs))
    q1_final_cpoints = [a,AB,ABCD,DA,ab,bc,cd,da,abcd]
    
    #Quad2
    q2_bezier_funcs = [
        lambda uv, b,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, AB[0],b,BC[0],ABCD[0],ab,bc,cd,da,abcd),
        lambda uv, b,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, AB[1],b,BC[1],ABCD[1],ab,bc,cd,da,abcd),
        lambda uv, b,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, AB[2],b,BC[2],ABCD[2],ab,bc,cd,da,abcd)
    ]
    q2_points = list(filter(lambda x: x[0] <= avgx and x[1] <= avgy, points))
    b, ab, bc, cd, da, abcd = geo.list_to_verts(_fit_bezier_patch_funcs(q2_points, q2_bezier_funcs))
    q2_final_cpoints = [AB,b,BC,ABCD,ab,bc,cd,da,abcd]
    
    ##Quad3
    q3_bezier_funcs = [
        lambda uv, c,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, ABCD[0],BC[0],c,CD[0],ab,bc,cd,da,abcd),
        lambda uv, c,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, ABCD[1],BC[1],c,CD[1],ab,bc,cd,da,abcd),
        lambda uv, c,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, ABCD[2],BC[2],c,CD[2],ab,bc,cd,da,abcd)
    ]
    q3_points = list(filter(lambda x: x[0] <= avgx and x[1] >= avgy, points))
    c, ab, bc, cd, da, abcd = geo.list_to_verts(_fit_bezier_patch_funcs(q3_points, q3_bezier_funcs))
    q3_final_cpoints = [ABCD,BC,c,CD,ab,bc,cd,da,abcd]
    
    ##Quad4
    q4_bezier_funcs = [
        lambda uv, d,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, DA[0],ABCD[0],CD[0],d,ab,bc,cd,da,abcd),
        lambda uv, d,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, DA[1],ABCD[1],CD[1],d,ab,bc,cd,da,abcd),
        lambda uv, d,ab,bc,cd,da,abcd: interpolating_bezier_patch_2_tuple(uv, DA[2],ABCD[2],CD[2],d,ab,bc,cd,da,abcd)
    ]
    q4_points = list(filter(lambda x: x[0] >= avgx and x[1] >= avgy, points))
    d, ab, bc, cd, da, abcd = geo.list_to_verts(_fit_bezier_patch_funcs(q4_points, q4_bezier_funcs))
    q4_final_cpoints = [DA,ABCD,CD,d,ab,bc,cd,da,abcd]
    
    return [q1_final_cpoints, q2_final_cpoints, q3_final_cpoints, q4_final_cpoints]