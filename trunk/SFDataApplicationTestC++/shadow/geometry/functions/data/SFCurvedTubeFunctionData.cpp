
#include "SFCurvedTubeFunctionData.h"

namespace sf{

SFDataset* SFCurvedTubeFunctionData::generateNewDatasetInstance() {
	return new SFCurvedTubeFunctionData();
}

SFCurvedTubeFunction* SFCurvedTubeFunctionData::buildResource() {
	SFCurvedTubeFunction* curvedTube=new SFCurvedTubeFunction();
	updateResource(curvedTube);

	//getDataAssetResource().setResource(1, getFirstCurve().getDataset().getDataAssetResource());
	//getDataAssetResource().setResource(2, getSecondCurve().getDataset().getDataAssetResource());
	return curvedTube;
}


void SFCurvedTubeFunctionData::updateResource(SFSurfaceFunction* resource) {
	SFCurvedTubeFunction* curvedTube=(SFCurvedTubeFunction*)resource;

	//getDataAssetResource().setResource(1, getFirstCurve().getDataset().getDataAssetResource());
	//getDataAssetResource().setResource(2, getSecondCurve().getDataset().getDataAssetResource());

	curvedTube->setCentralCurve(getFirstCurve()->getResource());
	curvedTube->setRayCurve(getSecondCurve()->getResource());
}
}
