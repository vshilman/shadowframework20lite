#include "SFTransform3f.h"


namespace sf{

SFTransform3f::SFTransform3f(){
    for(int i=0;i<12;i++)
        v[i]=0;
    v[0]=1;
    v[4]=1;
    v[8]=1;
}

//SFValuenf* SFTransform3f::cloneValue() {
//    SFTransform3f* transform = new SFTransform3f();
//    transform->set(this);
//    return transform;
//}

    inline int SFTransform3f::getSize(){
        return 12;
    }
    
    inline float* SFTransform3f::getV(){
        return v;
    }
    
void SFTransform3f::setPosition(float x, float y, float z) {
    v[9] = x;
    v[10] = y;
    v[11] = z;
}

void SFTransform3f::setMatrix(float a, float b, float c, float d, float e, float f,
        float g, float h, float i) {
    v[0] = a;
    v[1] = b;
    v[2] = c;
    v[3] = d;
    v[4] = e;
    v[5] = f;
    v[6] = g;
    v[7] = h;
    v[8] = i;
}

void SFTransform3f::setMatrix(SFMatrix3f matrix) {
    for (int i = 0; i < matrix.getSize(); i++) {
        this->v[i] = matrix.getV()[i];
    }
}

void SFTransform3f::setPosition(SFVertex3f position) {
    for (int i = 0; i < position.getSize(); i++) {
        this->v[i + 9] = position.getV()[i];
    }
}

void SFTransform3f::getMatrix(SFMatrix3f* matrix) {
    for (int i = 0; i < matrix->getSize(); i++) {
        matrix->getV()[i] = this->v[i];
    }
}

void SFTransform3f::getPosition(SFVertex3f* position) {
    for (int i = 0; i < position->getSize(); i++) {
        position->getV()[i] = this->v[i + 9];
    }
}


void SFTransform3f::transform(SFValue* position) {
    float x=position->getV()[0];
    float y=position->getV()[1];
    float z=position->getV()[2];
    position->getV()[0]=x*v[0]+y*v[1]+z*v[2]+v[9];
    position->getV()[1]=x*v[3]+y*v[4]+z*v[5]+v[10];
    position->getV()[2]=x*v[6]+y*v[7]+z*v[8]+v[11];
}

void SFTransform3f::transformDir(SFValue* dir) {
    float x=dir->getV()[0];
    float y=dir->getV()[1];
    float z=dir->getV()[2];
    dir->getV()[0]=x*v[0]+y*v[1]+z*v[2];
    dir->getV()[1]=x*v[3]+y*v[4]+z*v[5];
    dir->getV()[2]=x*v[6]+y*v[7]+z*v[8];
}

void SFTransform3f::mult(SFTransform3f transform){

    float multTmpVal[12];
    float* val=transform.v;

    multTmpVal[0]=v[0]*val[0]+v[1]*val[3]+v[2]*val[6];
    multTmpVal[1]=v[0]*val[1]+v[1]*val[4]+v[2]*val[7];
    multTmpVal[2]=v[0]*val[2]+v[1]*val[5]+v[2]*val[8];

    multTmpVal[3]=v[3]*val[0]+v[4]*val[3]+v[5]*val[6];
    multTmpVal[4]=v[3]*val[1]+v[4]*val[4]+v[5]*val[7];
    multTmpVal[5]=v[3]*val[2]+v[4]*val[5]+v[5]*val[8];

    multTmpVal[6]=v[6]*val[0]+v[7]*val[3]+v[8]*val[6];
    multTmpVal[7]=v[6]*val[1]+v[7]*val[4]+v[8]*val[7];
    multTmpVal[8]=v[6]*val[2]+v[7]*val[5]+v[8]*val[8];

    multTmpVal[9]=v[0]*val[9]+v[1]*val[10]+v[2]*val[11]+v[9];
    multTmpVal[10]=v[3]*val[9]+v[4]*val[10]+v[5]*val[11]+v[10];
    multTmpVal[11]=v[6]*val[9]+v[7]*val[10]+v[8]*val[11]+v[11];

    for (int i = 0; i < 12; i++) {
        this->v[i]=multTmpVal[i];
    }
}

}
