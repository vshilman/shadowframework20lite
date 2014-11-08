
#include "SFRadialSurfaceFunctionData.h"

namespace sf{
	SFDataset* SFRadialSurfaceFunctionData::generateNewDatasetInstance() {
		return new SFRadialSurfaceFunctionData();
	}

	SFRadialSurfaceFunction SFRadialSurfaceFunctionData::buildResource() {

		SFRadialSurfaceFunction* radialSurface=new SFRadialSurfaceFunction();
		updateResource(radialSurface);
		//getDataAssetResource().setResource(1, getFirstCurve().getDataset().getDataAssetResource());
		//getDataAssetResource().setResource(2, getSecondCurve().getDataset().getDataAssetResource());
		return radialSurface;
	}

	void SFRadialSurfaceFunctionData::updateResource(SFSurfaceFunction* resource) {
		SFRadialSurfaceFunction* radialSurface=(SFRadialSurfaceFunction*)resource;

		//getDataAssetResource()->setResource(1, getFirstCurve().getDataset().getDataAssetResource());
		//getDataAssetResource()->setResource(2, getSecondCurve().getDataset().getDataAssetResource());

		radialSurface->setBorderCurve(getFirstCurve()->getResource());
		radialSurface->setRayCurve(getSecondCurve()->getResource());
	}
}
