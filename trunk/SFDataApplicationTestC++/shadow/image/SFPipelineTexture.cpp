
#include "SFPipelineTexture.h"



namespace sf{

	SFPipelineTexture::SFPipelineTexture(int width, int height, SFImageFormat format,
      Filter filters, WrapMode wrapS,
      WrapMode wrapT):SFBufferData(width,height,format) {
		this->filters = filters;
		WrapS = wrapS;
		WrapT = wrapT;
	}
    
	SFPipelineTexture::Filter SFPipelineTexture::getFilters() {
		return filters;
	}
    
	SFPipelineTexture::WrapMode SFPipelineTexture::getWrapS() {
		return WrapS;
	}
    
	SFPipelineTexture::WrapMode SFPipelineTexture::getWrapT() {
		return WrapT;
	}

}
