#ifndef SFRIGIDTRANSFORM2F_H
#define SFRIGIDTRANSFORM2F_H

#include "SFValue.h"
#include "SFMatrix2f.h"
#include "SFVertex2f.h"

namespace sf{

class SFTransform2f:public SFValue{

    float v[6];
    
public:
    SFTransform2f();

    //SFValuenf* cloneValue();
    
    int getSize();
    
    float* getV();

    void setPosition(float x,float y);

    void setMatrix(float a,float b,float c,float d);

    void setMatrix(SFMatrix2f matrix);

    void setPosition(SFVertex2f position);

    void getMatrix(SFMatrix2f* matrix);

    void getPosition(SFVertex2f* position);
};
}

#endif // SFRIGIDTRANSFORM2F_H
