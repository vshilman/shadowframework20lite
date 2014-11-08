//
//  SFRenderedTextureFactory.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFRenderedTextureFactory_H
#define SFRenderedTextureFactory_H

#include "SFPipelineTexture.h"
#include "SFBufferData.h"
#include "SFImageFormat.h"
#include "SFBitmap.h"

namespace sf{


class SFRenderedTextureFactory {
    
public:

	virtual ~SFRenderedTextureFactory(){};

	virtual SFBufferData* generatePlainBuffer(int width, int height)=0;
	
	virtual SFBufferData* generatePlainBuffer(int width, int height, SFImageFormat format)=0;
	
	virtual SFPipelineTexture* generateTextureBuffer(int width, int height, SFImageFormat format,
                                                    SFPipelineTexture::Filter filters, SFPipelineTexture::WrapMode wrapS, SFPipelineTexture::WrapMode wrapT)=0;
	
	virtual SFPipelineTexture* generateBitmapTexture(SFBitmap bitmap,
                                                    SFPipelineTexture::Filter filters,
                                                    SFPipelineTexture::WrapMode wrapS,
                                                    SFPipelineTexture::WrapMode wrapT)=0;
	
	virtual void destroyBuffer(SFBufferData* bufferData)=0;
};

}

#endif
