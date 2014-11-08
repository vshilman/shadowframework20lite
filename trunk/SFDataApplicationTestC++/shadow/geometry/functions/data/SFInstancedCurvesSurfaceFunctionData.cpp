
#include "SFInstancedCurvesSurfaceFunctionData.h"

namespace sf{


SFInstancedCurvesSurfaceFunctionData::SFInstancedCurvesSurfaceFunctionData() {
	surfaceFunction=new SFDataAssetObject<SFSurfaceFunction>(0);
	indices=new SFShortArray(new short[0]);
	SFNamedParametersObject* parameters=new SFNamedParametersObject();
	parameters->addObject(surfaceFunction);
	parameters->addObject(indices);
	setData(parameters);
}

SFInstancedCurvesSurfaceFunctionData::~SFInstancedCurvesSurfaceFunctionData(){
	delete surfaceFunction;
	delete indices;
}

SFSurfaceFunction* SFInstancedCurvesSurfaceFunctionData::buildResource() {
	short* indices=this->indices->getShortValues();
	for (int i = 0; i < this->indices->getN(); i++) {
		((SFCurvesDataKeeperSurface)(this->surfaceFunction->getDataset())).addBuildingCurve(
				SFCurvesListKeeper::getKeeper().getCurvesList().getCurves()[indices[i]]);
	}
	SFSurfaceFunction* surfaceFunction=this->surfaceFunction->getResource();
	return surfaceFunction;
}

void SFInstancedCurvesSurfaceFunctionData::updateResource(SFSurfaceFunction* resource) {
	// TODO
}

}
