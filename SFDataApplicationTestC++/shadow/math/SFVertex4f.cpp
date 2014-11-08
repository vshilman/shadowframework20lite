#include "SFVertex4f.h"

namespace sf{

    SFVertex4f::SFVertex4f(float x, float y, float z, float w){
     this->set4f(x,y,z,w);
    }


    SFVertex4f::SFVertex4f(){
        
    }

SFVertex4f* SFVertex4f::middle(SFVertex4f A,SFVertex4f B){
    return new SFVertex4f(
            (A.v[0]+B.v[0])*0.5f,(A.v[1]+B.v[1])*0.5f,(A.v[2]+B.v[2])*0.5f,(A.v[3]+B.v[3])*0.5f
    );
}
    
    
    inline int SFVertex4f::getSize(){
        return 4;
    }
    
    inline float* SFVertex4f::getV(){
        return v;
    }
    

void SFVertex4f::add4f(SFVertex4f q){
    v[0]+=q.v[0];
    v[1]+=q.v[1];
    v[2]+=q.v[2];
    v[3]+=q.v[3];
}

void SFVertex4f::addMult4f(float a,SFVertex4f vx){
    v[0]+=vx.v[0]*a;
    v[1]+=vx.v[1]*a;
    v[2]+=vx.v[2]*a;
    v[3]+=vx.v[3]*a;
}

//SFValue* SFVertex4f::cloneValue() {
//    return new SFVertex4f(v[0],v[1],v[2],v[3]);
//}

double SFVertex4f::dot4f(SFVertex4f vx){
    return vx.v[0]*v[0]+vx.v[1]*v[1]+vx.v[2]*v[2]+vx.v[3]*v[3];
}

float SFVertex4f::getX(){
    return v[0];
}

float SFVertex4f::getY(){
    return v[1];
}

float SFVertex4f::getZ(){
    return v[2];
}

float SFVertex4f::getW(){
    return v[3];
}

void SFVertex4f::mult4f(float a){
    v[0]*=a;
    v[1]*=a;
    v[2]*=a;
    v[3]*=a;
}


void SFVertex4f::setX(float x){
    v[0]=x;
}

void SFVertex4f::setY(float y){
    v[1]=y;
}

void SFVertex4f::setZ(float z){
    v[2]=z;
}

void SFVertex4f::setW(float w){
    v[3]=w;
}

void SFVertex4f::scale4f(float sx,float sy,float sz,float sw){
    this->v[0]*=sx;
    this->v[1]*=sy;
    this->v[2]*=sz;
    this->v[3]*=sw;
}


void SFVertex4f::set4f(float x,float y,float z,float w){
    this->v[0]=x;
    this->v[1]=y;
    this->v[2]=z;
    this->v[3]=w;
}


void SFVertex4f::normalize4f(){
    float lengthRec=1/(float)(sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]+v[3]*v[3]));
    v[0]*=lengthRec;
    v[1]*=lengthRec;
    v[2]*=lengthRec;
    v[3]*=lengthRec;
}


void SFVertex4f::subtract4f(SFVertex4f q){
    v[0]-=q.v[0];
    v[1]-=q.v[1];
    v[2]-=q.v[2];
    v[3]-=q.v[3];
}

}
