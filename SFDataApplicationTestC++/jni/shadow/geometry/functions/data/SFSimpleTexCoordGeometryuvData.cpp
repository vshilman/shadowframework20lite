
#include "SFSimpleTexCoordGeometryuvData.h"

namespace sf{

SFSimpleTexCoordGeometryuvData::SFSimpleTexCoordGeometryuvData(){
	du=new SFFloat(1);
	dv=new SFFloat(1);
	SFNamedParametersObject* parameters=new SFNamedParametersObject();
	parameters->addObject(du);
	parameters->addObject(dv);
	setData(parameters);
}

SFSimpleTexCoordGeometryuvData::~SFSimpleTexCoordGeometryuvData(){
	delete du;
	delete dv;
}

SFSurfaceFunction* SFSimpleTexCoordGeometryuvData::buildResource() {
	return new SFSimpleTexCoordGeometryuv(du->getFloatValue(), dv->getFloatValue());
}

void SFSimpleTexCoordGeometryuvData::updateResource(SFSurfaceFunction* resource) {
	SFSimpleTexCoordGeometryuv* surface = (SFSimpleTexCoordGeometryuv*) resource;
	surface->setDu(du->getFloatValue());
	surface->setDv(dv->getFloatValue());
}

}
