#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFFloat.h"

namespace sf{
class SFNormalBasedObjPlaneTexCoordGeometryData extends SFDataAsset<SFDerivedTexCoordFunctionuv>{

	SFFloat a=new SFFloat(1);
	SFFloat du=new SFFloat(0);
	SFFloat av=new SFFloat(0);
	SFFloat bv=new SFFloat(1);
	SFFloat cv=new SFFloat(0);
	SFFloat dv=new SFFloat(0);

	SFNormalBasedObjPlaneTexCoordGeometryData(){
		SFNamedParametersObject parametersObject=new SFNamedParametersObject();
		parametersObject.addObject("a", a);
		parametersObject.addObject("du", du);
		parametersObject.addObject("av", av);
		parametersObject.addObject("bv", bv);
		parametersObject.addObject("cv", cv);
		parametersObject.addObject("dv", dv);
		setData(parametersObject);
	}

	
	SFNormalBasedObjPlaneTexCoordGeometry buildResource() {
		return new SFNormalBasedObjPlaneTexCoordGeometry(a.getFloatValue(), du.getFloatValue(),
				av.getFloatValue(), bv.getFloatValue(), cv.getFloatValue(), dv.getFloatValue());
	}

	
	void updateResource(SFDerivedTexCoordFunctionuv resource) {
		SFNormalBasedObjPlaneTexCoordGeometry geometry=(SFNormalBasedObjPlaneTexCoordGeometry)resource;
		geometry.setA(a.getFloatValue());
		geometry.setDa(du.getFloatValue());
		geometry.setAv(av.getFloatValue());
		geometry.setBv(bv.getFloatValue());
		geometry.setCv(cv.getFloatValue());
		geometry.setDv(dv.getFloatValue());
	}
}
;
}
#endif
