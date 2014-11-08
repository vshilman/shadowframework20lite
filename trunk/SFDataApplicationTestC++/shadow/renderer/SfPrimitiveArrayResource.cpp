
#include "shadow/renderer/SfPrimitiveArrayResource.h"


namespace sf{
	SfPrimitiveArrayResource::SfPrimitiveArrayResource(SFPrimitiveArray* array) {
		this->array = array;
	}

  	SFPrimitiveArray* SfPrimitiveArrayResource::getArray() {
  		return array;
	}

  	SFResource* SfPrimitiveArrayResource::getResource() {
  		return &resource;
	}

	
  	void SfPrimitiveArrayResource::destroy() {
  		array->destroy();
	}

	
  	void SfPrimitiveArrayResource::init() {
  		array->init();
	}
}
