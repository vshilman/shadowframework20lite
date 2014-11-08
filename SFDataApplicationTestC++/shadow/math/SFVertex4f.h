#ifndef SFVERTEX4F_H
#define SFVERTEX4F_H

#include "SFVertex3f.h"
#include "SFValue.h"

namespace sf{

class SFVertex4f:public SFValue{

    float v[4];
    
public:
    SFVertex4f();

    SFVertex4f(float x, float y, float z, float w);

    static SFVertex4f* middle(SFVertex4f A,SFVertex4f B);
    
    int getSize();
    
    float* getV();

    void add4f(SFVertex4f q);

    void addMult4f(float a,SFVertex4f vx);

    double dot4f(SFVertex4f vx);

    //SFValuenf* cloneValue();

    float getX();

    float getY();

    float getZ();

    float getW();

    void mult4f(float a);

    void normalize4f();

    void scale4f(float sx,float sy,float sz,float sw);

    void set4f(float x,float y,float z,float w);

    void setX(float x);

    void setY(float y);

    void setZ(float z);

    void setW(float w);

    void subtract4f(SFVertex4f q);

};

}

#endif // SFVERTEX4F_H
