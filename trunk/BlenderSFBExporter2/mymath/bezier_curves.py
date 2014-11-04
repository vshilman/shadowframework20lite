
'''This class contains all the mathematica descriptions of the bezier curves.'''

def bezier_curve_1(t, A, B):
    return (1-t) * A + t * B

def bezier_curve_2(t, A, AB, B):
    return (1-t)**2 * A + 2*(1-t)*t * AB + t**2 * B

def bezier_curve_3(t, A, B, C, D):
    return (1-t)**3 * A + 3*(1-t)**2 * t * B + 3*(1-t)*t**2 * C + t**3 * D


# Compile functions ###########################################################

def compile_bezier_curve_1(A, B):
    return lambda t: bezier_curve_1(t, A, B)

def compile_bezier_curve_2(A, AB, B):
    return lambda t: bezier_curve_2(t, A, AB, B)

def compile_bezier_curve_3(A,B,C,D):
    return lambda t: bezier_curve_3(t, A, B, C, D)

def compile_bezier_curve(params):
    assert(len(params) <= 4), "Bezier curve with more than 4 parameters are not supported at the moment."
    return globals()["compile_bezier_curve_" + str(len(params) - 1)](*params)