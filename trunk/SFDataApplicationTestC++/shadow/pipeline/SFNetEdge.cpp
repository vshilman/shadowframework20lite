/*
 * SFNetEdge.cpp
 *
 *  Created on: 05/giu/2013
 *      Author: Alessandro
 */

#include "SFNetEdge.h"

namespace sf{

SFNetEdge::SFNetEdge() {
	this->size=1;
	this->indices=0;
}

SFNetEdge::SFNetEdge(int size) {
	this->size=size;
	this->indices=0;
}


SFNetEdge::~SFNetEdge() {

}


int SFNetEdge::getSize() {
	return size;
}

short** SFNetEdge::getIndices() {
	return indices;
}

void SFNetEdge::setIndices(short** indices) {
	this->indices = indices;
}

void SFNetEdge::setSize(int size) {
	this->size = size;
}

}
