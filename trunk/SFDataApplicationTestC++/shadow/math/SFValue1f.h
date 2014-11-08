#ifndef SFVALUE1F_H
#define SFVALUE1F_H

#include "SFValue.h"


namespace sf{

class SFValue1f:public SFValue{

    float v[1];
    
public:
    SFValue1f();
    
    SFValue1f(float x);

    SFValue1f(double x);
    
    int getSize(){
        return 1;
    }
    
    float* getV(){
        return v;
    }
    
    void add1f(float dX);

    //SFValuenf* cloneValue();

    float getX();

    void mult1f(float m);

    void setX(float x);

    void subtract1f(float dX);
};

}

#endif // SFVALUE1F_H
