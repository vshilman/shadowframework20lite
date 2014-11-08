#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves/SFCurvesList.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFDataAssetList.h"
#include "shadow/system/data/SFNamedParametersObject.h"

namespace sf{

class SFCurvesListData : public SFDataAsset<SFCurvesList>{

	SFDataAssetList<SFCurve*>* curves;

public:
	SFCurvesListData();
	
	SFCurvesList* buildResource();

	void updateResource(SFCurvesList* curveList);

};

}
#endif
