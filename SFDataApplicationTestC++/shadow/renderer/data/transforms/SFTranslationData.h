#ifndef shadow_renderer_data_transforms_H_
#define shadow_renderer_data_transforms_H_

#include "shadow/math/SFMatrix3f.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFVertex3fData.h"

namespace sf{
class SFTranslationData : public SFDataAsset<SFTransformResource>{

	SFVertex3fData position=new SFVertex3fData();
public:
	SFTranslationData(){
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
			parameters->addObject( position);
			setData(parameters);
	}
	
	SFTransformResource* buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();

		SFTransformResource* transform=new SFTransformResource();
		transform->setMatrix(matrix);
		transform->setPosition(this->position.getVertex3f());

		return transform;
	}
	
	void updateResource(SFTransformResource* resource) {
		// TODO Auto-generated method stub

	}
};

}
#endif
