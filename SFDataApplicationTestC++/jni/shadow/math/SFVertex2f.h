#ifndef SFVERTEX2F_H
#define SFVERTEX2F_H

#include "SFValue.h"
#include <math.h>

namespace sf{

class SFVertex2f:public SFValue{

protected:
    float v[2];

public:

    SFVertex2f();

    SFVertex2f(float x, float y);

    SFVertex2f(double x, double y);
    
    int getSize();
    
    float* getV();

    static float getDistance(SFVertex2f v1,SFVertex2f v2);

    static SFVertex2f* middle(SFVertex2f A,SFVertex2f B);

    void add2f(SFVertex2f vx);

    void addMult2f(float a,SFVertex2f vx);

    //was deprecated
    //SFValue2f* cloneValue();

    float dot2f(SFVertex2f vx);

    float getLength();

    float getX();

    float getY();

    void mult2f(float m);

    void normalize2f();

    void scale2f(float sx,float sy);

    void set2f(float x,float y);

    void setX(float y);

    void setY(float y);

    void subtract2f(SFVertex2f vx);

};

}

#endif // SFVERTEX2F_H
