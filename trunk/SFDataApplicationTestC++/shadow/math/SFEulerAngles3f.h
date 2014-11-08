#ifndef SFEULERANGLES3F_H
#define SFEULERANGLES3F_H

#include <math.h>
#include "SFVertex3f.h"
#include "SFMatrix3f.h"

namespace sf{

class SFEulerAngles3f:SFVertex3f{

    SFEulerAngles3f();

    SFEulerAngles3f(double x, double y, double z);

    SFEulerAngles3f(float x, float y, float z);

    void getMatrix(SFMatrix3f* matrix);

    void setMatrix(SFMatrix3f matrix);
};

}

#endif // SFEULERANGLES3F_H
