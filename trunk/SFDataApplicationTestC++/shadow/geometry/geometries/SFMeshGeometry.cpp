/*
 * SFMeshGeometry.cpp
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/geometries/SFMeshGeometry.h"

namespace sf {


SFMeshGeometry::~SFMeshGeometry() {
	// TODO Auto-generated destructor stub
}




SFMeshGeometry::SFMeshGeometry(){
	primitive=0;
	compiledIndex=-1;
}

SFMeshGeometry::SFMeshGeometry(SFPrimitive* primitive) {
	this->primitive=primitive;
	compiledIndex=-1;
}

SFMeshGeometry::SFMeshGeometry(SFMesh mesh) {
	this->mesh=mesh;
	this->primitive=mesh.getPrimitive();
	compiledIndex=-1;
}


SFResource* SFMeshGeometry::getResource() {
	return &resource;
}


void SFMeshGeometry::setPrimitive(SFPrimitive* primitive) {
	this->primitive=primitive;
}


SFPrimitive* SFMeshGeometry::getPrimitive() {
	return primitive;
}


void SFMeshGeometry::drawGeometry(int lod) {
	//lod is still ignored
	//this is much ok...

	if(mesh.getPrimitive()!=0){
		SFPipeline::getSfPipelineGraphics()->setLod(lod);
//			if(compiledIndex!=-1)
//				SFPipeline.getSfPipelineGraphics().drawCompiledPrimitives(getArray(), compiledIndex);
//			else if(mesh.getFirstElement()!=-1)
		SFPipeline::getSfPipelineGraphics()->drawPrimitives(mesh.getArray(),mesh.getFirstElement(),mesh.getLastElement()-mesh.getFirstElement());
	}
}


void SFMeshGeometry::compile() {
}


void SFMeshGeometry::update() {
	//Do nothing
}



void SFMeshGeometry::decompile() {
	//destroy mesh
	mesh.destroy();
}


void SFMeshGeometry::init() {

	if(mesh.getPrimitive()==0){
		mesh=SFMesh(this->primitive);
		mesh.init();

	}

	if(mesh.getFirstElement()==-1){
		setFirstElement(getArray()->getElementsCount());
		int* rangePositions=new int[getPrimitive()->getGridsCount()];
		for (int i = 0; i < getPrimitive()->getGridsCount(); i++) {
			SFValuesArray* valuesArray=getArray()->getPrimitiveData(i);
			rangePositions[i]=((SFArray<SFValuenf>*)valuesArray)->getElementsCount();
		}

		compile();

		setLastElement(getArray()->getElementsCount());
		SFIndexRange* ranges=mesh.getPrimitiveDataRanges();
		for (int i = 0; i < getPrimitive()->getGridsCount(); i++) {
			SFValuesArray* valuesArray=getArray()->getPrimitiveData(i);
			int rangePosition=((SFArray<SFValuenf>*)valuesArray)->getElementsCount();
			ranges[i]=SFIndexRange(rangePositions[i], rangePosition-rangePositions[i]);
		}

		update();

		compiledIndex=SFPipeline::getSfPipelineGraphics()->compilePrimitiveArray(
				getArray(),getFirstElement(), getLastElement());

	}else{
//				int[] rangePositions=new int[getPrimitive().getGridsCount()];
//				for (int i = 0; i < rangePositions.length; i++) {
//					rangePositions[i]=getArray().getPrimitiveData(i).getElementsCount();
//				}
			//setLastElement(getArray().getElementsCount());
			SFIndexRange* ranges=getMesh().getPrimitiveDataRanges();
			for (int i = 0; i < getPrimitive()->getGridsCount(); i++) {
				SFValuesArray* valuesArray=getArray()->getPrimitiveData(i);
				int rangePosition=((SFArray<SFValuenf>*)valuesArray)->getElementsCount();
				ranges[i]=SFIndexRange(0, rangePosition);
			}

	}
}

//	boolean isOn2D(float x,float y){
//		return getArray()->isOn2D(getMesh().getFirstElement(), getMesh().getLastElement(), x, y);
//	}


void SFMeshGeometry::destroy() {
	if(mesh.getFirstElement()!=-1){
		decompile();
	}
}


int SFMeshGeometry::getCompiledIndex() {
	return compiledIndex;
}

SFMesh SFMeshGeometry::getMesh() {
	return mesh;
}

int SFMeshGeometry::getFirstElement() {
	return mesh.getFirstElement();
}

int SFMeshGeometry::getElementsCount() {
	return mesh.getLastElement()-mesh.getFirstElement();
}


void SFMeshGeometry::setFirstElement(int firstElement) {
	this->mesh.setFirstElement(firstElement);
}


int SFMeshGeometry::getLastElement() {
	return mesh.getLastElement();
}


void SFMeshGeometry::setLastElement(int lastElement) {
	this->mesh.setLastElement(lastElement);
}

SFPrimitiveArray* SFMeshGeometry::getArray() {
	return mesh.getArray();
}

} /* namespace sf */
