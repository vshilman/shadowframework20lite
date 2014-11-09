/*
 * SFDataset.h
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro
 */

#ifndef SFDATASET_H_
#define SFDATASET_H_

#include <string>
using namespace std;

#include "SFDataObject.h"


namespace sf{
class SFDataset {

public:
	virtual ~SFDataset(){};

	virtual SFDataObject* getSFDataObject()=0;

	virtual SFDataset* generateNewDatasetInstance()=0;
};
}

#endif /* SFDATASET_H_ */
