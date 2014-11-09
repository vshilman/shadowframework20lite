/*
 * SFNetPolygon.cpp
 *
 *  Created on: 05/giu/2013
 *      Author: Alessandro
 */

#include "SFNetPolygon.h"


namespace sf{

SFNetPolygon::SFNetPolygon() {
	this->edges = 0;
	this->edgesLength = 0;
	this->array=0;
	this->subPolygons=0;
	this->subPolygonsLength=0;
	this->updateData=0;
	this->update_=0;
}

SFNetPolygon::SFNetPolygon(SFNetEdge* edges,int length) {
	this->edges = edges;
	this->edgesLength = length;
	this->array=0;
	this->subPolygons=0;
	this->subPolygonsLength=0;
	this->updateData=0;
	this->update_=0;
}

SFNetPolygon::~SFNetPolygon() {
}

SFNetEdge* SFNetPolygon::getEdges() {
	return edges;
}

int SFNetPolygon::getEdgesLength() {
	return edgesLength;
}

void SFNetPolygon::setEdges(SFNetEdge* edges,int length) {
	this->edges = edges;
	this->edgesLength=length;
}

SFNetPolygon* SFNetPolygon::getSubPolygons() {
	return subPolygons;
}

int SFNetPolygon::getSubPolygonsLength() {
	return subPolygonsLength;
}

void SFNetPolygon::setSubPolygons(SFNetPolygon* subPolygons,int length) {
	this->subPolygons = subPolygons;
	this->subPolygonsLength = length;
}

SFNetUpdate* SFNetPolygon::getUpdate() {
	return update_;
}

void SFNetPolygon::setUpdate(SFNetUpdate* update) {
	this->update_ = update;
}

void SFNetPolygon::init(){
	this->update_->init(this);
	for(unsigned int i =0; i<subPolygonsLength;i++){
		this->subPolygons[i].init();
	}
}

void SFNetPolygon::update(){
	this->update_->update(this);
	for(unsigned int i =0; i<subPolygonsLength;i++){
		this->subPolygons[i].update();
	}
}

void SFNetPolygon::setUpdateData(void* updateData) {
	this->updateData = updateData;
}

void* SFNetPolygon::getUpdateData() {
	return updateData;
}

SFPrimitiveArray* SFNetPolygon::getArray() {
	return array;
}

void SFNetPolygon::setArray(SFPrimitiveArray* array) {
	this->array = array;
}

}
