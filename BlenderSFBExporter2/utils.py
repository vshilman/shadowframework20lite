import inspect

'''This functions provides a series of utilities for the main package'''

def lint(A, B, t):
    '''Simple linear interpolation.'''
    return (1 - t) * A + t * B

def get_number_arguments(f):
    args, varargs, varkw, defaults = inspect.getargspec(f)
    if len(args) < 2:
        msg = "Unable to determine number of fit parameters."
        raise ValueError(msg)
    if 'self' in args:
        return (len(args)-2)
    else:
        return (len(args)-1)