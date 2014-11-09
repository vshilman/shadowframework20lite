/*
 * SFBicurvedLoftedSurface.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/functions/SFBicurvedLoftedSurface.h"

namespace sf {

SFBicurvedLoftedSurface::~SFBicurvedLoftedSurface(){
	delete A;
	delete B;
}


SFBicurvedLoftedSurface::SFBicurvedLoftedSurface() {
	maxTA=1;
	maxTB=1;
	A=0;
	B=0;
}

SFBicurvedLoftedSurface::SFBicurvedLoftedSurface(SFCurve* a, SFCurve* b) {
	A = a;
	B = b;
	maxTA=A->getTMax();
	maxTB=B->getTMax();
}



float SFBicurvedLoftedSurface::getX(float u, float v) {
	A->getVertex(u*maxTA, &tmp);
	B->getVertex(u*maxTB, &tmp2);
	tmp.mult(1-v);
	tmp.addMult(v, tmp2);
	return tmp.getX();
}

float SFBicurvedLoftedSurface::getY(float u, float v) {
	return tmp.getY();
}

float SFBicurvedLoftedSurface::getZ(float u, float v) {
	return tmp.getZ();
}

SFCurve* SFBicurvedLoftedSurface::getA() {
	return A;
}

void SFBicurvedLoftedSurface::setA(SFCurve* a) {
	A = a;

}

SFCurve* SFBicurvedLoftedSurface::getB() {
	return B;

}

void SFBicurvedLoftedSurface::setB(SFCurve* b) {
	B = b;
}

float SFBicurvedLoftedSurface::getMaxTA() {
	return maxTA;
}

void SFBicurvedLoftedSurface::setMaxTA(float maxTA) {
	this->maxTA = maxTA;
}

float SFBicurvedLoftedSurface::getMaxTB() {
	return maxTB;
}

void SFBicurvedLoftedSurface::setMaxTB(float maxTB) {
	this->maxTB = maxTB;
}

} /* namespace sf */
