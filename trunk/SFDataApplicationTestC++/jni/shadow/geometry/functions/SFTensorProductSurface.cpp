/*
 * SFTensorProductSurface.cpp
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/functions/SFTensorProductSurface.h"

namespace sf {

void SFTensorProductSurface::evaluateLastVertex(float u,float v) {
		for (int i = 0; i < productCurve->getControlPointSize(); i++) {
			SFValuenf* vertex=productCurve->getControlPoint(i);
			guideLines[i]->getVertex(u, vertex);
		}
		productCurve->compileCurve();
		productCurve->getVertex(v, &lastVertex);
	}

SFTensorProductSurface::~SFTensorProductSurface(){
	for(int i=0;i<productCurve->getControlPointSize();i++){
		delete guideLines[i];
	}
	delete productCurve;
	delete[] guideLines;
	delete[] lastVertex.getV();
}

SFTensorProductSurface::SFTensorProductSurface(){
	guideLines=0;
	productCurve=0;
}

void SFTensorProductSurface::setGuideLines(SFCurve** guideLines) {
	this->guideLines = guideLines;
}

void SFTensorProductSurface::setProductCurve(SFCurve* productCurve) {
	this->productCurve = productCurve;
	SFValuenf* value=productCurve->generateValue();
	lastVertex=*(value);
	delete value;
}

float SFTensorProductSurface::getX(float u, float v) {
	evaluateLastVertex(u,v);
	return lastVertex.getV()[0];
}

float SFTensorProductSurface::getY(float u, float v) {
	return lastVertex.getV()[1];
}

float SFTensorProductSurface::getZ(float u, float v) {
	return lastVertex.getV()[2];
}

} /* namespace sf */
