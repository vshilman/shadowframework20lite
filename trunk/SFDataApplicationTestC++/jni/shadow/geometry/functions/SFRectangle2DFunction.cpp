/*
 * SFRectangle2DFunction.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/functions/SFRectangle2DFunction.h"

namespace sf {


SFRectangle2DFunction::~SFRectangle2DFunction(){}

SFRectangle2DFunction::SFRectangle2DFunction(float x, float y, float w, float h) {
	this->x = x;
	this->y = y;
	this->w = w;
	this->h = h;
}

float SFRectangle2DFunction::getX(float u, float v) {
	return x+u*w;
}

float SFRectangle2DFunction::getY(float u, float v) {
	return y+v*h;
}


float SFRectangle2DFunction::getZ(float u, float v) {
	return 0;
}

float SFRectangle2DFunction::getX() {
	return x;
}

void SFRectangle2DFunction::setX(float x) {
	this->x = x;
}

float SFRectangle2DFunction::getY() {
	return y;
}

void SFRectangle2DFunction::setY(float y) {
	this->y = y;
}

float SFRectangle2DFunction::getW() {
	return w;
}

void SFRectangle2DFunction::setW(float w) {
	this->w = w;
}

float SFRectangle2DFunction::getH() {
	return h;
}

void SFRectangle2DFunction::setH(float h) {
	this->h = h;
}
} /* namespace sf */
