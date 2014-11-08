/*
 * SFTilesSet.cpp
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/geometries/structures/SFTilesSet.h"

namespace sf {

SFTilesSet::~SFTilesSet() {
	// TODO Auto-generated destructor stub
}

SFTilesSet::SFTilesSet() {
	stepX=0;
	stepY=0;
	sizeX=0;
	sizeY=0;
}

void SFTilesSet::setSizeX(int sizeX) {
	this->sizeX = sizeX;
	stepX=1.0f/sizeX;
}

int SFTilesSet::getSizeX(void* sfTilesSpace) {
	return sizeX;
}

void SFTilesSet::setSizeY( int sizeY) {
	this->sizeY = sizeY;
	stepY=1.0f/sizeY;
}

int SFTilesSet::getSizeY(void* sfTilesSpace) {
	return sizeY;
}

void SFTilesSet::setSizes(int sizeX, int sizeY) {
	setSizeX( sizeX);
	setSizeY( sizeY);
}

int SFTilesSet::getSizeX() {
	return sizeX;
}

int SFTilesSet::getSizeY() {
	return sizeY;
}

float SFTilesSet::getStepX() {
	return stepX;
}

float SFTilesSet::getStepY() {
	return stepY;
}

} /* namespace sf */
