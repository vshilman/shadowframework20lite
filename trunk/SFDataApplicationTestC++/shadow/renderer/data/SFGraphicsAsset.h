#ifndef shadow_renderer_data_SFGraphicsAsset_H_
#define shadow_renderer_data_SFGraphicsAsset_H_


#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/SFInitiator.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFDataObject.h"
#include "shadow/system/data/SFDataset.h"

namespace sf{
template <class T>
class SFGraphicsAsset : public SFDataAsset<T> , public SFDataset{

public:
	void setupResource() {
		SFInitiator::addInitiable(this->resource);
	}

	void disposeResource(){
		SFInitiator::addDestroyable(this->resource);

		//SFDataAsset::disposeResource();
	}
};
}
#endif
