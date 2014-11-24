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

def first(seq, cond):
    '''Returns the first element of seq which satisfies cond.'''
    return next(x for x in seq if cond(x))

def remove_from_list(l, elem):
    '''Remove elem from list. Returns a copy of the list.'''
    temp = list(l)
    temp.remove(elem)
    return temp

def contains_one(s1, s2):
    '''Retuns true if at least one element of s1 is in s2'''
    return any(elem in s2 for elem in s1)