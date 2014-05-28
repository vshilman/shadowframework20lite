#ifndef shadow_renderer_data_transforms_H_
#define shadow_renderer_data_transforms_H_

#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFNoTransformData extends SFDataAsset<SFTransformResource>{

	SFTransformResource voidTransform=new SFTransformResource();

	SFNoTransformData() {
		setData(new SFNamedParametersObject());
	}

	
	SFNoTransformData generateNewDatasetInstance() {
		return this;
	}

	
	SFTransformResource buildResource() {
		return voidTransform;
	}

	
	void updateResource(SFTransformResource resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
