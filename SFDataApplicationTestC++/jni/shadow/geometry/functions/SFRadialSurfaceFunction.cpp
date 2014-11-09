/*
 * SFRadialSurfaceFunction.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/functions/SFRadialSurfaceFunction.h"

namespace sf {


SFRadialSurfaceFunction::~SFRadialSurfaceFunction(){
	delete borderCurve;
	delete rayCurve;
}


SFRadialSurfaceFunction::SFRadialSurfaceFunction() {
	borderCurve=0;
	rayCurve=0;
	A=0;
	B=0;
}

SFRadialSurfaceFunction::SFRadialSurfaceFunction(SFCurve* borderCurve,
		SFCurve* rayCurve) {
	setBorderCurve(borderCurve);
	setRayCurve(rayCurve);
}

void SFRadialSurfaceFunction::setBorderCurve(SFCurve* borderCurve) {
	this->borderCurve = borderCurve;
}

void SFRadialSurfaceFunction::setRayCurve(SFCurve* rayCurve) {
	this->rayCurve = rayCurve;
	rayCurve->getVertex(0, &r0);
	rayCurve->getVertex(1, &r1);
	r1.subtract(r0);
}


void SFRadialSurfaceFunction::evaluateAB(float u){
	SFVertex3f* p=new SFVertex3f();
	borderCurve->getVertex(/*u*borderCurve.getTMax()+borderCurve.getTMin()*(1-u)*/u, p);
	p->subtract(r0);
	float dot=r1.dot3f(r1);
	A=(r1.getX()*p->getX()-r1.getY()*p->getY())/dot;
	B=(+r1.getY()*p->getX()+r1.getX()*p->getY())/dot;
}


float SFRadialSurfaceFunction::getX(float u, float v) {
	evaluateAB(u);
	rayCurve->getVertex(/*u*rayCurve.getTMax()+rayCurve.getTMin()*(1-u)*/v, &pv);
	pv.subtract(r0);
	return A*pv.getX()-B*pv.getY()+r0.getX();
}


float SFRadialSurfaceFunction::getY(float u, float v) {
	return B*pv.getX()+A*pv.getY()+r0.getY();
}


float SFRadialSurfaceFunction::getZ(float u, float v) {
	return pv.getZ();
}


} /* namespace sf */
