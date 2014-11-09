
#include "SFPipelineTransform3f.h"


namespace sf{

SFPipelineTransform3f::SFPipelineTransform3f(SFRigidTransform3fArray* array, int index) {
    this->array = array;
    this->index = index;
}

void SFPipelineTransform3f::attachOn(SFPipelineTransform3f transform){
    transform.array->attach(array, index, transform.index);
}

void SFPipelineTransform3f::setPosition(SFVertex3f* vertex){
    array->setElementPosition(index, vertex);
}

void SFPipelineTransform3f::setOrientation(SFMatrix3f* matrix){
    array->setElementOrientation(index, matrix);
}

void SFPipelineTransform3f::getPosition(SFVertex3f* vertex){
    array->getElementPosition(index, vertex);
}

void SFPipelineTransform3f::getOrientation(SFMatrix3f* matrix){
    array->getElementOrientation(index, matrix);
}

void SFPipelineTransform3f::apply(){
    array->apply(index);
}

}
