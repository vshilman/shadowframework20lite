#ifndef shadow_image_SFRenderedTexturesSet_H_
#define shadow_image_SFRenderedTexturesSet_H_

#include "SFGraphicsResource.h"
#include "SFUpdatable.h"
#include "SFPipelineTexture.h"

namespace sf{
class SFRenderedTexturesSet: public SFGraphicsResource,public SFUpdatable{

public:
	virtual ~SFRenderedTexturesSet();

	virtual SFPipelineTexture* getTexture(int index)=0;

	virtual int getTextureSize()=0;

};

}
#endif
