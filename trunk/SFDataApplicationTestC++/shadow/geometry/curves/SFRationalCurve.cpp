/*
 * SFRationalCurve.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFRationalCurve.h"

namespace sf {


SFRationalCurve::SFRationalCurve(SFCurve* curve,int size) {
	this->curve = curve;
	vertices=new SFValuenf*[size];
	vertices_length=size;
	weights=0;
	sampleVertex=0;
}

SFRationalCurve::~SFRationalCurve(){
	for(int i=0;i<vertices_length;i++){
		delete[] vertices[i]->getV();
		delete vertices[i];
	}
	delete[] vertices;
}

void SFRationalCurve::setSize(int size){
	for(int i=0;i<vertices_length;i++){
		delete (vertices[i]);
	}
	delete[] vertices;

	vertices=new SFValuenf*[size];
}

void SFRationalCurve::setWeights(float* weights,short weights_length) {
	this->weights = weights;
}


void SFRationalCurve::setControlPoint(int index, SFValuenf* vertex) {
	vertices[index]= vertex;
}


int SFRationalCurve::getControlPointSize() {
	return vertices_length;
}


SFValuenf* SFRationalCurve::getControlPoint(int index) {
	return vertices[index];
}


void SFRationalCurve::compileCurve(){
	for (int i = 0; i < vertices_length; i++) {
		int size=vertices[i]->getSize();
		SFValuenf* value=new SFValuenf(size+1);
		value->set(*vertices[i]);
		value->mult(weights[i]);
		value->getV()[size]=weights[i];
		curve->setControlPoint(i, value);
		delete value;
	}
	sampleVertex=new SFValuenf(vertices[0]->getSize()+1);
	curve->compileCurve();
}

SFResource* SFRationalCurve::getResource() {
	return &resource;
}


SFValuenf* SFRationalCurve::generateValue() {
	return new SFVertex4f();
}


float SFRationalCurve::getTMax() {
	return curve->getTMax();
}

float SFRationalCurve::getTMin() {
	return curve->getTMin();
}

void SFRationalCurve::getVertex(float t, SFValuenf* read) {
	this->curve->getVertex(t, sampleVertex);
	sampleVertex->mult(1.0f/sampleVertex->getV()[sampleVertex->getSize()-1]);
	read->set(*sampleVertex);
}

} /* namespace sf */
