#include "SFVertex3f.h"

namespace sf{

SFVertex3f::SFVertex3f(double x, double y, double z){
	set3f(x, y, z);
}

SFVertex3f::SFVertex3f(float x, float y, float z){
	set3f(x, y, z);
}

SFVertex3f::SFVertex3f(){
	set3f(0, 0, 0);
}
    
    
    inline int SFVertex3f::getSize(){
        return 3;
    }
    
    inline float* SFVertex3f::getV(){
        return (v);
    }

float SFVertex3f::getDistance(SFVertex3f v1, SFVertex3f v2) {
	float x = v1.getX() - v2.getX();
	float y = v1.getY() - v2.getY();
	float z = v1.getZ() - v2.getZ();
	return (float) (sqrt(x * x + y * y + z * z));
}

SFVertex3f* SFVertex3f::middle(SFVertex3f A, SFVertex3f B) {
	return new SFVertex3f((A.v[0] + B.v[0]) * 0.5f, (A.v[1] + B.v[1]) * 0.5f,
			(A.v[2] + B.v[2]) * 0.5f);
}


void SFVertex3f::add3f(SFVertex3f vx) {
	v[0] += vx.v[0];
	v[1] += vx.v[1];
	v[2] += vx.v[2];
}

void SFVertex3f::addMult3f(float a, SFVertex3f vx) {
	v[0] += vx.v[0] * a;
	v[1] += vx.v[1] * a;
	v[2] += vx.v[2] * a;
}


SFVertex3f* SFVertex3f::cross(SFVertex3f vx) {
	return new SFVertex3f(v[1] * vx.v[2] - v[2] * vx.v[1],
			v[2] * vx.v[0] - v[0] * vx.v[2], v[0] * vx.v[1] - v[1] * vx.v[0]);
}

float SFVertex3f::dot3f(SFVertex3f vx) {
	return vx.v[0] * v[0] + vx.v[1] * v[1] + vx.v[2] * v[2];
}

float SFVertex3f::getLength() {
	return (float) (sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]));
}

float SFVertex3f::getX() {
	return v[0];
}

float SFVertex3f::getY() {
	return v[1];
}

float SFVertex3f::getZ() {
	return v[2];
}

void SFVertex3f::mult3f(float a) {
	v[0] *= a;
	v[1] *= a;
	v[2] *= a;
}

void SFVertex3f::normalize3f() {
	float lengthRec = 1 / getLength();
	v[0] *= lengthRec;
	v[1] *= lengthRec;
	v[2] *= lengthRec;
}


SFVertex3f* SFVertex3f::cloneValue() {
	return new SFVertex3f(v[0], v[1], v[2]);
}

void SFVertex3f::scale3f(float sx, float sy, float sz) {
	v[0] *= sx;
	v[1] *= sy;
	v[2] *= sz;
}

void SFVertex3f::set3f(float x, float y, float z) {
	v[0] = x;
	v[1] = y;
	v[2] = z;
}

void SFVertex3f::setX(float x) {
	v[0] = x;
}

void SFVertex3f::setY(float y) {
	v[1] = y;
}

void SFVertex3f::setZ(float z) {
	v[2] = z;
}


void SFVertex3f::subtract3f(SFVertex3f vx) {
	v[0] -= vx.v[0];
	v[1] -= vx.v[1];
	v[2] -= vx.v[2];
}

}
