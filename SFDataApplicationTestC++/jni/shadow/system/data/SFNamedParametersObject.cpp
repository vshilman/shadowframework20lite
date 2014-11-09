/*
 * SFNamedParametersObject.cpp
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro
 */

#include "SFNamedParametersObject.h"


namespace sf{


SFNamedParametersObject::SFNamedParametersObject(){

}

SFNamedParametersObject::~SFNamedParametersObject(){

}

void SFNamedParametersObject::addObject(SFDataObject* dataObject){
	//objectNames.push_back(objectName);
	dataObjects.push_back(dataObject);
}


SFNamedParametersObject* SFNamedParametersObject::clone()  {
	SFNamedParametersObject* namedParametricObject=new SFNamedParametersObject();
	for (unsigned i = 0; i < dataObjects.size(); i++) {
		namedParametricObject->addObject( dataObjects.at(i)->clone());
	}

	return namedParametricObject;
}

int SFNamedParametersObject::size(){
	return dataObjects.size();
}

SFDataObject* SFNamedParametersObject::getDataObject(int index) {
	return this->dataObjects[index];
}




//void SFNamedParametersObject::writeOnStream(SFOutputStream* stream) {
//	int n=dataObjects.size();
//	for (int i = 0; i < n; i++) {
//		dataObjects[i]->writeOnStream(stream);
//	}
//}

}
