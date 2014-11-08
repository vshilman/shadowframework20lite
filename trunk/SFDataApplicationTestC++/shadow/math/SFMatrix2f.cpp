#include "SFMatrix2f.h"


namespace sf{

SFMatrix2f::SFMatrix2f() {
}

SFMatrix2f::SFMatrix2f(float a, float b, float c, float d){
    v[0]=a;
    v[1]=b;
    v[2]=c;
    v[3]=d;
}

//SFValuenf* SFMatrix2f::cloneValue() {
//    return new SFMatrix2f(v[0],v[1],v[2],v[3]);
//}
    
    
    inline int SFMatrix2f::getSize(){
        return 4;
    }
    
    inline float* SFMatrix2f::getV(){
        return v;
    }

SFMatrix2f SFMatrix2f::getRotationZ(float angle) {
    SFMatrix2f m=SFMatrix2f();
    float cos_=(float) (cos(angle));
    float sin_=(float) (sin(angle));

    m.v[0]=cos_;
    m.v[1]=sin_;
    m.v[2]=-sin_;
    m.v[3]=cos_;

    return m;
}

SFMatrix2f SFMatrix2f::getTransposed(SFMatrix2f m) {
    SFMatrix2f n=SFMatrix2f();

    n.v[0]=m.v[0];
    n.v[2]=m.v[1];
    n.v[1]=m.v[2];
    n.v[3]=m.v[3];

    return n;
}

SFMatrix2f SFMatrix2f::getIdentity() {
    SFMatrix2f n=SFMatrix2f();

    n.v[0]=1;
    n.v[2]=0;
    n.v[1]=0;
    n.v[3]=1;

    return n;
}

SFMatrix2f SFMatrix2f::getScale(float scaleX, float scaleY) {
    SFMatrix2f n=SFMatrix2f();

    n.v[0]=scaleX;
    n.v[2]=0;
    n.v[1]=0;
    n.v[3]=scaleY;

    return n;
}

SFMatrix2f SFMatrix2f::getInverse(SFMatrix2f m) {
    SFMatrix2f n=SFMatrix2f();

    float delta=m.v[0] * m.v[3] - m.v[2] * m.v[1];

    if (delta != 0) {
        delta=1 / delta;

        n.v[0]=delta * (m.v[3]);
        n.v[1]=-delta * (m.v[1]);
        n.v[2]=delta * (-m.v[2]);
        n.v[3]=delta * (m.v[0]);
    }

    return n;
}

void SFMatrix2f::set(SFMatrix2f m) {
    v[0]=m.v[0];
    v[1]=m.v[1];
    v[2]=m.v[2];
    v[3]=m.v[3];
}

SFMatrix2f SFMatrix2f::MultMatrix(SFMatrix2f m) {
    SFMatrix2f n=SFMatrix2f();

    n.v[0]=v[0] * m.v[0] + v[1] * m.v[2];
    n.v[1]=v[0] * m.v[1] + v[1] * m.v[3];

    n.v[2]=v[2] * m.v[0] + v[3] * m.v[2];
    n.v[3]=v[2] * m.v[1] + v[3] * m.v[3];

    return n;
}

SFVertex2f SFMatrix2f::Mult(SFVertex2f p) {
    SFVertex2f n=SFVertex2f(0.0,0.0);

    n.setX(v[0] * p.getX() + v[1] * p.getY());
    n.setY(v[2] * p.getX() + v[2] * p.getY());

    return n;
}

void SFMatrix2f::transform(SFVertex2f* p) {

    float x=p->getX(), y=p->getY();

    p->setX(v[0] * x + v[1] * y);
    p->setY(v[2] * x + v[2] * y);
}

/**
 * @return Returns the a.
 */
float SFMatrix2f::getA() {
    return v[0];
}

/**
 * @param a
 *            The a to set.
 */
void SFMatrix2f::setA(float a) {
    v[0]=a;
}

/**
 * @return Returns the b.
 */
float SFMatrix2f::getB() {
    return v[1];
}

/**
 * @param b
 *            The b to set.
 */
void SFMatrix2f::setB(float b) {
    v[1]=b;
}

/**
 * @return Returns the c.
 */
float SFMatrix2f:: getC() {
    return v[2];
}

/**
 * @param c
 *            The c to set.
 */
void SFMatrix2f::setC(float c) {
    v[2]=c;
}

/**
 * @return Returns the d.
 */
float SFMatrix2f::getD() {
    return v[3];
}

/**
 * @param d
 *            The d to set.
 */
void SFMatrix2f::setD(float d) {
    v[3]=d;
}

}
