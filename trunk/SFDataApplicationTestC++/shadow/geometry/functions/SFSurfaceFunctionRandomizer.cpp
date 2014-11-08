/*
 * SFSurfaceFunctionRandomizer.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/functions/SFSurfaceFunctionRandomizer.h"

namespace sf {

SFSurfaceFunctionRandomizer::SFSurfaceFunctionRandomizer(int seed) {
	randomizer.setSeed(seed);
}

SFSurfaceFunctionRandomizer::~SFSurfaceFunctionRandomizer(){}

float SFSurfaceFunctionRandomizer::getValue(float u, float v) {
	return randomizer.randomFloat();
}

void SFSurfaceFunctionRandomizer::setSeed(int seed){
	randomizer.setSeed(seed);
	randomizer.reset();
}


float SFSurfaceFunctionRandomizer::getX(float u, float v) {
	return u;
}


float SFSurfaceFunctionRandomizer::getY(float u, float v) {
	return v;
}


float SFSurfaceFunctionRandomizer::getZ(float u, float v) {
	return getValue(u, v);
}

} /* namespace sf */
