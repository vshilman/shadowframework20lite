//
//  SFPipelineTexture.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPipelineTexture_H
#define SFPipelineTexture_H

#include "SFBufferData.h"

namespace sf{

class SFPipelineTexture: public SFBufferData{
    
public:
	enum WrapMode{
		REPEAT,
		MIRRORED_REPEAT,
		CLAMP_TO_EDGE
	};
	
	enum Filter{
		NEAREST,
		LINEAR,
		LINEAR_MIPMAP
	};
	
    virtual void apply(int textureLevel)=0;
	
private:
    Filter filters;
	WrapMode WrapS;
    WrapMode WrapT;
	
public:
	SFPipelineTexture(int width, int height, SFImageFormat format,Filter filters, WrapMode wrapS, WrapMode wrapT);

	virtual ~SFPipelineTexture(){};

	Filter getFilters();
    
	WrapMode getWrapS();
    
	WrapMode getWrapT();
};

}

#endif /* defined(SFPipelineTexture__) */
