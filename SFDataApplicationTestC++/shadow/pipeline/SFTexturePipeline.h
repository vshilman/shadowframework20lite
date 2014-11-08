//
//  SFTexturePipeline.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFTexturePipeline_h
#define _SFTexturePipeline_h

#include "../image/SFRenderedTexture.h"
#include "../image/SFBufferData.h"
#include "../image/SFRenderedTextureFactory.h"


namespace sf{

class SFTexturePipeline {
    
public:
    
    virtual ~SFTexturePipeline(){}
    
	virtual void beginNewRenderedTexture(SFRenderedTexture* textureData)=0;
	
	virtual void endRenderedTexture(SFRenderedTexture* textureData)=0;
	
	virtual void destroyRenderedTexture(SFRenderedTexture* textureData)=0;
    
	virtual void destroyBufferData(SFBufferData* bufferData)=0;
	
	virtual SFRenderedTextureFactory* getRenderedTextureFactory()=0;
};

}

#endif
