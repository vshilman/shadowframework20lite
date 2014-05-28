#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShortArray.h"

namespace sf{
class SFInstancedCurveData extends SFDataAsset<SFCurve>{

	AssetObject<SFCurve> curveModel=new SFDataAssetObject<SFCurve>(null);
	Array indices=new SFShortArray(new short[0]);

	SFInstancedCurveData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("curveModel", curveModel);
		parameters.addObject("indices", indices);
		setData(parameters);
	}

	
	SFCurve buildResource() {
		short[] indices=this->indices.getShortValues();
		for (int i = 0; i < indices.length; i++) {
			((SFValuesDataKeeperCurve)(this->curveModel.getDataset())).addBuildingValue(
					SFValuesListKeeper.getKeeper().getValuesList().getValues()[indices[i]]);
		}
		SFCurve curve=this->curveModel.getResource();
		return curve;
	}

	
	void updateResource(SFCurve resource) {
		// TODO
	}
}
;
}
#endif
