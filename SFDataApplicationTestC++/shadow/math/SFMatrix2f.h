#ifndef SFMATRIX2F_H
#define SFMATRIX2F_H

#include "SFValue.h"
#include "SFVertex2f.h"
#include <math.h>

namespace sf{

class SFMatrix2f:public SFValue{

    float v[4];
    
public:
    SFMatrix2f();

    SFMatrix2f(float a, float b, float c, float d);

    //SFValuenf* cloneValue();
    
    int getSize();
    
    float* getV();

    static SFMatrix2f getRotationZ(float angle);

    static SFMatrix2f getTransposed(SFMatrix2f m);

    static SFMatrix2f getIdentity();

    static SFMatrix2f getScale(float scaleX, float scaleY);

    static SFMatrix2f getInverse(SFMatrix2f m);

    void set(SFMatrix2f m);

    SFMatrix2f MultMatrix(SFMatrix2f m);

    SFVertex2f Mult(SFVertex2f p);

    void transform(SFVertex2f* p);

    float getA();

    void setA(float a);

    float getB();

    void setB(float b);

    float getC();

    void setC(float c);

    float getD();

    void setD(float d);

};
}

#endif // SFMATRIX2F_H
