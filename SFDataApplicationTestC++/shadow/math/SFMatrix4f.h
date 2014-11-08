#ifndef SFMATRIX4F_H
#define SFMATRIX4F_H

#include "SFValue.h"

namespace sf{

class SFMatrix4f:public SFValue{
    
    float v[16];
    
public:
    //SFValuenf* cloneValue();
    SFMatrix4f(){
        
    }
    
    int getSize();
    
    float* getV();

    SFMatrix4f(float a,float b,float c,float d,
                    float e,float f,float g,float h,
                    float i,float l,float m,float n,
               float o,float p,float q,float r);

    static SFMatrix4f getIdentity();
};

}

#endif // SFMATRIX4F_H
