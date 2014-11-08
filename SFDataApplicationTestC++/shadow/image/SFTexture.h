#ifndef shadow_image_SFTexture_H_
#define shadow_image_SFTexture_H_

#include "SFIResource.h"
#include "SFResource.h"
#include "SFRenderedTexturesSet.h"
#include "SFPipelineTexture.h"

namespace sf{
class SFTexture : public SFIResource{

	SFRenderedTexturesSet* texturesSet;
	int index;
	SFResource resource;

public:
	SFTexture(SFRenderedTexturesSet* texturesSet, int index);

  	SFResource* getResource();

  	SFPipelineTexture* getTexture();

  	SFRenderedTexturesSet* getTexturesSet();

  	int getIndex();

  	void setIndex(int index);

  	void setTexturesSet(SFRenderedTexturesSet* texturesSet);

};

}
#endif
