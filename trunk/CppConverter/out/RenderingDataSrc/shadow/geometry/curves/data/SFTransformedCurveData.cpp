#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.SFTransformedCurve.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/renderer/data.transforms.SFNoTransformData.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFTransformedCurveData extends SFDataAsset<SFCurve>{

	SFLibraryReference<SFTransformResource> transform=new SFLibraryReference<SFTransformResource>(new SFNoTransformData());
	SFLibraryReference<SFCurve> curve =new SFLibraryReference<SFCurve>();

	SFTransformedCurveData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("transform", transform);
		parameters.addObject("curve", curve);
		setData(parameters);
	}

	
	SFCurve buildResource() {
		final SFTransformedCurve trCurve=new SFTransformedCurve();
		updateResource(trCurve);
		return trCurve;
	}

	
	void updateResource(SFCurve resource) {
		final SFTransformedCurve trCurve=(SFTransformedCurve)resource;
		trCurve.setTransform(this->transform.getResource());
		trCurve.setCurve(this->curve.getResource());
	}
}
;
}
#endif
