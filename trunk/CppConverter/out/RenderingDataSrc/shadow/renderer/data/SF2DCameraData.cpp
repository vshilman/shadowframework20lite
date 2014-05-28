#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFCamera.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFFloat.h"

namespace sf{
class SF2DCameraData extends SFDataAsset<SFCamera>{

	SFFloat leftL=new SFFloat(0);
	SFFloat upL=new SFFloat(0);

	SF2DCameraData() {
		prepare();
	}
	SF2DCameraData(float leftL,float upL) {
		prepare();
		setDimensions(leftL, upL);
	}

	void prepare() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("leftL", leftL);
		parameters.addObject("upL", upL);
		setData(parameters);
	}

	
	SFCamera buildResource() {
		SFCamera camera=new SFCamera(new SFVertex3f(0,0,0),
				new SFVertex3f(1,0,0),new SFVertex3f(0,1,0),new SFVertex3f(0,0,1),0,leftL.getFloatValue(),
				upL.getFloatValue());
		return camera;
	}

	
	void updateResource(SFCamera resource) {
		// TODO Auto-generated method stub	
	}

	void setDimensions(float leftL,float upL){
		this->leftL.setFloatValue(leftL);
		this->upL.setFloatValue(upL);
	}
}
;
}
#endif
