/*
 * SFDataModule.h
 *
 *  Created on: 12/set/2013
 *      Author: Alessandro
 */

#ifndef SFDATAMODULE_H_
#define SFDATAMODULE_H_

#include "SFNamedParametersObject.h"
#include "SFDataset.h"

namespace sf {

template <class T>
class SFDataModule : public SFDataset{
	SFNamedParametersObject sfDataObject;

public:
	SFDataModule(){
		sfDataObject=0;
	}
	virtual ~SFDataModule();

	virtual T* getResource()=0;

	virtual SFDataset* generateNewDatasetInstance()=0;

//	template <class G>
//	G* getParameter(string parameter){
//		return (G)sfDataObject->getObject(parameter);
//	}

	SFNamedParametersObject* getSFDataObject() {
		return &sfDataObject;
	}

//	string getType() {
//		return getClass().getSimpleName();
//	}

	void setData(SFNamedParametersObject* sfDataObject) {
		this->sfDataObject = *sfDataObject;
	}
};

} /* namespace sf */
#endif /* SFDATAMODULE_H_ */
