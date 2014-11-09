#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFShort.h"

namespace sf{
class SFIndexCurveData : public SFDataAsset<SFCurve>{

	SFShort* index;

public:
	SFIndexCurveData() {
		index=new SFShort((short)0);
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject( index);
		setData(parameters);
	}

	
	SFCurve* buildResource() {
		short indices=this->index->getShortValue();
		SFCurve* curve=SFCurvesListKeeper::getKeeper().getCurvesList().getCurves()[indices];
		return curve;
	}

	void updateResource(SFCurve* resource) {
		// TODO
	}
};

}
#endif
