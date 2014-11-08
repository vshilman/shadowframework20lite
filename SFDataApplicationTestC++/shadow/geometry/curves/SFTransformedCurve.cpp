/*
 * SFTransformedCurve.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFTransformedCurve.h"

namespace sf {


SFTransformedCurve::SFTransformedCurve() {
	this->curve=0;
}

SFTransformedCurve::SFTransformedCurve(SFCurve* curve, SFTransform3f transform) {
	this->curve = curve;
	this->transform = transform;
}

SFTransformedCurve::~SFTransformedCurve(){
	delete curve;//I think so
}

void SFTransformedCurve::compileCurve() {
	curve->compileCurve();
}

SFValuenf* SFTransformedCurve::getControlPoint(int index) {
	return curve->getControlPoint(index);
}

int SFTransformedCurve::getControlPointSize() {
	return curve->getControlPointSize();
}

void SFTransformedCurve::setControlPoint(int index, SFValuenf vertex) {
	curve->setControlPoint(index, vertex);
}

SFResource* SFTransformedCurve::getResource() {
	return &resource;
}

void SFTransformedCurve::setCurve(SFCurve* curve) {
	this->curve = curve;
}

void SFTransformedCurve::setTransform(SFTransform3f transform) {
	this->transform = transform;
}

SFValuenf* SFTransformedCurve::generateValue() {
	return curve->generateValue();
}


void SFTransformedCurve::getDev2Dt(float ts, SFValuenf* read) {
	curve->getDev2Dt(ts,read);
	transform.transformDir(*read);
}


void SFTransformedCurve::getDevDt(float t, SFValuenf* read) {
	curve->getDevDt(t, read);
	transform.transformDir(*read);
}

float SFTransformedCurve::getTMax() {
	return curve->getTMax();
}

float SFTransformedCurve::getTMin() {
	return curve->getTMin();
}

void SFTransformedCurve::getVertex(float t, SFValuenf* read) {
	curve->getVertex(t, read);
	transform.transform(*read);
}

} /* namespace sf */
