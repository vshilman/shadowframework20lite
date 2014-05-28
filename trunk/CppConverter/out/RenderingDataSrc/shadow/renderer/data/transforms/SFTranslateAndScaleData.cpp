#ifndef shadow_renderer_data_transforms_H_
#define shadow_renderer_data_transforms_H_

#include "shadow/math/SFMatrix3f.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFFloat.h"
#include "shadow/system/data.objects.SFVertex3fData.h"

namespace sf{
class SFTranslateAndScaleData extends SFDataAsset<SFTransformResource>{

	SFVertex3fData position=new SFVertex3fData();
	SFFloat scale=new SFFloat(1);

	SFTranslateAndScaleData(){
		setup();
	}

	SFTranslateAndScaleData(float x,float y,float z,float scale){
		setup();
		place(x, y, z, scale);
	}

	void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("position", position);
		parameters.addObject("scale", scale);
		setData(parameters);
	}

	void place(float x,float y,float z,float scale){
		position.getVertex3f().set3f(x,y,z);
		this->scale.setFloatValue(scale);
	}

	
	SFTransformResource buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();
		matrix.mult(scale.getFloatValue());

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
