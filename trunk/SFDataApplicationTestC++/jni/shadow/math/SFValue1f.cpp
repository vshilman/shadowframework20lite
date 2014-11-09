#include "SFValue1f.h"

namespace sf{
    
SFValue1f::SFValue1f() {
   this->v[0] = 0;
}
    
SFValue1f::SFValue1f(float x) {
    this->v[0] = x;
}

SFValue1f::SFValue1f(double x){
    this->v[0] = (float)x;
}

void SFValue1f::add1f(float dX){
    v[0]+=dX;
}
//
//SFValuenf* SFValue1f::cloneValue() {
//    return new SFValue1f(this->v[0]);
//}

float SFValue1f::getX(){
    return v[0];
}

void SFValue1f::mult1f(float m){
    v[0]*=m;
}

void SFValue1f::setX(float x){
    v[0]=x;
}

void SFValue1f::subtract1f(float dX){
    v[0]-=dX;
}

}
