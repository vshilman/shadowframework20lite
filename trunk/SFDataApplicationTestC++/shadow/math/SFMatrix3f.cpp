#include "SFMatrix3f.h"

#include <iostream>
using namespace std;


namespace sf{

SFMatrix3f::SFMatrix3f()  {

	v[0] = 1;
	v[4] = 1;
	v[8] = 1;
	v[1] = 0;
	v[2] = 0;
	v[3] = 0;
	v[5] = 0;
	v[6] = 0;
	v[7] = 0;
}

SFMatrix3f::SFMatrix3f(float a, float b, float c, float d, float e, float f,
		float g, float h, float i)  {
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
    
    
    inline int SFMatrix3f::getSize(){
        return 9;
    }
    
    inline float* SFMatrix3f::getV(){
        return v;
    }

//SFValuenf* SFMatrix3f::cloneValue() {
//	return new SFMatrix3f(v[0], v[1], v[2], v[3], v[4], v[5], v[6], v[7], v[8]);
//}

SFMatrix3f SFMatrix3f::getRotationZ(float angle) {
	SFMatrix3f m = SFMatrix3f();
	float cos_ = (float) (cos(angle));
	float sin_ = (float) (sin(angle));

	m.v[0] = cos_;
	m.v[1] = sin_;
	m.v[2] = 0;
	m.v[3] = -sin_;
	m.v[4] = cos_;
	m.v[5] = 0;
	m.v[6] = 0;
	m.v[7] = 0;
	m.v[8] = 1;

	return m;
}

SFMatrix3f SFMatrix3f::getRotationY(float angle) {
	SFMatrix3f m = SFMatrix3f();
	float cos_ = (float) (cos(angle));
	float sin_ = (float) (sin(angle));

	m.v[0] = cos_;
	m.v[1] = 0;
	m.v[2] = sin_;
	m.v[3] = 0;
	m.v[4] = 1;
	m.v[5] = 0;
	m.v[6] = -sin_;
	m.v[7] = 0;
	m.v[8] = cos_;

	return m;
}

SFMatrix3f SFMatrix3f::getRotationX(float angle) {
	SFMatrix3f m = SFMatrix3f();
	float cos_ = (float) (cos(angle));
	float sin_ = (float) (sin(angle));

	m.v[0] = 1;
	m.v[1] = 0;
	m.v[2] = 0;
	m.v[3] = 0;
	m.v[4] = cos_;
	m.v[5] = sin_;
	m.v[6] = 0;
	m.v[7] = -sin_;
	m.v[8] = cos_;

	return m;
}

SFMatrix3f SFMatrix3f::getTransposed(SFMatrix3f m) {
	SFMatrix3f n = SFMatrix3f();

	n.v[0] = m.v[0];
	n.v[3] = m.v[1];
	n.v[6] = m.v[2];
	n.v[1] = m.v[3];
	n.v[4] = m.v[4];
	n.v[7] = m.v[5];
	n.v[2] = m.v[6];
	n.v[5] = m.v[7];
	n.v[8] = m.v[8];

	return n;
}

SFMatrix3f SFMatrix3f::getIdentity() {
	SFMatrix3f n = SFMatrix3f();

	n.v[0] = 1;
	n.v[3] = 0;
	n.v[6] = 0;
	n.v[1] = 0;
	n.v[4] = 1;
	n.v[7] = 0;
	n.v[2] = 0;
	n.v[5] = 0;
	n.v[8] = 1;

	return n;
}

SFMatrix3f SFMatrix3f::getScale(float scaleX, float scaleY, float scaleZ) {
	SFMatrix3f n = SFMatrix3f();

	n.v[0] = scaleX;
	n.v[3] = 0;
	n.v[6] = 0;
	n.v[1] = 0;
	n.v[4] = scaleY;
	n.v[7] = 0;
	n.v[2] = 0;
	n.v[5] = 0;
	n.v[8] = scaleZ;

	return n;
}

SFMatrix3f SFMatrix3f::getInverse(SFMatrix3f m) {
	SFMatrix3f n = SFMatrix3f();

	float delta = m.v[0] * (m.v[4] * m.v[8] - m.v[5] * m.v[7])
			- m.v[1] * (m.v[3] * m.v[8] - m.v[5] * m.v[6])
			+ m.v[2] * (m.v[3] * m.v[7] - m.v[4] * m.v[6]);

	if (delta != 0) {
		delta = 1 / delta;

		n.v[0] = delta * (m.v[4] * m.v[8] - m.v[5] * m.v[7]);
		n.v[1] = -delta * (m.v[1] * m.v[8] - m.v[2] * m.v[7]);
		n.v[2] = delta * (m.v[1] * m.v[5] - m.v[2] * m.v[4]);
		n.v[3] = -delta * (m.v[3] * m.v[8] - m.v[5] * m.v[6]);
		n.v[4] = delta * (m.v[0] * m.v[8] - m.v[2] * m.v[6]);
		n.v[5] = -delta * (m.v[0] * m.v[5] - m.v[2] * m.v[3]);
		n.v[6] = delta * (m.v[3] * m.v[7] - m.v[4] * m.v[6]);
		n.v[7] = -delta * (m.v[0] * m.v[7] - m.v[1] * m.v[6]);
		n.v[8] = delta * (m.v[0] * m.v[4] - m.v[1] * m.v[3]);
	}

	return n;
}

//SFMatrix3f SFMatrix3f::getRotationMatrix(SFVertex4f q) {
//	SFMatrix3f m = SFMatrix3f();
//
//	m.setA(1 - 2 * (q.getY() * q.getY() + q.getZ() * q.getZ()));
//	m.setB(-2 * q.getZ() * q.getW() + 2 * q.getX() * q.getY());
//	m.setC(2 * q.getW() * q.getY() + 2 * q.getX() * q.getZ());
//
//	m.setD(2 * q.getZ() * q.getW() + 2 * q.getX() * q.getY());
//	m.setE(1 - 2 * (q.getX() * q.getX() + q.getZ() * q.getZ()));
//	m.setF(2 * q.getY() * q.getZ() - 2 * q.getX() * q.getW());
//
//	m.setG(2 * q.getX() * q.getZ() - 2 * q.getW() * q.getY());
//	m.setH(2 * q.getY() * q.getY() - 2 * q.getW() * q.getX());
//	m.setI(1 - 2 * (q.getX() * q.getX() + q.getY() * q.getY()));
//
//	return m;
//}

SFMatrix3f SFMatrix3f::MultMatrix(SFMatrix3f m) {
	SFMatrix3f n = SFMatrix3f();

	n.v[0] = v[0] * m.v[0] + v[1] * m.v[3] + v[2] * m.v[6];
	n.v[1] = v[0] * m.v[1] + v[1] * m.v[4] + v[2] * m.v[7];
	n.v[2] = v[0] * m.v[2] + v[1] * m.v[5] + v[2] * m.v[8];

	n.v[3] = v[3] * m.v[0] + v[4] * m.v[3] + v[5] * m.v[6];
	n.v[4] = v[3] * m.v[1] + v[4] * m.v[4] + v[5] * m.v[7];
	n.v[5] = v[3] * m.v[2] + v[4] * m.v[5] + v[5] * m.v[8];

	n.v[6] = v[6] * m.v[0] + v[7] * m.v[3] + v[8] * m.v[6];
	n.v[7] = v[6] * m.v[1] + v[7] * m.v[4] + v[8] * m.v[7];
	n.v[8] = v[6] * m.v[2] + v[7] * m.v[5] + v[8] * m.v[8];

	return n;
}


SFVertex3f SFMatrix3f::Mult(SFVertex3f p) {
	SFVertex3f n = SFVertex3f();

	float* vm=n.getV();
	vm[0]=(v[0] * p.getX() + v[1] * p.getY() + v[2] * p.getZ());
	vm[1]=(v[3] * p.getX() + v[4] * p.getY() + v[5] * p.getZ());
	vm[2]=(v[6] * p.getX() + v[7] * p.getY() + v[8] * p.getZ());

	cout << "p.get() " << p.getX()<< "," << p.getY() <<","<<p.getZ()<< endl;
	return n;
}

void SFMatrix3f::transform(SFVertex3f* p) {

	float x = (float) p->getX(), y = (float) p->getY(), z = (float) p->getZ();

	p->setX(v[0] * x + v[1] * y + v[2] * z);
	p->setY(v[3] * x + v[4] * y + v[5] * z);
	p->setZ(v[6] * x + v[7] * y + v[8] * z);

}


float SFMatrix3f::getA() {
	return v[0];
}

void SFMatrix3f::setA(float a) {
	v[0] = a;
}

float SFMatrix3f::getB() {
	return v[1];
}

void SFMatrix3f::setB(float b) {
	v[1] = b;
}

float SFMatrix3f::getC() {
	return v[2];
}

void SFMatrix3f::setC(float c) {
	v[2] = c;
}

float SFMatrix3f::getD() {
	return v[3];
}

void SFMatrix3f::setD(float d) {
	v[3] = d;
}

float SFMatrix3f::getE() {
	return v[4];
}

void SFMatrix3f::setE(float e) {
	v[4] = e;
}

float SFMatrix3f::getF() {
	return v[5];
}

void SFMatrix3f::setF(float f) {
	v[5] = f;
}

float SFMatrix3f::getG() {
	return v[6];
}

void SFMatrix3f::setG(float g) {
	v[6] = g;
}

float SFMatrix3f::getH() {
	return v[7];
}

void SFMatrix3f::setH(float h) {
	v[7] = h;
}

float SFMatrix3f::getI() {
	return v[8];
}

void SFMatrix3f::setI(float i) {
	v[8] = i;
}

}
