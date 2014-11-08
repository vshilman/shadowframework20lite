//
//  SFGL20RenderedTextureFactory.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20RenderedTextureFactory__
#define SFGL20RenderedTextureFactory__

#include "SFGraphicsHeaders.h"
//#include <GL/glew.h>
#include "SFRenderedTextureFactory.h"
#include "SFGL20RenderBuffer.h"
#include "SFGL20Texture.h"
#include "SFImageFormat.h"
#include "SFBufferData.h"


namespace sf{
class SFGL20RenderedTextureFactory : public SFRenderedTextureFactory{
    
public:
    
	
	 SFBufferData* generatePlainBuffer(int width, int height) {
         SFGL20RenderBuffer* renderBuffer = new SFGL20RenderBuffer(width, height, ARGB8);
		renderBuffer->build();
		return renderBuffer;
	}
	
	
	 SFBufferData* generatePlainBuffer(int width, int height,
                                            SFImageFormat format) {
		SFGL20RenderBuffer* renderBuffer = new SFGL20RenderBuffer(width, height, format);
		renderBuffer->build();
		return renderBuffer;
	}
	
	
	 SFPipelineTexture* generateTextureBuffer(int width, int height,
                                             SFImageFormat format, SFPipelineTexture::Filter filters, SFPipelineTexture::WrapMode wrapS, SFPipelineTexture::WrapMode wrapT) {
		SFGL20Texture* texture=new SFGL20Texture(width, height, format, filters, wrapS, wrapT);
		texture->build();
		return texture;
	}
	
	
	 SFPipelineTexture* generateBitmapTexture(SFBitmap bitmap, SFPipelineTexture::Filter filters,SFPipelineTexture::WrapMode wrapS, SFPipelineTexture::WrapMode wrapT) {
		SFGL20Texture* texture=new SFGL20Texture(bitmap.getWidth(), bitmap.getHeight(), bitmap.getFormat(), filters, wrapS, wrapT);
		texture->loadBitmap(bitmap);
		return texture;
	}
	
	
	 void destroyBuffer(SFBufferData* bufferData) {
		//TODO
        //if(dynamic_cast<SFGL20Texture*>(bufferData))
		//	((SFGL20Texture*)bufferData).destroy();
		//if(dynamic_cast<SFGL20RenderBuffer*>(bufferData))
		//	((SFGL20RenderBuffer*)bufferData).destroy();
	}
};
}


#endif /* defined(SFGL20RenderedTextureFactory__) */
