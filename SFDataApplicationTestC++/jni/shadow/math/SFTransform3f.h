#ifndef SFTRANSFORM3F_H
#define SFTRANSFORM3F_H

#include "SFValue.h"
#include "SFVertex3f.h"
#include "SFMatrix3f.h"
#include "../system/SFInitiable.h"

namespace sf{

class SFTransform3f:public SFValue{

    float v[12];
    
public:
    SFTransform3f();

    //SFValuenf* cloneValue();

    int getSize();
    
    float* getV();
    
    void setPosition(float x, float y, float z);

    void setMatrix(float a, float b, float c, float d, float e, float f,
            float g, float h, float i);

    void setMatrix(SFMatrix3f matrix);

    void setPosition(SFVertex3f position);

    void getMatrix(SFMatrix3f* matrix);

    void getPosition(SFVertex3f* position);

    void transform(SFValue* position);

    void transformDir(SFValue* dir);

    void mult(SFTransform3f transform);

};

}

#endif // SFTRANSFORM3F_H
