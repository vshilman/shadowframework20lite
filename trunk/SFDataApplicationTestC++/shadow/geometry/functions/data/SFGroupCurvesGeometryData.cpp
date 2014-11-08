#include "SFGroupCurvesGeometryData.h"


namespace sf{

SFGroupCurvesGeometryData::SFGroupCurvesGeometryData() {
	blocks = new SFDataObjectsList<SFString>(new SFString());
	curves = new SFLibraryReferenceList<SFCurvesList>(new SFLibraryReference<SFCurvesList>());
	geometries = new SFDataAssetList<SFMeshGeometry>();
	primitive=new SFString();
	SFNamedParametersObject* parameters=new SFNamedParametersObject();
	parameters->addObject(blocks);
	parameters->addObject(curves);
	parameters->addObject(geometries);
	parameters->addObject(primitive);
	setData(parameters);
}

SFGroupCurvesGeometryData::~SFGroupCurvesGeometryData() {
	delete blocks;
	delete curves;
	delete geometries;
	delete primitive;
}


SFGeometry* SFGroupCurvesGeometryData::buildResource() {
	SFGroupMeshGeometry* geometry=new SFGroupMeshGeometry();
	updateResource(geometry);
	return geometry;
}


void SFGroupCurvesGeometryData::updateResource(SFGeometry* resource) {
	for (int i = 0; i < this->curves->size(); i++) {
		SFPrimitiveBlock* block=SFPrimitiveBlock::valueOf(this->blocks->at(i).getString());
		SFCurvesListKeeper::getKeeper().registerList(block,
				curves->at(i)->getResource());
		SFCurvesListKeeper::getKeeper().setBlock(i, block);
	}

	SFGroupMeshGeometry* geometry=(SFGroupMeshGeometry*)resource;
	SFPrimitive* primitive=SFPipeline::getPrimitive(this->primitive->getString());
	geometry->setPrimitive(primitive);
	for (int i = 0; i < geometries->size(); i++) {
		geometry->addGeometry( (SFMeshGeometry*)geometries->at(i)->getResource());
	}
}

}
