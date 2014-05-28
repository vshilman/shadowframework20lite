#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/geometry/geometries.SFParametrizedGeometry.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFFloatArray.h"
#include "shadow/system/data.objects.SFShortByteField.h"
#include "shadow/system/data.objects.SFString.h"
#include "shadow/tmp/SFQuadsGridGeometry.h"

namespace sf{
class SFQuadsGridGeometryData extends SFGraphicsAsset<SFGeometry>{

	Array u_splits=new SFFloatArray(0);
	Array v_splits=new SFFloatArray(0);
	SFShortByteField NuNv=new SFShortByteField((short)0);
	SFString primitive=new SFString();

	SFParametrizedGeometry surfaceGeometry;

	SFQuadsGridGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("u_splits", u_splits);
		parameters.addObject("v_splits", v_splits);
		parameters.addObject("NuNv", NuNv);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}

	
	SFParametrizedGeometry buildResource() {

		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry();

		updateResource(gridGeometry);

		return surfaceGeometry;
	}

	
	void updateResource(SFGeometry resource) {
		SFQuadsGridGeometry gridGeometry=(SFQuadsGridGeometry)resource;

		if(u_splits.getFloatValues().length!=0){
			gridGeometry.getQuadsGrid().setU_splits( u_splits.getFloatValues());
		}
			gridGeometry.getQuadsGrid().setNu( NuNv.getByte(0));
		}
		if(v_splits.getFloatValues().length!=0){
			gridGeometry.getQuadsGrid().setU_splits( v_splits.getFloatValues());
		}
			gridGeometry.getQuadsGrid().setNv( NuNv.getByte(1));
		}

		surfaceGeometry=new SFParametrizedGeometry(gridGeometry);
		SFPrimitive primitive=SFPipeline.getPrimitive(this->primitive.getString());
		surfaceGeometry.setPrimitive(primitive);
		gridGeometry.setPrimitive(primitive.getConstructionPrimitive());

	}

	SFFloatArray getU_splits() {
		return u_splits;
	}

	SFFloatArray getV_splits() {
		return v_splits;
	}

	void setNu(int nu){
		NuNv.setByte(1, nu);
	}

	void setNv(int nv){
		NuNv.setByte(0, nv);
	}

	int getNu() {
		return NuNv.getByte(1);
	}

	int getNv() {
		return NuNv.getByte(0);
	}

	void setNuNv(int nu,int nv){
		setNu(nu);
		setNv(nv);
	}

	SFString getPrimitive() {
		return primitive;
	}


}
;
}
#endif
