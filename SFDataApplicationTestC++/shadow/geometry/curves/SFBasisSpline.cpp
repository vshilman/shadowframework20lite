/*
 * SFBasisSpline.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFBasisSpline.h"

namespace sf {

SFBasisSpline::SFBasisSpline(bool closed,int size){
	this->closed = closed;
	vertices=new SFValuenf[size];
	vertices_length=size;
	resource=new SFResource();
}

SFBasisSpline::~SFBasisSpline(){
	delete resource;
	delete[] vertices;
}

void SFBasisSpline::setSize(int size){
	delete [] vertices;
	vertices=new SFValuenf[size];
	vertices_length=size;
}

SFResource* SFBasisSpline::getResource ( ){
	return resource;
}

void SFBasisSpline::setControlPoint(int index, SFValuenf vertex){
	this->vertices[index].set(vertex);
}

int SFBasisSpline::getControlPointSize() {
	return vertices_length;
}

SFValuenf* SFBasisSpline::getControlPoint(int index) {
	return vertices+index;
}

SFValuenf* SFBasisSpline::getVertices ( ){
	return vertices;
}

float SFBasisSpline::getTMax() {
	return 1;
}


float SFBasisSpline::getTMin() {
	return 0;
}

SFValuenf* SFBasisSpline::generateValue() {
	return new SFValuenf(vertices[0].getSize());
}

} /* namespace sf */
