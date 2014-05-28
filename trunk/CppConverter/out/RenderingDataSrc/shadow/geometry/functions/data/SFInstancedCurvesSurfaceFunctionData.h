#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/curves.data.SFCurvesDataKeeperSurface.h"
#include "shadow/geometry/curves.data.SFCurvesListKeeper.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShortArray.h"

namespace sf{
class SFInstancedCurvesSurfaceFunctionData extends SFDataAsset<SFSurfaceFunction> {

	AssetObject<SFSurfaceFunction> surfaceFunction=new SFDataAssetObject<SFSurfaceFunction>(null);
	Array indices=new SFShortArray(new short[0]);

	SFInstancedCurvesSurfaceFunctionData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("surfaceFunction", surfaceFunction);
		parameters.addObject("indices", indices);
		setData(parameters);
	}

	
	SFSurfaceFunction buildResource() {
		short[] indices=this->indices.getShortValues();
		for (int i = 0; i < indices.length; i++) {
			((SFCurvesDataKeeperSurface)(this->surfaceFunction.getDataset())).addBuildingCurve(
					SFCurvesListKeeper.getKeeper().getCurvesList().getCurves()[indices[i]]);
		}
		SFSurfaceFunction surfaceFunction=this->surfaceFunction.getResource();
		return surfaceFunction;
	}


	
	void updateResource(SFSurfaceFunction resource) {
		// TODO
	}
}
;
}
#endif
