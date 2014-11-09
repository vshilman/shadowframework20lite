/*
 * SFStandardAbstractCurve.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFStandardAbstractCurve.h"

namespace sf {

SFStandardAbstractCurve::SFStandardAbstractCurve(int n){
		vertices=new SFValuenf*[n];
		vertices_length=n;
	}

SFStandardAbstractCurve::~SFStandardAbstractCurve(){
	delete[] vertices;
}

void SFStandardAbstractCurve::compileCurve() {
	//nothing to do
}

void SFStandardAbstractCurve::setControlPoint(int index, SFValuenf* vertex) {
	vertices[index]=*vertex;
}

SFResource* SFStandardAbstractCurve::getResource() {
	return &resource;
}

int SFStandardAbstractCurve::getControlPointSize(){
	return vertices_length;
}

void SFStandardAbstractCurve::setControlPoint(SFValuenf* value,int index) {
	vertices[index]=value;
}

SFValuenf* SFStandardAbstractCurve::getControlPoint(int index) {
	return vertices[index];
}

SFValuenf* SFStandardAbstractCurve::generateValue() {
	return new SFValuenf(vertices[0]->getSize());
}

float SFStandardAbstractCurve::getTMin() {
	return 0;
}

float SFStandardAbstractCurve::getTMax() {
	return 1;
}

} /* namespace sf */
