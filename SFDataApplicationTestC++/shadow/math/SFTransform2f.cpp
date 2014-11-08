#include "SFTransform2f.h"



namespace sf{

SFTransform2f::SFTransform2f(){
}

//SFValuenf* SFTransform2f::cloneValue() {
//	SFTransform2f* transform=new SFTransform2f();
//    transform->set(this);
//    return transform;
//}
    
    
    inline int SFTransform2f::getSize(){
        return 6;
    }
    
    inline float* SFTransform2f::getV(){
        return this->v;
    }


void SFTransform2f::setPosition(float x,float y){
    v[4]=x;
    v[5]=y;
}

void SFTransform2f::setMatrix(float a,float b,float c,float d){
    v[0]=a;
    v[1]=b;
    v[2]=c;
    v[3]=d;
}

void SFTransform2f::setMatrix(SFMatrix2f matrix){
    for (int i = 0; i < matrix.getSize(); i++) {
        this->v[i]=matrix.getV()[i];
    }
}

void SFTransform2f::setPosition(SFVertex2f position){
    for (int i = 0; i < position.getSize(); i++) {
        this->v[i+4]=position.getV()[i];
    }
}

void SFTransform2f::getMatrix(SFMatrix2f* matrix){
    for (int i = 0; i < matrix->getSize(); i++) {
        matrix->getV()[i]=this->v[i];
    }
}

void SFTransform2f::getPosition(SFVertex2f* position){
    for (int i = 0; i < position->getSize(); i++) {
        position->getV()[i]=this->v[i+4];
    }
}

}
