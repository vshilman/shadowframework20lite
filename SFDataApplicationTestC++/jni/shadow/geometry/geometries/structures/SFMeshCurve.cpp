/*
 * SFMeshCurve.cpp
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/geometries/structures/SFMeshCurve.h"

namespace sf {

SFCurve** SFMeshCurve::getCurve() {
	return curve;
}

void SFMeshCurve::setCurve(SFCurve** curve) {
	this->curve = curve;
}

int SFMeshCurve::getSide() {
	return side;
}

void SFMeshCurve::setSide(short side) {
	this->side = side;
}

short* SFMeshCurve::getVertices() {
	return vertices;
}

void SFMeshCurve::setVertices(short vertices[2]) {
	this->vertices[0] = vertices[0];
	this->vertices[1] = vertices[1];
}

SFResource SFMeshCurve::getResource() {
	return resource;
}

} /* namespace sf */
