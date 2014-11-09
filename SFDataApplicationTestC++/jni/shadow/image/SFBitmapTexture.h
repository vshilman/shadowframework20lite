#ifndef shadow_image_SFBitmapTexture_H_
#define shadow_image_SFBitmapTexture_H_

#include "SFPipelineTexture.h"
#include "SFPipeline.h"
#include "SFResource.h"
#include "SFUpdatable.h"
#include "SFBitmap.h"
#include "SFRenderedTexturesSet.h"

namespace sf{
class SFBitmapTexture :public SFRenderedTexturesSet, public SFUpdatable{

  	SFPipelineTexture* texture;
  	SFBitmap* bitmap;
  	SFResource resource;

  	SFPipelineTexture::Filter filter;
  	SFPipelineTexture::WrapMode wrapMode;

public:
  	SFBitmapTexture(SFBitmap* bitmap);

  	SFResource* getResource() ;

  	SFBitmap* getBitmap();

  	void setBitmap(SFBitmap* bitmap);

  	SFPipelineTexture* getTexture(int index);
	
  	void init();
	
  	void update();

  	SFPipelineTexture::Filter getFilter();

  	void setFilter(SFPipelineTexture::Filter filter);

  	SFPipelineTexture::WrapMode getWrapMode();

  	void setWrapMode(SFPipelineTexture::WrapMode wrapMode);
	
  	void destroy();

  	int getTextureSize() ;

};

}
#endif
