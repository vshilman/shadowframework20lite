#ifndef shadow_renderer_data_transforms_H_
#define shadow_renderer_data_transforms_H_

#include "shadow/math/SFMatrix3f.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFVertex3fData.h"

namespace sf{
class SFTranslationData extends SFDataAsset<SFTransformResource>{

	SFVertex3fData position=new SFVertex3fData();

	SFTranslationData(){
		setup();
	}

	SFTranslationData(float x,float y,float z){
		setup();
		position.getVertex3f().set3f(x,y,z);
	}

	void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("position", position);
		setData(parameters);
	}

	
	SFTransformResource buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();

		SFTransformResource transform=new SFTransformResource();
		transform.setMatrix(matrix);
		transform.setPosition(this->position.getVertex3f());

		return transform;
	}

	
	void updateResource(SFTransformResource resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
