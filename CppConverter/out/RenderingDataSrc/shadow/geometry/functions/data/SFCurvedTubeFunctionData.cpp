#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/functions.SFCurvedTubeFunction.h"
#include "shadow/system/data.SFDataset.h"

class SFCurvedTubeFunctionData extends SFTwoCurvesFunctionData{

	
	SFDataset generateNewDatasetInstance() {
		return new SFCurvedTubeFunctionData();
	}

	
	SFCurvedTubeFunction buildResource() {
		final SFCurvedTubeFunction curvedTube=new SFCurvedTubeFunction();
		updateResource(curvedTube);

		getDataAssetResource().setResource(1, getFirstCurve().getDataset().getDataAssetResource());
		getDataAssetResource().setResource(2, getSecondCurve().getDataset().getDataAssetResource());
		return curvedTube;
	}


	
	void updateResource(SFSurfaceFunction resource) {
		SFCurvedTubeFunction curvedTube=(SFCurvedTubeFunction)resource;

		getDataAssetResource().setResource(1, getFirstCurve().getDataset().getDataAssetResource());
		getDataAssetResource().setResource(2, getSecondCurve().getDataset().getDataAssetResource());

		curvedTube.setCentralCurve(getFirstCurve().getResource());
		curvedTube.setRayCurve(getSecondCurve().getResource());
	}
}
;
}
#endif
