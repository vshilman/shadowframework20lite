#include "SFMeshData.h"
#include "../system/SFValuesArray.h"
#include "../system/SFArray.h"


namespace sf{
SFMeshData::SFMeshData(SFPrimitiveArray* array) {
    this->array = array;
    this->firstElement=0;
    this->lastElement=0;
    this->indices=0;
    this->gridIndex=0;
}


SFMeshData::SFMeshData(SFPrimitiveArray* array, int firstElement, int lastElement,
        int dataIndex) {
    this->array = array;
    this->firstElement = firstElement;
    this->lastElement = lastElement;
    this->gridIndex = dataIndex;
    this->indices=0;
}

SFMeshData::SFMeshData(SFPrimitiveArray* array, short* indices, int gridIndex) {
    this->array = array;
    this->indices = indices;
    this->gridIndex = gridIndex;
    this->firstElement=0;
    this->lastElement=0;
}


SFMeshData::~SFMeshData(){
    SFPipeline::getSfPipelineMemory()->destroyPrimitiveArray(0);
    delete indices;
}

int SFMeshData::getGridN(){
    return array->getPrimitive()->getGrid(gridIndex)->getN();
}

SFPrimitive* SFMeshData::getPrimitive(){
    return getArray()->getPrimitive();
}

SFValuenf* SFMeshData::generateSample(){
	SFArray<SFValuenf>* vArray=array->getPrimitiveData(gridIndex);
    return vArray->generateSample();
	//return array->getPrimitiveData(gridIndex)->generateSample();
}


int SFMeshData::getIndex(int index){
    if(indices==0)
        return index+firstElement;
    else
        return indices[index];
}

void SFMeshData::setElement(int index,SFValuenf* value){
	SFArray<SFValuenf>* vArray=array->getPrimitiveData(gridIndex);
	vArray->setElement(getIndex(index), value);
}

void SFMeshData::getElement(int index,SFValuenf* value){
	SFArray<SFValuenf>* vArray=array->getPrimitiveData(gridIndex);
	vArray->getElement(getIndex(index), value);
}

SFArray<SFValuenf>* SFMeshData::getArrayData(){
    return array->getPrimitiveData(gridIndex);
}

SFPrimitiveArray* SFMeshData::getArray() {
    return array;
}
void SFMeshData::setArray(SFPrimitiveArray* array) {
    this->array = array;
}
int SFMeshData::getFirstElement() {
    return firstElement;
}
void SFMeshData::setFirstElement(int firstElement) {
    this->firstElement = firstElement;
}
int SFMeshData::getLastElement() {
    return lastElement;
}
void SFMeshData::setLastElement(int lastElement) {
    this->lastElement = lastElement;
}

int SFMeshData::getGridIndex() {
    return gridIndex;
}

void SFMeshData::setGridIndex(int gridIndex) {
    this->gridIndex = gridIndex;
}

void SFMeshData::setIndices(short* indices) {
    this->indices = indices;
}

int SFMeshData::getSize(){
    return lastElement-firstElement;
}

}
