/*
 * SFSpline.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFSpline.h"

namespace sf {


SFSpline::SFSpline(int size) {
	this->controlPoints = new SFValuenf*[size];
	controlPoints_length=size;
	curve=0;
	curveSize=0;
	curveCount=0;
	resource=new SFResource(0);
}

SFSpline::~SFSpline(){
	delete resource;
	for(int i=0;i<controlPoints_length;i++){
		delete[] controlPoints[i]->getV();
		delete[] controlPoints[i];
	}
	delete[] controlPoints;
}


void SFSpline::setSize(int size){
	for(int i=0;i<controlPoints_length;i++){
		delete[] controlPoints[i]->getV();
		delete[] controlPoints[i];
	}
	delete[] controlPoints;
	controlPoints_length=size;
	controlPoints=new SFValuenf*[size];
}


void SFSpline::setCurve(SFCurve* curve) {
	this->curve = curve;
}


void SFSpline::compileCurve() {
	curve->compileCurve();
	this->curveSize=curve->getControlPointSize();
	this->curveCount=(controlPoints_length-1)/(curveSize-1);
}

SFResource* SFSpline::getResource() {
	return resource;
}


void SFSpline::setControlPoint(int index, SFValuenf* vertex) {
	controlPoints[index] = vertex;
}


int SFSpline::getControlPointSize() {
	return controlPoints_length;
}


SFValuenf* SFSpline::getControlPoint(int index) {
	return controlPoints[index];
}


void SFSpline::getDev2Dt(float T, SFValuenf* read) {
	T = selectCurve(T);
	curve->getDev2Dt(T, read);
}

void SFSpline::getDevDt(float T, SFValuenf* read) {
	T = selectCurve(T);
	curve->getDevDt(T, read);
}


void SFSpline::getVertex(float T, SFValuenf* read) {
	T = selectCurve(T);
	curve->getVertex(T, read);
}

float SFSpline::selectCurve(float T) {
	float t=T*curveCount;
	int index=(int)t;
	if(index==curveCount){
		index--;
	}
	t-=index;
	for (int i = 0; i < curveSize; i++) {
		curve->setControlPoint(i, controlPoints[index*(curveSize-1)+i]);
	}
	return t;
}


SFValuenf* SFSpline::generateValue() {
	return new SFValuenf(controlPoints[0]->getSize());
}


float SFSpline::getTMax() {
	return 1;
}


float SFSpline::getTMin() {
	return 0;
}

} /* namespace sf */
