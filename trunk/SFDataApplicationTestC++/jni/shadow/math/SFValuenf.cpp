
#include "SFValuenf.h"


namespace sf{

SFValuenf::SFValuenf() {
	this->v = (float*)0;
	this->length=0;
}

SFValuenf::SFValuenf(int n) {
    this->v = new float[n];
    this->length=n;
}

SFValuenf::SFValuenf(float* v,int length) {
    this->v = v;
    this->length=length;
}

SFValuenf* SFValuenf::middle(SFValuenf A,SFValuenf B){
    SFValuenf* value=new SFValuenf(A.length);
    for (int i = 0; i < A.length; i++) {
        value->v[i]=(A.v[i]+B.v[i])*0.5f;
    }
    return value;
}

float* SFValuenf::getV() {
    return v;
}

int SFValuenf::getSize(){
    return length;
}


}


