/*
 * SFGeometry.cpp
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#include "SFGeometry.h"

namespace sf {


SFGeometry::SFGeometry(){
	rendering_hint=0;
	available_lods=0;
	fatherGeometry=0;
	changed=true;
}

SFGeometry::~SFGeometry(){
	for(unsigned int i=0;i<sonGeometries.size();i++){
		delete sonGeometries[i];
	}
}


int SFGeometry::getRendering_hint() {
	return rendering_hint;
}

void SFGeometry::setRendering_hint(int rendering_hint) {
	this->rendering_hint = rendering_hint;
}

int SFGeometry::getAvailable_lods() {
	return available_lods;
}

void SFGeometry::setAvailable_lods(int available_lods) {
	this->available_lods = available_lods;
}

int SFGeometry::getSonsCount(){
	return sonGeometries.size();
}

int SFGeometry::addSon(SFGeometry* son){
	sonGeometries.push_back(son);
	son->setFatherGeometry(this);
	return sonGeometries.size()-1;
}

SFGeometry* SFGeometry::getSon(int index){
	return sonGeometries.at(index);
}

SFGeometry* SFGeometry::getFatherGeometry() {
	return fatherGeometry;
}

void SFGeometry::setFatherGeometry(SFGeometry* fatherGeometry) {
	this->fatherGeometry = fatherGeometry;
}


void SFGeometry::rebuild(){
	compile();
	for (unsigned int i = 0; i < sonGeometries.size(); i++) {
		sonGeometries[i]->rebuild();
	}
}

} /* namespace sf */
