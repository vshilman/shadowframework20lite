
#ifndef SFDATAASSETOBJECT_H_
#define SFDATAASSETOBJECT_H_

#include "objects/SFDatasetObject.h"

namespace sf{
template <class T>
class SFDataAssetObject :public SFDatasetObject<SFDataAsset<T> >{
public:
	SFDataAssetObject(SFDataAsset<T>* dataset){

	}

	virtual ~SFDataAssetObject();


	SFDataAssetObject(SFDataAsset<T> dataset) {
		super(dataset);
	}

	T* getResource() {
		return (T*)(this->getDataset()->getResource());
	}
};
}

#endif /* SFDATAASSETOBJECT_H_ */
