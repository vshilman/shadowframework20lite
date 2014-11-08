/*
 * SFIResource.cpp
 *
 *  Created on: 11/set/2013
 *      Author: Alessandro
 */

#include "SFResource.h"


namespace sf {

	SFResource::SFResource(int size,SFUpdatable* updatable) {
		this->updatable=updatable;
		this->asset=0;
	}

	SFResource::SFResource() {
		this->updatable=0;
		this->asset=0;
	}

	void SFResource::setAsset(SFAsset<void*>* asset) {
		this->asset = asset;
	}

	void SFResource::resourceChanged(){
		//System.err.println("resourceChanged "+this+" "+this.usingResources.size()+" "+updatable);
		if(updatable!=0)
			SFUpdater::addUpdatable(updatable);
		for (unsigned int i = 0; i < usingResources.size(); i++) {
			usingResources.at(i)->resourceChanged();
		}
	}

	void SFResource::checkIndex(unsigned int index){
		while(usedResources.size()<=index){
			usedResources.push_back(0);
		}
	}


	SFResource* SFResource::getResource(int index){
		return usedResources.at(index);
	}

	void SFResource::setResource(int index,SFResource* resource){
		checkIndex(index);
		if(usedResources.at(index)==resource){
			return;
		}
		if(usedResources.at(index)==0){
			usedResources[index]= resource;//stop
			resource->lock(this);
		}else{
			usedResources.at(index)->free(this);
			usedResources[index]=resource;//stop
			resource->lock(this);
			resourceChanged();
		}
	}

	void SFResource::free(SFResource* res){
		std::vector<SFResource*>::iterator position=std::find(usedResources.begin(),usedResources.end(),res);
		usingResources.erase(position);
		if(usingResources.size()==0){
			if(asset!=0)
				asset->disposeResource();
		}
	}

	void SFResource::lock(SFResource* resource){
		usingResources.push_back(resource);
	}

};
