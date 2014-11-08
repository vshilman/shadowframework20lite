
#include "SFBicurvedLoftedSurfaceData.h"

namespace sf{


SFDataset* SFBicurvedLoftedSurfaceData::generateNewDatasetInstance() {
	return new SFBicurvedLoftedSurfaceData();
}


SFBicurvedLoftedSurfaceData::SFBicurvedLoftedSurfaceData() {
	centralCurve=new SFLibraryReference<SFCurve>();
	rayCurve=new SFLibraryReference<SFCurve>();
	SFNamedParametersObject* namedParameters=new SFNamedParametersObject();
	namedParameters->addObject(centralCurve);
	namedParameters->addObject(rayCurve);
	setData(namedParameters);
}


SFBicurvedLoftedSurfaceData::~SFBicurvedLoftedSurfaceData(){
	delete centralCurve;
	delete rayCurve;
}

void SFBicurvedLoftedSurfaceData::addBuildingCurve(SFCurve* curve) {
	addingCurve.push_back(curve);
}


SFLibraryReference<SFCurve>* SFBicurvedLoftedSurfaceData::getFirstCurve(){
	return centralCurve;
}

SFLibraryReference<SFCurve>* SFBicurvedLoftedSurfaceData::getSecondCurve(){
	return rayCurve;
}


SFBicurvedLoftedSurface* SFBicurvedLoftedSurfaceData::buildResource() {
	SFBicurvedLoftedSurface* loftCurve = new SFBicurvedLoftedSurface();
	updateResource(loftCurve);
	return loftCurve;
}


void SFBicurvedLoftedSurfaceData::updateResource(SFSurfaceFunction* resource) {
	SFBicurvedLoftedSurface* loftCurve=(SFBicurvedLoftedSurface*)resource;

	if(addingCurve.size()==2){
		loftCurve->setA(addingCurve[0]);
		loftCurve->setB(addingCurve[1]);
	}else{
		loftCurve->setA(getFirstCurve()->getResource());
		loftCurve->setB(getSecondCurve()->getResource());
	}

}
}
