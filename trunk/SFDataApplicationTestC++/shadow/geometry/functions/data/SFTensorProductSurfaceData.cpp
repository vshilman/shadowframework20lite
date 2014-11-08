
#include "SFTensorProductSurfaceData.h"

namespace sf{


SFTensorProductSurfaceData::SFTensorProductSurfaceData(){
	guideLines=new SFLibraryReferenceList<SFCurve>(new SFLibraryReference<SFCurve>());
	productCurve=new SFLibraryReference<SFCurve>();
	SFNamedParametersObject* namedParameters=new SFNamedParametersObject();
	namedParameters->addObject( guideLines);
	namedParameters->addObject( productCurve);
	setData(namedParameters);
}

SFTensorProductSurfaceData::~SFTensorProductSurfaceData(){
	delete guideLines;
	delete productCurve;
}

void SFTensorProductSurfaceData::addBuildingCurve(SFCurve* curve) {
	curves.push_back(curve);
}


SFSurfaceFunction* SFTensorProductSurfaceData::buildResource() {
	SFTensorProductSurface* surface=new SFTensorProductSurface();
	updateResource(surface);
	return surface;
}


void SFTensorProductSurfaceData::updateResource(SFSurfaceFunction* resource) {

	SFTensorProductSurface* surface=(SFTensorProductSurface*)resource;

	int guideLineSize=this->guideLines->size();
	SFCurve** guideLines=(SFCurve**)(new SFCurve*[guideLineSize+this->curves.size()]);
	for (int i = 0; i < guideLineSize; i++) {
		guideLines[i]=(this->guideLines->at(i)->getResource());
	}

	for (int i = 0; i < curves.size(); i++) {
		guideLines[i+guideLineSize]=(this->curves.at(i));
	}

	surface->setProductCurve(productCurve->getResource());

	surface->setGuideLines(guideLines);
}

}
