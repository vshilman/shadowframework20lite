#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/SFResource.h"

namespace sf{
class SfPrimitiveArrayResource implements SFGraphicsResource{

	Array array;
//	SFResource resource=new SFResource(0);

//	SfPrimitiveArrayResource(SFPrimitiveArray array) {
//		super();
		this->array = array;
	}

//	SFPrimitiveArray getArray() {
//		return array;
	}

//	SFResource getResource() {
//		return resource;
	}

	
//	void destroy() {
//		array.destroy();
	}

	
//	void init() {
//		array.init();
	}
}
;
}
#endif
