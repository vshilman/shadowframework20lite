#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves/SFTransformedCurve.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/renderer/data/transforms/SFNoTransformData.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFLibraryReference.h"
#include "shadow/system/data/SFNamedParametersObject.h"

namespace sf{
class SFTransformedCurveData : public SFDataAsset<SFCurve>{

	SFLibraryReference<SFTransformResource> transform;
	SFLibraryReference<SFCurve> curve;

public:
	SFTransformedCurveData() {
		transform=new SFLibraryReference<SFTransformResource>(new SFNoTransformData());
		curve =new SFLibraryReference<SFCurve>();
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject( transform);
		parameters->addObject( curve);
		setData(parameters);
	}

	
	SFCurve buildResource() {
		SFTransformedCurve* trCurve=new SFTransformedCurve();
		updateResource(trCurve);
		return trCurve;
	}

	
	void updateResource(SFCurve* resource) {
		SFTransformedCurve* trCurve=(SFTransformedCurve*)resource;
		trCurve->setTransform(this->transform.getResource());
		trCurve->setCurve(this->curve.getResource());
	}
};
}
#endif
