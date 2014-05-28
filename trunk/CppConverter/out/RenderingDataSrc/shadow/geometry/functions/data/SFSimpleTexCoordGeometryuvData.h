#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/functions.SFSimpleTexCoordGeometryuv.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFFloat.h"

namespace sf{
class SFSimpleTexCoordGeometryuvData extends SFDataAsset<SFSurfaceFunction>{

	SFFloat du=new SFFloat(1);
	SFFloat dv=new SFFloat(1);

	SFSimpleTexCoordGeometryuvData(){
		prepare();
	}

	SFSimpleTexCoordGeometryuvData(float du,float dv){
		prepare();
		setDu(du);
		setDv(dv);
	}

	void prepare() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("du", du);
		parameters.addObject("dv", dv);
		setData(parameters);
	}

	
	SFSurfaceFunction buildResource() {
		return new SFSimpleTexCoordGeometryuv(du.getFloatValue(), dv.getFloatValue());
	}

	
	void updateResource(SFSurfaceFunction resource) {
		SFSimpleTexCoordGeometryuv surface = (SFSimpleTexCoordGeometryuv) resource;
		surface.setDu(du.getFloatValue());
		surface.setDv(dv.getFloatValue());
	}

	void set(float du,float dv) {
		this->du.setFloatValue(du);
		this->dv.setFloatValue(dv);
	}

	void setDu(float du) {
		this->du.setFloatValue(du);
	}

	void setDv(float dv) {
		this->dv.setFloatValue(dv);
	}
}
;
}
#endif
