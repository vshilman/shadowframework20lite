#include "SFTranslateAndScaleData.h"

namespace sf{

SFTranslateAndScaleData::SFTranslateAndScaleData(){
	position=new SFVertex3fData();
	scale=new SFFloat(1);
	SFNamedParametersObject* parameters=new SFNamedParametersObject();
	parameters->addObject(position);
	parameters->addObject(scale);
	setData(parameters);
}

SFTransformResource* SFTranslateAndScaleData::buildResource() {
	SFMatrix3f* matrix=new SFMatrix3f();
	matrix->mult(scale->getFloatValue());

	SFTransformResource* transform=new SFTransformResource();
	transform->setMatrix(*matrix);
	transform->setPosition(this->position->getVertex3f());

	return transform;
}

void SFTranslateAndScaleData::updateResource(SFTransformResource* resource) {

}
}
