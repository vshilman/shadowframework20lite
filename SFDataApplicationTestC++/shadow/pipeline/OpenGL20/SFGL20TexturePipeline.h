//
//  SFGL20TexturePipeline.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20TexturePipeline__
#define SFGL20TexturePipeline__


#include "../SFTexturePipeline.h"
#include "images/SFGL20RenderedTexture.h"
#include "images/SFGL20RenderedTextureFactory.h"


namespace sf{
class SFGL20TexturePipeline : public SFTexturePipeline{
    
	SFGL20RenderedTexture texture;
	
	SFGL20RenderedTextureFactory factory;
	
public:
	void beginNewRenderedTexture(SFRenderedTexture* textureData) {
		texture.initShadowTexture(*textureData);
		
		//what should i do?
	}
	
    //	@Override
    //	public int beginNewRenderedTexture(SFTextureData textureData) {
    //
    //		texture.initShadowTexture(textureData);
    //		int fbo=texture.getFbo();
    //		int txo=texture.getTexture_object();
    //		frameBuffers.put(txo,fbo);
    //
    //		return txo;
    //	}
	
    void destroyRenderedTexture(SFRenderedTexture* textureData) {
		// TODO Auto-generated method stub
		
		//Rendered texture are still not destroyed...
	}
	
	void endRenderedTexture(SFRenderedTexture* textureData) {
		// TODO Auto-generated method stub
        SFGL20TexturePipeline::texture.closeShadowTexture();
	}
	
    void destroyBufferData(SFBufferData* bufferData) {
		// TODO Auto-generated method stub
		
	}
	
    SFRenderedTextureFactory* getRenderedTextureFactory() {
		return &factory;
	}
};
}

#endif /* defined(SFGL20TexturePipeline__) */
