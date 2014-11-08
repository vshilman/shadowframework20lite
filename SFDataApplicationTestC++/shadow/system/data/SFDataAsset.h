/*
 * SFDataAsset.h
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro
 */

#ifndef SFDATAASSET_H_
#define SFDATAASSET_H_

#include "../SFUpdatable.h"
#include "../SFAsset.h"
#include "../SFIResource.h"
#include "SFDataModule.h"

#include <vector>
using namespace std;

namespace sf{

template <class T>
class SFDataAsset :public SFDataModule<T>, SFAsset<T>, SFUpdatable{

protected:
	T* resource ;
	static bool updateMode;

public:
	SFDataAsset(){
		this->resource=0;
		this->updateMode=true;
	}
	virtual ~SFDataAsset(){

	}

	void setupResource(){

	}


	static bool isUpdateMode() {
		return updateMode;
	}

	virtual T* buildResource()=0;

	virtual void updateResource(T*)=0;

	virtual SFDataAsset* generateNewDatasetInstance()=0;

	T* getResource() {
		if (resource == 0 || updateMode) {
			resource = buildResource();
			if(((SFIResource*)resource)->getResource()!=0)
				((SFIResource*)resource)->getResource()->setAsset((SFAsset<void*>*)this);

			setupResource();
		}

		return resource;
	}

	void update() {

		if(resource==0){
			resource=buildResource();
		}else{
			disposeResource();
			updateResource(resource);
			setupResource();
			((SFIResource*)resource)->getResource()->resourceChanged();
		}
//		unsetResource();
//		resource = buildResource();
//		setupResource();
//		for (int i = 0; i < listeners.size(); i++) {
//			listeners.get(i).resourceChanged(resource);
//		}
	}

//	void releaseResource(SFAssetListener<T>* listener){
//		listeners.remove(listener);
//		if(listeners.size()==0){
//			unsetResource();
//			resource=0;
//		}
//	}

	void disposeResource(){

	}

	static void setUpdateMode(bool updateMode) {
		SFDataAsset::updateMode = updateMode;
	}

};

}

#endif /* SFDATAASSET_H_ */
