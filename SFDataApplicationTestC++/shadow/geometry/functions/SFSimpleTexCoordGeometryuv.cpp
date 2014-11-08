/*
 * SFSimpleTexCoordGeometryuv.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/functions/SFSimpleTexCoordGeometryuv.h"

namespace sf {

SFSimpleTexCoordGeometryuv::~SFSimpleTexCoordGeometryuv(){};

SFSimpleTexCoordGeometryuv::SFSimpleTexCoordGeometryuv(float du, float dv) {
	this->du = du;
	this->dv = dv;
}


float SFSimpleTexCoordGeometryuv::getX(float u, float v) {
	return u*du;
}

float SFSimpleTexCoordGeometryuv::getY(float u, float v) {
	return v*dv;
}


float SFSimpleTexCoordGeometryuv::getZ(float u, float v) {
	return 0;
}

float SFSimpleTexCoordGeometryuv::getDu() {
	return du;
}

void SFSimpleTexCoordGeometryuv::setDu(float du) {
	this->du = du;
}

float SFSimpleTexCoordGeometryuv::getDv() {
	return dv;
}

void SFSimpleTexCoordGeometryuv::setDv(float dv) {
	this->dv = dv;
}

} /* namespace sf */
