#ifndef shadow_renderer_data_transforms_H_
#define shadow_renderer_data_transforms_H_

#include "shadow/math/SFMatrix3f.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFFloat.h"
#include "shadow/system/data/objects/SFVertex3fData.h"

namespace sf{
class SFTranslateAndScaleData : public SFDataAsset<SFTransformResource>{

	SFVertex3fData* position;
	SFFloat* scale;

public:
	SFTranslateAndScaleData();

	SFTransformResource* buildResource();

	void updateResource(SFTransformResource* resource);

};

}
#endif
