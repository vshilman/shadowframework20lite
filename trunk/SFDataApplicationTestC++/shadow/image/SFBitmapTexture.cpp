
#include "SFBitmapTexture.h"

namespace sf{

	SFBitmapTexture::SFBitmapTexture(SFBitmap* bitmap) {
  		this->bitmap = bitmap;
  		//this->texture = texture;
  		//resource->setResource(0, bitmap->getResource());
  		filter=SFPipelineTexture::LINEAR_MIPMAP;
  		wrapMode=SFPipelineTexture::REPEAT;
	}

  	SFResource* SFBitmapTexture::getResource() {
  		return &resource;
	}

  	SFBitmap* SFBitmapTexture::getBitmap() {
  		return bitmap;
	}

  	void SFBitmapTexture::setBitmap(SFBitmap* bitmap) {
		this->bitmap = bitmap;
	}

	
  	SFPipelineTexture* SFBitmapTexture::getTexture(int index) {
  		return texture;
	}

	
  	void SFBitmapTexture::init() {
  		if(texture==0)
  			texture=SFPipeline::getSfTexturePipeline()->getRenderedTextureFactory()->generateBitmapTexture(*bitmap, filter,
  					wrapMode, wrapMode);
	}

	
  	void SFBitmapTexture::update() {
  		SFPipeline::getSfTexturePipeline()->destroyBufferData(texture);
  		texture=SFPipeline::getSfTexturePipeline()->getRenderedTextureFactory()->generateBitmapTexture(*bitmap, filter,
  				wrapMode, wrapMode);
	}

  	SFPipelineTexture::Filter SFBitmapTexture::getFilter() {
  		return filter;
	}

  	void SFBitmapTexture::setFilter(SFPipelineTexture::Filter filter) {
		this->filter = filter;
	}

  	SFPipelineTexture::WrapMode SFBitmapTexture::getWrapMode() {
  		return wrapMode;
	}

  	void SFBitmapTexture::setWrapMode(SFPipelineTexture::WrapMode wrapMode) {
		this->wrapMode = wrapMode;
	}

	
  	void SFBitmapTexture::destroy() {
  		if(texture!=0){
  			SFPipeline::getSfTexturePipeline()->destroyBufferData(texture);
		}
	}

  	int SFBitmapTexture::getTextureSize() {
  		return 1;
	}

}
