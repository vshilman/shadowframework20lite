/*
 * SFLibraryReference.h
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro
 */

#ifndef SFLIBRARYREFERENCE_H_
#define SFLIBRARYREFERENCE_H_

#include "SFDataAsset.h"
#include "SFDataCenter.h"
#include "objects/SFPrimitiveType.h"

#include <string>
using namespace std;


namespace sf{

const string NULL_REFERENCE("0");
const string MISSING_REFERENCE("0=0");

template <class T>
class SFLibraryReference : public SFPrimitiveType{

	string datasetName;
	SFDataAsset<T>* dataset ;

public:

	SFLibraryReference() {
		this->dataset=0;
		datasetName=NULL_REFERENCE;
	}

	virtual ~SFLibraryReference(){

	}

	SFLibraryReference(SFDataAsset<T>* dataset) {
		this->dataset=dataset;
	}


	SFLibraryReference<T>* clone() {
		return new SFLibraryReference<T>();
	}

	void readFromStream(SFInputStream* stream) {
		this->datasetName = stream->readName();
		if (datasetName.compare(MISSING_REFERENCE)==0) {
			this->datasetName=NULL_REFERENCE;
			this->dataset=0;
		}else if (isNullReference()) {
			this->dataset = (SFDataAsset<T>) (SFDataCenter::getDataCenter()->readDataset(stream));
		}
	}

	bool isNullReference() {
		return datasetName.compare(NULL_REFERENCE)==0;
	}

	void writeOnStream(SFOutputStream* stream) {
		if (isNullReference() && dataset==0) {
			stream->writeName(MISSING_REFERENCE);
			return;
		}
		stream->writeName(this->datasetName);

		if (isNullReference()) {
			SFDataCenter::getDataCenter()->writeDataset(stream, dataset);
		}
	}

	void setReference(string name) {
		this->datasetName = name;
	}

	string getReference() {
		return datasetName;
	}

	//TODO: rename as setDataAsset
	void setDataset(SFDataAsset<T> dataset) {
		this->dataset = dataset;
	}

	//TODO: rename as getDataAsset
	SFDataAsset<T> getDataset() {
		return dataset;
	}

	T* getResource() {
		if (!isNullReference()) {
			SFDataAsset<T> asset=(SFDataAsset<T>)SFDataCenter::getDataCenter()->makeDatasetAvailable(datasetName);
			return asset.getResource();
		}
		if(dataset==0)
			return 0;
		return dataset->getResource();
	}

	T* retrieveDataset() {
		return getResource();
	}
};
}

#endif /* SFLIBRARYREFERENCE_H_ */
