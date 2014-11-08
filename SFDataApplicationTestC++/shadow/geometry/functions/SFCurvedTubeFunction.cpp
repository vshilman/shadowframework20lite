/*
 * SFCurvedTubeFunction.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/functions/SFCurvedTubeFunction.h"

namespace sf {


SFCurvedTubeFunction::~SFCurvedTubeFunction(){
	delete centralCurve;
	delete rayCurve;
}


SFCurvedTubeFunction::SFCurvedTubeFunction() {
	lastV=-1;
	cos_=0;
	sin_=0;
	centralCurve=0;
	rayCurve=0;
}

SFCurvedTubeFunction::SFCurvedTubeFunction(SFCurve* centralCurve,
		SFCurve* rayCurve) {
	this->centralCurve = centralCurve;
	this->rayCurve = rayCurve;
	lastV=-1;
	cos_=0;
	sin_=0;
}

SFVertex3f SFCurvedTubeFunction::getDu() {
	SFVertex3f du(getDXDu(),getDYDu(),getDZDu());
	du.normalize3f();
	return du;
}

SFVertex3f SFCurvedTubeFunction::getDv() {
	SFVertex3f dv(getDXDv(),getDYDv(),getDZDv());
	dv.normalize3f();
	return dv;
}

SFVertex3f SFCurvedTubeFunction::getPosition() {
	return SFVertex3f(getX(),getY(),getZ());
}


void SFCurvedTubeFunction::evalAll(float v){
	if(v!=lastV){
		centralCurve->getVertex(v, &Ccv);
		centralCurve->getDevDt(v, &dCcdv);
		rayCurve->getVertex(v, &Vec1v);
		Vec1v.subtract3f(Ccv);
		SFVertex3f* cross=dCcdv.cross(Vec1v);
		Vec2v.setArray(cross->getV());
			delete cross;
		Vec2v.mult((float)(sqrt(Vec1v.dot3f(Vec1v)/Vec2v.dot3f(Vec2v))));

		SFVertex3f dCcdv2;
		centralCurve->getDev2Dt(v, &dCcdv2);
		rayCurve->getDevDt(v, &DVec1v);
		DVec1v.subtract3f(dCcdv);
		//DVec1v.normalize3f();
		cross=dCcdv.cross(DVec1v);
		DVec2v.setArray(cross->getV());
			delete cross;
			cross=dCcdv2.cross(DVec1v);
		DVec2v.add3f(dCcdv2);
			delete cross;
		lastV=v;
	}
}

float SFCurvedTubeFunction::getX(float u,float v) {
	evalAll(v);
	cos_=(float)(cos(2*M_PI*u));
	sin_=(float)(sin(2*M_PI*u));
	return Ccv.getX()+cos_*Vec1v.getX()+sin_*Vec2v.getX();
}

float SFCurvedTubeFunction::getY(float u,float v) {
	return Ccv.getY()+cos_*Vec1v.getY()+sin_*Vec2v.getY();
}

float SFCurvedTubeFunction::getZ(float u,float v) {
	return Ccv.getZ()+cos_*Vec1v.getZ()+sin_*Vec2v.getZ();
}


float SFCurvedTubeFunction::getX() {
	return Ccv.getX()+cos_*Vec1v.getX()+sin_*Vec2v.getX();
}

float SFCurvedTubeFunction::getY() {
	return Ccv.getY()+cos_*Vec1v.getY()+sin_*Vec2v.getY();
}

float SFCurvedTubeFunction::getZ() {
	return Ccv.getZ()+cos_*Vec1v.getZ()+sin_*Vec2v.getZ();
}

float SFCurvedTubeFunction::getDXDv() {
	return dCcdv.getX()+cos_*DVec1v.getX()+sin_*DVec2v.getX();
}

float SFCurvedTubeFunction::getDYDv() {
	return dCcdv.getY()+cos_*DVec1v.getY()+sin_*DVec2v.getY();
}

float SFCurvedTubeFunction::getDZDv() {
	return dCcdv.getZ()+cos_*DVec1v.getZ()+sin_*DVec2v.getZ();
}

float SFCurvedTubeFunction::getDXDu() {
	return -sin_*Vec1v.getX()+cos_*Vec2v.getX();
}

float SFCurvedTubeFunction::getDYDu() {
	return -sin_*Vec1v.getY()+cos_*Vec2v.getY();
}

float SFCurvedTubeFunction::getDZDu() {
	return -sin_*Vec1v.getZ()+cos_*Vec2v.getZ();
}

SFCurve* SFCurvedTubeFunction::getCentralCurve() {
	return centralCurve;
}

void SFCurvedTubeFunction::setCentralCurve(SFCurve* centralCurve) {
	this->centralCurve = centralCurve;
}

SFCurve* SFCurvedTubeFunction::getRayCurve() {
	return rayCurve;
}

void SFCurvedTubeFunction::setRayCurve(SFCurve* rayCurve) {
	this->rayCurve = rayCurve;
}

} /* namespace sf */
