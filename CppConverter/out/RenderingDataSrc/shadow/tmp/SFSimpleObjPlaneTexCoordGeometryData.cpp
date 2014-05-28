#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFFloat.h"

namespace sf{
class SFSimpleObjPlaneTexCoordGeometryData extends SFDataAsset<SFDerivedTexCoordFunctionuv>{

	SFFloat au=new SFFloat(1);
	SFFloat bu=new SFFloat(0);
	SFFloat cu=new SFFloat(0);
	SFFloat du=new SFFloat(0);
	SFFloat av=new SFFloat(0);
	SFFloat bv=new SFFloat(1);
	SFFloat cv=new SFFloat(0);
	SFFloat dv=new SFFloat(0);

	SFSimpleObjPlaneTexCoordGeometryData(){
		SFNamedParametersObject parametersObject=new SFNamedParametersObject();
		parametersObject.addObject("au", au);
		parametersObject.addObject("bu", bu);
		parametersObject.addObject("cu", cu);
		parametersObject.addObject("du", du);
		parametersObject.addObject("av", av);
		parametersObject.addObject("bv", bv);
		parametersObject.addObject("cv", cv);
		parametersObject.addObject("dv", dv);
		setData(parametersObject);
	}

	
	SFSimpleObjPlaneTexCoordGeometry buildResource() {
		return new SFSimpleObjPlaneTexCoordGeometry(au.getFloatValue(), bu.getFloatValue(), cu.getFloatValue(), du.getFloatValue(),
				av.getFloatValue(), bv.getFloatValue(), cv.getFloatValue(), dv.getFloatValue());
	}

	
	void updateResource(SFDerivedTexCoordFunctionuv resource) {

	}

	void setAu(float au) {
		this->au.setFloatValue(au);
	}

	void setBu(float bu) {
		this->bu.setFloatValue(bu);
	}

	void setCu(float cu) {
		this->cu.setFloatValue(cu);
	}

	void setAv(float av) {
		this->av.setFloatValue(av);
	}

	void setBv(float bv) {
		this->bv.setFloatValue(bv);
	}

	void setCv(float cv) {
		this->cv.setFloatValue(cv);
	}


}
;
}
#endif
