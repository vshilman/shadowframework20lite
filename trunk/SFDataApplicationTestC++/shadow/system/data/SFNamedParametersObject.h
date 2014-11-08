/*
 * SFNamedParametersObject.h
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro
 */

#ifndef SFNAMEDPARAMETERSOBJECT_H_
#define SFNAMEDPARAMETERSOBJECT_H_

#include "SFDataObject.h"
#include <vector>
#include <string>
using namespace std;


namespace sf{
class SFNamedParametersObject : public SFDataObject{

	//vector<string> objectNames;
	vector<SFDataObject*> dataObjects;

public:
	SFNamedParametersObject();

	virtual ~SFNamedParametersObject();

	void addObject(SFDataObject* dataObject);

	SFNamedParametersObject* clone();

	int size();

	SFDataObject* getDataObject(int index);

//	SFDataObject* getObject(string parameter){
//		return dataObjects->objectNames.indexOf(parameter));
//	}

	void readFromStream(SFInputStream* stream) {
		int n=dataObjects.size();
		for (int i = 0; i < n; i++) {
			dataObjects[i]->readFromStream((SFInputStream*)stream);
		}
	}

	//void writeOnStream(SFOutputStream* stream);
};
}

#endif /* SFNAMEDPARAMETERSOBJECT_H_ */
