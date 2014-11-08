
#include "SFTexture.h"

namespace sf{

	SFTexture::SFTexture(SFRenderedTexturesSet* texturesSet, int index) {
		this->index = index;
		setTexturesSet(texturesSet);
	}

	SFResource* SFTexture::getResource() {
		return &resource;
	}

	SFPipelineTexture* SFTexture::getTexture(){
		return texturesSet->getTexture(index);
	}

	SFRenderedTexturesSet* SFTexture::getTexturesSet() {
		return texturesSet;
	}

	int SFTexture::getIndex() {
		return index;
	}

	void SFTexture::setIndex(int index) {
		this->index = index;
		resource.resourceChanged();
	}

	void SFTexture::setTexturesSet(SFRenderedTexturesSet* texturesSet) {
		this->texturesSet = texturesSet;
		if(texturesSet!=0)
			resource.setResource(0, texturesSet->getResource());
	}

}
