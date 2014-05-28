#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "shadow/math/SFValuenf.h"
#include "shadow/pipeline/SFStructureArray.h"
#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/SFResource.h"

namespace sf{
class SFStructureArrayResource implements SFGraphicsResource{

	Array array;
//	SFResource resource=new SFResource(0);

//	SFStructureArrayResource(SFStructureArray array) {
//		super();
		this->array = array;
	}
//	SFStructureArray getArray() {
//		return array;
	}
//	void setArray(SFStructureArray array) {
		this->array = array;
	}
//	SFResource getResource() {
//		return resource;
	}

//	void setParameterValue(int index,int parametersIndex,SFValuenf element){
//		getArray().setParameterValue(index, parametersIndex, element);
//		resource.resourceChanged();
	}

//	void getParameterValue(int index,int parametersIndex,SFValuenf element){
//		getArray().setParameterValue(index, parametersIndex, element);
//		resource.resourceChanged();
	}

	
//	void init() {
//		array.init();
	}

	
//	void destroy() {
//		array.destroy();
	}
}
;
}
#endif
