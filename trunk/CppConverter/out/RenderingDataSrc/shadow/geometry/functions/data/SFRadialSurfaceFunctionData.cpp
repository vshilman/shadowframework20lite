#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/functions.SFRadialSurfaceFunction.h"
#include "shadow/system/data.SFDataset.h"

class SFRadialSurfaceFunctionData extends SFTwoCurvesFunctionData{


	
	SFDataset generateNewDatasetInstance() {
		return new SFRadialSurfaceFunctionData();
	}

	
	SFRadialSurfaceFunction buildResource() {

		SFRadialSurfaceFunction radialSurface=new SFRadialSurfaceFunction();
		updateResource(radialSurface);
		getDataAssetResource().setResource(1, getFirstCurve().getDataset().getDataAssetResource());
		getDataAssetResource().setResource(2, getSecondCurve().getDataset().getDataAssetResource());
		return radialSurface;
	}

	
	void updateResource(SFSurfaceFunction resource) {
		SFRadialSurfaceFunction radialSurface=(SFRadialSurfaceFunction)resource;

		getDataAssetResource().setResource(1, getFirstCurve().getDataset().getDataAssetResource());
		getDataAssetResource().setResource(2, getSecondCurve().getDataset().getDataAssetResource());

		radialSurface.setBorderCurve(getFirstCurve().getResource());
		radialSurface.setRayCurve(getSecondCurve().getResource());
	}
}
;
}
#endif
