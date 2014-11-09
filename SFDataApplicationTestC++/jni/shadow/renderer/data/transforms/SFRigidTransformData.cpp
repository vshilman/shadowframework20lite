#include "SFRigidTransformData.h"

namespace sf{

SFRigidTransformData::SFRigidTransformData(){
	position=new SFVertex3fData();
	orientation=new SFVertex3fData();
	scale=new SFFloat(1);
	SFNamedParametersObject* parameters=new SFNamedParametersObject();
	parameters->addObject( position);
	parameters->addObject( orientation);
	parameters->addObject( scale);
	setData(parameters);
}

SFTransformResource* SFRigidTransformData::buildResource() {
	SFMatrix3f* matrix=new SFMatrix3f();
	SFEulerAngles3f* angles=new SFEulerAngles3f();
	angles->set(orientation->getVertex3f());
	angles->getMatrix(matrix);
	matrix->mult(scale->getFloatValue());

	SFTransformResource* transform=new SFTransformResource();
	transform->setMatrix(*matrix);
	transform->setPosition(this->position->getVertex3f());

	return transform;
}

void SFRigidTransformData::updateResource(SFTransformResource* resource) {

}
}
