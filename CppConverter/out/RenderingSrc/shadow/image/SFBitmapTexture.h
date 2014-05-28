#ifndef shadow_image_H_
#define shadow_image_H_

#include "shadow/image/SFPipelineTexture.Filter.h"
#include "shadow/image/SFPipelineTexture.WrapMode.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/system/SFResource.h"
#include "shadow/system/SFUpdatable.h"

class SFBitmapTexture implements SFRenderedTexturesSet, SFUpdatable{

//	SFPipelineTexture texture;
//	SFBitmap bitmap;
//	SFResource resource=new SFResource(1,this);

	AR_MIPMAP;
	AT;

//	SFBitmapTexture(SFBitmap bitmap) {
//		super();
		this->bitmap = bitmap;
//		resource.setResource(0, bitmap.getResource());
	}

//	SFResource getResource() {
//		return resource;
	}

//	SFBitmap getBitmap() {
//		return bitmap;
	}

//	void setBitmap(SFBitmap bitmap) {
		this->bitmap = bitmap;
	}

	
//	SFPipelineTexture getTexture(int index) {
//		return texture;
	}

	
//	void init() {
//		if(texture==null)
//			texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, filter,
//					wrapMode, wrapMode);
	}

	
//	void update() {
//		SFPipeline.getSfTexturePipeline().destroyBufferData(texture);
//		texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, filter,
//				wrapMode, wrapMode);
	}

//	Filter getFilter() {
//		return filter;
	}

//	void setFilter(Filter filter) {
		this->filter = filter;
	}

//	WrapMode getWrapMode() {
//		return wrapMode;
	}

//	void setWrapMode(WrapMode wrapMode) {
		this->wrapMode = wrapMode;
	}

	
//	void destroy() {
//		if(texture!=null){
//			SFPipeline.getSfTexturePipeline().destroyBufferData(texture);
		}
	}

	
//	int getTextureSize() {
//		return 1;
	}
}
;
}
#endif
