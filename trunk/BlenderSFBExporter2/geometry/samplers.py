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

def sample_patch_quads_step_uv(patch, stepu, stepv):
    '''Sample patch in quads using two different steps on u and v'''
    for u in np.arange(0.0, 1.0, stepu):
        for v in np.arange(0.0, 1.0, stepv):
            yield [patch.uv(u,v), patch.uv(u+stepu,v), patch.uv(u+stepu, v+stepv), patch.uv(u, v+stepv)]

def sample_patch_quads_step(patch, step):
    '''Sample the current patch and return quads.'''
    return sample_patch_quads_step_uv(patch, step, step)

def sample_patch_quads_samples_uv(patch, nu, nv):
    return sample_patch_quads_step_uv(patch, 1.0 / nu, 1.0 / nv)

def sample_patch_quads_samples(patch, n):
    return sample_patch_quads_step(patch, 1.0 / n)