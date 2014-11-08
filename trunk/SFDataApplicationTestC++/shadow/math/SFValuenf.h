#ifndef SFVALUENF_H
#define SFVALUENF_H

#include <math.h>

#include "SFValue.h" 


namespace sf{

/* SFValuenf is not going to clean its memory
 * */
    class SFValuenf: public SFValue{

protected:
    float* v;
    char length;

public:
    SFValuenf();
    
    //Note: Should be never used !! Damly dangerous!
    SFValuenf(int n);

    SFValuenf(float* v,int length);

    static SFValuenf* middle(SFValuenf A,SFValuenf B);

    virtual float* getV();

    int getSize();

};

}
#endif // SFVALUENF_H
