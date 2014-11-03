import math
import scipy

'''This class contains the mathematical foundations of bezier curves and patches.
The bernstein polynomials.'''

def __bernstein_poly(i, n):
    '''Returns the bernstein poly of degree n in position i.'''
    return lambda t: scipy.misc.comb(n, i) * ((1-t)**(n - i)) * (t)**i

def bernstein_poly(n):
    '''Retunrns a list of function representing all the Bernstein monomials.'''
    return [__bernstein_poly(i, n-1) for i in range(n)]

def bernstein_poly_2D(n):
    '''Returns a list representing all the 2D Bernstein monomials.'''
    return [(__bernstein_poly(i, n-1), __bernstein_poly(j, n-1)) for i in range(n) for j in range(n)]

def bezier_curve_n(params):
    '''Returns the function of the bezier curve using the provided parameters as control points.
    This is the most generic definition of a curve.'''
    poly = list(zip(bernstein_poly(len(params)), params))
    return lambda t: sum(Pi * Bi(t) for Bi, Pi in poly)

def bezier_patch_n(params):
    '''Returns the function of the bezier patch using the provided parameters as control points.
    This is the most generic definition of a patch.'''
    poly = list(zip(bernstein_poly_2D(math.sqrt(len(params))), params))
    return lambda u,v: sum(Pi * Bi[0](u) * Bi[1](v) for Bi, Pi in poly)