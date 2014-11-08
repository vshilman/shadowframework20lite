#include "SFVertex2f.h"

namespace sf{

SFVertex2f::SFVertex2f() {
	v[0] = 0;
	v[1] = 0;
}

SFVertex2f::SFVertex2f(float x, float y)  {
	v[0] = x;
	v[1] = y;
}

SFVertex2f::SFVertex2f(double x, double y) {
	v[0] = (float) x;
	v[1] = (float) y;
}

SFVertex2f* SFVertex2f::middle(SFVertex2f A, SFVertex2f B) {
	return new SFVertex2f((A.v[0] + B.v[0]) * 0.5f, (A.v[1] + B.v[1]) * 0.5f);
}

    
    inline int SFVertex2f::getSize(){
        return 2;
    }
    
    inline float* SFVertex2f::getV(){
        return this->v;
    }

float SFVertex2f::getDistance(SFVertex2f v1, SFVertex2f v2) {
	float x = v1.getX() - v2.getX();
	float y = v1.getY() - v2.getY();
	return (float) (sqrt(x * x + y * y));
}

void SFVertex2f::add2f(SFVertex2f vx) {
	v[0] += vx.v[0];
	v[1] += vx.v[1];
}

void SFVertex2f::addMult2f(float a, SFVertex2f vx) {
	v[0] += vx.v[0] * a;
	v[1] += vx.v[1] * a;
}

float SFVertex2f::dot2f(SFVertex2f vx) {
	return vx.v[0] * v[0] + vx.v[1] * v[1];
}

//SFValuenf* SFVertex2f::cloneValue() {
//	return new SFVertex2f(v[0], v[1]);
//}

float SFVertex2f::getLength() {
	return (float) (sqrt(v[0] * v[0] + v[1] * v[1]));
}

float SFVertex2f::getX() {
	return v[0];
}

float SFVertex2f::getY() {
	return v[1];
}

void SFVertex2f::mult2f(float m) {
	v[0] *= m;
	v[1] *= m;
}


void SFVertex2f::normalize2f() {
	float lengthRec = 1 / getLength();
	v[0] *= lengthRec;
	v[1] *= lengthRec;
}

void SFVertex2f::scale2f(float sx, float sy) {
	v[0] *= sx;
	v[1] *= sy;
}


void SFVertex2f::setX(float x) {
	v[0] = x;
}

void SFVertex2f::setY(float y) {
	v[1] = y;
}


void SFVertex2f::set2f(float x, float y) {
	this->v[0] = x;
	this->v[1] = y;
}

void SFVertex2f::subtract2f(SFVertex2f vx) {
	v[0] -= vx.v[0];
	v[1] -= vx.v[1];
}

}
