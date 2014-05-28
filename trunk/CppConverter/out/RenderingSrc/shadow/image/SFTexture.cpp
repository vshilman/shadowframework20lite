#ifndef shadow_image_H_
#define shadow_image_H_

#include "shadow/system/SFIResource.h"
#include "shadow/system/SFResource.h"


class SFTexture implements SFIResource{

//	SFRenderedTexturesSet texturesSet;
//	int index;
//	SFResource resource=new SFResource(1);

//	SFTexture(SFRenderedTexturesSet texturesSet, int index) {
//		super();
		this->index = index;
//		setTexturesSet(texturesSet);
	}

//	SFResource getResource() {
//		return resource;
	}

//	SFPipelineTexture getTexture(){
//		return texturesSet.getTexture(index);
	}

//	SFRenderedTexturesSet getTexturesSet() {
//		return texturesSet;
	}

//	int getIndex() {
//		return index;
	}

//	void setIndex(int index) {
		this->index = index;
//		resource.resourceChanged();
	}

//	void setTexturesSet(SFRenderedTexturesSet texturesSet) {
		this->texturesSet = texturesSet;
//		if(texturesSet!=null)
//			resource.setResource(0, texturesSet.getResource());
	}


}
;
}
#endif
