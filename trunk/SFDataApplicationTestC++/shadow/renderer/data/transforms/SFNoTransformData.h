#ifndef shadow_renderer_data_transforms_H_
#define shadow_renderer_data_transforms_H_

#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFNamedParametersObject.h"

namespace sf{
class SFNoTransformData : public SFDataAsset<SFTransformResource>{

	SFTransformResource voidTransform=new SFTransformResource();

public:
	SFNoTransformData();
	
	SFNoTransformData* generateNewDatasetInstance() ;
	
	SFTransformResource* buildResource();

	void updateResource(SFTransformResource* resource);
	
};

}
#endif
