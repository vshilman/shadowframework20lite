/*
 * SFNCurve.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFNCurve.h"

namespace sf {



SFNCurve::SFNCurve(){
	P=0;
}

SFNCurve::~SFNCurve(){

}


void SFNCurve::compileCurve() {
	P->compileCurve();
}

SFValuenf* SFNCurve::getControlPoint(int index) {
	return P->getControlPoint(index);
}

int SFNCurve::getControlPointSize() {
	return P->getControlPointSize();
}

void SFNCurve::setControlPoint(int index, SFValuenf* vertex) {
	P->setControlPoint(index, vertex);
}

SFResource* SFNCurve::getResource() {
	return &resource;
}

void SFNCurve::getVertex(float t, SFValuenf* read) {

	float T=(t-P->getTMin())/(P->getTMax()-P->getTMin());

	read->set(firstNormal);
	read->mult(1-T);
	read->addMult(T, secondNormal);

	SFVertex3f* derivative=new SFVertex3f();
	P->getDevDt(t, derivative);

	float K=-read->dot(*derivative)/derivative->dot(*derivative);

	read->addMult(K, *derivative);
	delete derivative;
}

SFValuenf* SFNCurve::generateValue() {
	return new SFVertex3f();
}

float SFNCurve::getTMax() {
	return P->getTMax();
}

float SFNCurve::getTMin() {
	return P->getTMin();
}

SFCurve* SFNCurve::getP() {
	return P;
}

void SFNCurve::setP(SFCurve* p) {
	P = p;
}

SFVertex3f SFNCurve::getFirstNormal() {
	return firstNormal;
}

void SFNCurve::setFirstNormal(SFVertex3f secondNormal) {
	this->firstNormal = firstNormal;
}

SFVertex3f SFNCurve::getSecondNormal() {
	return secondNormal;
}

void SFNCurve::setSecondNormal(SFVertex3f secondNormal) {
	this->secondNormal = secondNormal;
}


} /* namespace sf */
