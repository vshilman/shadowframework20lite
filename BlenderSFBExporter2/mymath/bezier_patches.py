import numpy as np

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
    vfunc = np.vectorize(interpolating_bezier_patch_4_4)
    return vfunc(uv[0],uv[1],A,AB,B,BC,C,CD,D,DA,ABCD, x1,x2,x3,x4,x5,x6 ,y1,y2,y3,y4,y5,y6, c1,c2,c3,c4)

# Compile bezier patches ######################################################

def compile_bezier_patch_1(A,B,C,D):
    return lambda u,v: bezier_patch_1(u, v, A, B, C, D)

def compile_bezier_patch_2(A,B,C,D,AB,BC,CD,DA,ABCD):
    return lambda u,v: bezier_patch_2(u, v, A,B,C,D,AB,BC,CD,DA,ABCD)

def compile_interpolating_bezier_patch_2(A,B,C,D,AB,BC,CD,DA,ABCD):
    return lambda u,v: interpolating_bezier_patch_2(u, v, A,B,C,D,AB,BC,CD,DA,ABCD)

def compile_bezier_patch(params):
    #TODO Terrible solution
    degree = {4: 1, 9: 2}[len(params)]
    return globals()["compile_bezier_patch_" + str(degree)](*params)

def compile_interpolating_bezier_patch(params):
    #TODO Terrible solution
    degree = {4: 1, 9: 2}[len(params)]
    return globals()["compile_interpolating_bezier_patch_" + str(degree)](*params)
