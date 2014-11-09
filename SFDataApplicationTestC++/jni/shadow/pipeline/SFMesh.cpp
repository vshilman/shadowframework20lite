#include "SFMesh.h"


namespace sf{

SFMesh::SFMesh(SFPrimitive* primitive){
	this->firstElement=-1;
	this->lastElement=-1;
	this->primitive=primitive;
	this->ranges=new SFIndexRange[primitive->getBlocksSize()];
	this->array=0;
}

SFMesh::SFMesh(SFPrimitive* primitive,SFPrimitiveArray* array){
	this->firstElement=-1;
	this->lastElement=-1;
	this->primitive=primitive;
	this->ranges=new SFIndexRange[primitive->getBlocksSize()];
	this->array=array;
}


SFMesh::~SFMesh(){
    delete ranges;
}

SFPrimitiveArray* SFMesh::getArray() {
    return array;
}

SFPrimitive* SFMesh::getPrimitive() {
    return primitive;
}

void SFMesh::destroy() {
	SFPipeline::getSfPipelineMemory()->destroyPrimitiveArray(this->array);
}

void SFMesh::init() {
	if(array==0)
		array=SFPipeline::getSfPipelineMemory()->generatePrimitiveArray(getPrimitive());
}


int SFMesh::getFirstElement() {
    return firstElement;
}

void SFMesh::setFirstElement(int firstElement) {
    this->firstElement = firstElement;
}

int SFMesh::getLastElement() {
    return lastElement;
}

void SFMesh::setLastElement(int lastElement) {
    this->lastElement = lastElement;
}

int SFMesh::getSize(){
    return lastElement-firstElement;
}

SFIndexRange* SFMesh::getPrimitiveDataRanges() {
    return ranges;
}

//void SFMesh::setPrimitiveDataRanges(SFIndexRange* positions) {
//    this->ranges = positions;
//}

/*void SFMesh::evaluateRanges(){

    SFPrimitive* primitive=array->getPrimitive();

    this->ranges=new SFIndexRange*[primitive->getGridsCount()];

    for (int gridIndex = 0; gridIndex < primitive->getGridsCount(); gridIndex++) {
        this->ranges[gridIndex]=new SFIndexRange(-1, -1);
    }

    SFPrimitiveIndices indices=array->generateSample();
    int* positions=primitive->getIndicesPositions();
    int* sizes=primitive->getIndicesSizes();

    for (int i = firstElement; i < lastElement; i++) {
        array->getElement(i, indices);

        for (int gridIndex = 0; gridIndex < primitive->getGridsCount(); gridIndex++) {
            for (int j = 0; j < sizes[gridIndex]; j++) {
                int index=indices.getPrimitiveIndices()[j+positions[gridIndex]];
                this->ranges[gridIndex]->insertIndex(index);
            }
        }
    }

}*/
}
