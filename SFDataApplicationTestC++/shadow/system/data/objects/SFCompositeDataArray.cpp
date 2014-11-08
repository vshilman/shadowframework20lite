
#include "SFCompositeDataArray.h"

#include <iostream>

namespace sf{
SFCompositeDataArray::SFCompositeDataArray() {
	//check / solve this
	generateData();
}

SFCompositeDataArray::~SFCompositeDataArray(){
	for(unsigned int i=0;i<dataObject.size();i++){
		delete dataObject[i];
	}
}

void SFCompositeDataArray::addDataObject(SFDataObject* object){
	this->dataObject.push_back(object);
}

vector<SFDataObject*>* SFCompositeDataArray::getDataObject() {
	return &dataObject;
}

int SFCompositeDataArray::elementsSize() {
	return dataObject.size();
}

void SFCompositeDataArray::readFromStream(SFInputStream* stream) {
	for (unsigned int i = 0; i < dataObject.size(); i++) {
		cout << "SFCompositeDataArray::readFromStream " << i << " "<< dataObject.size()<< " "<< dataObject.at(i)<< endl;
		dataObject.at(i)->readFromStream(stream);
	}
}
}
