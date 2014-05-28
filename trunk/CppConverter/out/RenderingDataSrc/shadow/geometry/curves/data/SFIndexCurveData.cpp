#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"

namespace sf{
class SFIndexCurveData extends SFDataAsset<SFCurve>{

	SFShort index=new SFShort((short)0);

	SFIndexCurveData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("index", index);
		setData(parameters);
	}

	
	SFCurve buildResource() {
		short indices=this->index.getShortValue();
		SFCurve curve=SFCurvesListKeeper.getKeeper().getCurvesList().getCurves()[indices];
		return curve;
	}


	
	void updateResource(SFCurve resource) {
		// TODO
	}
}
;
}
#endif
