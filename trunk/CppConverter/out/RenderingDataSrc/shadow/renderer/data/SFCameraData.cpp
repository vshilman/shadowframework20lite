#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/renderer/SFCamera.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFFloat.h"
#include "shadow/system/data.objects.SFVertex3fData.h"

namespace sf{
class SFCameraData extends SFDataAsset<SFCamera>{

	SFVertex3fData focus=new SFVertex3fData();
	SFVertex3fData left=new SFVertex3fData();
	SFVertex3fData up=new SFVertex3fData();
	SFVertex3fData dir=new SFVertex3fData();
	SFFloat distance=new SFFloat(0);
	SFFloat leftL=new SFFloat(0);
	SFFloat upL=new SFFloat(0);

	SFCameraData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("focus", focus);
		parameters.addObject("left", left);
		parameters.addObject("up", up);
		parameters.addObject("dir", dir);
		parameters.addObject("distance", distance);
		parameters.addObject("leftL", leftL);
		parameters.addObject("upL", upL);
		setData(parameters);
	}

	
	SFDataset generateNewDatasetInstance() {
		return new SFCameraData();
	}

	
	SFCamera buildResource() {
		return new SFCamera(focus.getVertex3f(), dir.getVertex3f(), left.getVertex3f(),
				up.getVertex3f(), leftL.getFloatValue(), upL.getFloatValue(), distance.getFloatValue());
	}

	
	void updateResource(SFCamera resource) {
		// TODO Auto-generated method stub

	}

	void setCamera(SFCamera camera){
		focus.getVertex3f().set(camera.getF());
		left.getVertex3f().set(camera.getLeft());
		dir.getVertex3f().set(camera.getDir());
		up.getVertex3f().set(camera.getUp());
		distance.setFloatValue(camera.getDistance());
		leftL.setFloatValue(camera.getLeftL());
		upL.setFloatValue(camera.getUpL());
	}
}
;
}
#endif
