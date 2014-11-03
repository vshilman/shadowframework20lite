import numpy as np

'''
Those are the function used to sample curves and patches.
Curve is supposed to implement the t method, while pathces
have the implement the uv method.
'''

def sample_curve_samples(curve, n):
    return (curve.t(x) for x in np.linspace(0.0, 1.0, n))

def sample_curve_step(curve, step):
    return sample_curve_samples(curve, 1.0 / step)

def sample_patch_samples(patch, n):
    return (patch.uv(u,v) for u in np.linspace(0.0, 1.0, n)
                          for v in np.linspace(0.0, 1.0, n))

def sample_patch_step(patch, step):
    return sample_patch_samples(patch, 1.0 / step)