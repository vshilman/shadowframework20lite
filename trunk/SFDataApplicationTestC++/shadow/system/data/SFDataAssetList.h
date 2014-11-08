/*
 * SFDataAssetList.h
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro
 */

#ifndef SFDATAASSETLIST_H_
#define SFDATAASSETLIST_H_

#include <vector>
using namespace std;

#include "SFDataCenter.h"
#include "SFDataObject.h"
#include "SFDataAsset.h"

namespace sf{

template <class T>
class SFDataAssetList : public vector<T*>,public SFDataObject{
public:
	SFDataAssetList();
	virtual ~SFDataAssetList();

	void readFromStream(SFInputStream* stream) {
		int n=stream->readShort();
		for (int i = 0; i < n; i++) {
			T* t=(T*)SFDataCenter::getDataCenter()->readDataset(stream);
			push_back(t);
		}
	}

	void writeOnStream(SFOutputStream* stream) {
		int n=(int)(this->size());
		stream->writeShort(n);
		//stream->writeShort((short)(size()));
		for (unsigned int i = 0; i < n; i++) {
			SFDataCenter::getDataCenter()->writeDataset(stream,(SFDataset*)(this[i]));
			//SFDataCenter::getDataCenter()->writeDataset(stream, (SFDataset*)at(i));
		}
	}

	SFDataAssetList<T>* clone(){
		return new SFDataAssetList<T>();
	}
};
}

#endif /* SFDATAASSETLIST_H_ */
