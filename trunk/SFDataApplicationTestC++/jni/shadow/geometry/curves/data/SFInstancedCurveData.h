#ifndef shadow_geometry_curves_data_SFInstancedCurveData_H_
#define shadow_geometry_curves_data_SFInstancedCurveData_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFDataAssetObject.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFShortArray.h"
#include "shadow/geometry/curves/data/SFValuesDataKeeperCurve.h"

namespace sf{
class SFInstancedCurveData : public SFDataAsset<SFCurve>{

	SFDataAssetObject<SFCurve> curveModel=new SFDataAssetObject<SFCurve>(0);
	SFShortArray indices=new SFShortArray(new short[0]);

public:
	SFInstancedCurveData() {
		curveModel=new SFDataAssetObject<SFCurve>(0);
		indices=new SFShortArray(new short[0]);
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject( curveModel);
		parameters->addObject( indices);
		setData(parameters);
	}

	
	SFCurve buildResource() {
		short* indices=this->indices.getShortValues();
		for (int i = 0; i < this->indices.getN(); i++) {
			((SFValuesDataKeeperCurve*)(this->curveModel.getDataset())).addBuildingValue(
					SFValuesListKeeper.getKeeper().getValuesList().getValues()[indices[i]]);
		}
		SFCurve* curve=this->curveModel.getResource();
		return curve;
	}

	
	void updateResource(SFCurve* resource) {
		// TODO
	}
};

}
#endif
