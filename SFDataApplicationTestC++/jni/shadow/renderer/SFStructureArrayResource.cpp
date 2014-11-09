
#include "SFStructureArrayResource.h"


namespace sf{

	SFStructureArrayResource::SFStructureArrayResource(SFStructureArray* array) {
		this->array = array;
	}

	SFStructureArray* SFStructureArrayResource::getArray() {
		return array;
	}

	void SFStructureArrayResource::setArray(SFStructureArray* array) {
		this->array = array;
	}

	SFResource* SFStructureArrayResource::getResource() {
		return &resource;
	}

	void SFStructureArrayResource::setParameterValue(int index,int parametersIndex,SFValuenf element){
		getArray()->setParameterValue(index, parametersIndex, &element);
		resource.resourceChanged();
	}

	void SFStructureArrayResource::getParameterValue(int index,int parametersIndex,SFValuenf element){
		getArray()->setParameterValue(index, parametersIndex, &element);
		resource.resourceChanged();
	}


	void SFStructureArrayResource::init() {
		array->init();
	}

	void SFStructureArrayResource::destroy() {
		array->destroy();
	}

}
