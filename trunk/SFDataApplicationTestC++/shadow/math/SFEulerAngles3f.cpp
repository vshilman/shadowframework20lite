#include "SFEulerAngles3f.h"

namespace sf{

SFEulerAngles3f::SFEulerAngles3f():SFVertex3f(0.0,0.0,0.0){
}

SFEulerAngles3f::SFEulerAngles3f(double x, double y, double z):SFVertex3f(x,y,z){
}

SFEulerAngles3f::SFEulerAngles3f(float x, float y, float z):SFVertex3f(x,y,z){
}

void SFEulerAngles3f::getMatrix(SFMatrix3f* matrix){

    float c1=(float)(cos(v[0]));
    float s1=(float)(sin(v[0]));
    float c2=(float)(cos(v[1]));
    float s2=(float)(sin(v[1]));
    float c3=(float)(cos(v[2]));
    float s3=(float)(sin(v[2]));

    matrix->setA(c1*c3-s1*s2*s3);
    matrix->setB(-c2*s1);
    matrix->setC(c1*s3+c3*s1*s2);
    matrix->setD(c3*s1+c1*s2*s3);
    matrix->setE(c1*c2);
    matrix->setF(s1*s3-c1*c3*s2);
    matrix->setG(-c2*s3);
    matrix->setH(s2);
    matrix->setI(c2*c3);

}

void SFEulerAngles3f::setMatrix(SFMatrix3f matrix){

    //Retrieve s2 from H and evaluate c2
    float s2=matrix.getH();
    float c2=(float)(sqrt(1-s2*s2));
    float c1=0,s1=0,c3=0,s3=0;

    v[1]=(float)(atan2(s2, c2));//SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(c2, s2);

    if(c2==0){

        c3=1;
        s3=0;
        c1=matrix.getA();
        s1=matrix.getD();
    }else{
        float recC2=1.0f/c2;
        //get (c3,s3) from G,I
        c3=matrix.getI()*recC2;
        s3=-matrix.getG()*recC2;
        //get (c1,s1) from E,B
        c1=matrix.getE()*recC2;
        s1=-matrix.getB()*recC2;
    }

    v[0]=(float)(atan2(s1, c1));
    v[2]=(float)(atan2(s3, c3));
}

}
