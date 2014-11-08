//
//  SFGL20Texture.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20Texture__
#define SFGL20Texture__

#include "SFGraphicsHeaders.h"

#include "SFPipelineTexture.h"
#include "SFBitmap.h"
#include "SFGL20ImageObject.h"


namespace sf{
class SFGL20Texture : public SFPipelineTexture, SFGL20ImageObject{
    
private:
	int textureObject;
    
public:
	SFGL20Texture(int width, int height, SFImageFormat format,
                  SFPipelineTexture::Filter filters,
                  SFPipelineTexture::WrapMode wrapS,
                  SFPipelineTexture::WrapMode wrapT):
        SFPipelineTexture(width, height, format, filters, wrapS, wrapT){
		textureObject=-1;
	}
    
    
    static GLuint getGLFormat(SFImageFormat format){
		switch (format) {
                //case ARGB8: return GL_RGBA8;
			case DEPTH16: return GL_DEPTH_COMPONENT16;
			case DEPTH8: return GL_DEPTH_COMPONENT16;
			case RGB565: return 0;//return GL_RGB565;
			case STENCIL8: return GL_STENCIL_INDEX8;
			case ARGB4: return GL_RGBA4;
			case GRAY8: return GL_LUMINANCE;
			case RGB8: return GL_RGB;
                
            default:
                return GL_RGB;
		}
	}
    
	int getGLMinFilter(){
		switch (getFilters()) {
			case NEAREST: return GL_NEAREST;
			case LINEAR: return GL_LINEAR;
			case LINEAR_MIPMAP: return GL_LINEAR_MIPMAP_LINEAR;
            default:
                return GL_LINEAR_MIPMAP_LINEAR;
		}
	}
	
	int getGLMagFilter(){
		switch (getFilters()) {
			case NEAREST: return GL_NEAREST;
			case LINEAR: return GL_LINEAR;
			case LINEAR_MIPMAP: return GL_LINEAR;
            default:
                return GL_LINEAR;
		}
	}
	
	int getGLWrap(WrapMode mode){
		switch (mode) {
			case REPEAT: return GL_REPEAT;
			case MIRRORED_REPEAT: return GL_MIRRORED_REPEAT;
			case CLAMP_TO_EDGE: return GL_CLAMP_TO_EDGE;
            default:
                return GL_REPEAT;
		}
	}
	
	
	void build() {
        
		if(textureObject==-1){
			GLuint txo;
			glGenTextures(1,&txo);
			textureObject=txo;
			glBindTexture(GL_TEXTURE_2D, textureObject);
			setupParameters();
			glTexImage2D(GL_TEXTURE_2D, 0, getGLFormat(getFormat()), getWidth(), getHeight(), 0,
                            GL_RGB,GL_UNSIGNED_BYTE, 0);
			glGenerateMipmap(GL_TEXTURE_2D);
		}
        
	}
    
	void setupParameters() {
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, getGLMinFilter());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, getGLMagFilter());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, getGLWrap(getWrapS()));
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, getGLWrap(getWrapS()));
	}
	
	
	void apply(int textureLevel) {
		build();
		glActiveTexture(GL_TEXTURE0+textureLevel);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, textureObject);
	}
	
	void loadBitmap(SFBitmap bitmap) {
        
		if(textureObject==-1){
			GLuint txo;
			glGenTextures(1,&txo);
			textureObject=txo;
		}
		glBindTexture(GL_TEXTURE_2D, textureObject);
		setupParameters();
		if(bitmap.getFormat()==GRAY8){
			glTexImage2D(GL_TEXTURE_2D, 0, GL_LUMINANCE,
                            bitmap.getWidth(), bitmap.getHeight(), 0, GL_LUMINANCE, GL_UNSIGNED_BYTE, bitmap.getData());
		}else{
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB,
                            bitmap.getWidth(), bitmap.getHeight(), 0, GL_RGB, GL_UNSIGNED_BYTE, bitmap.getData());
		}
		glGenerateMipmap(GL_TEXTURE_2D);
	}
	
	
	void destroy() {
        //GL_API voidGL_APIENTRY glDeleteTextures (GLsizei n, const GLuint* textures);
		const unsigned int txo=textureObject;
        glDeleteTextures((GLsizei)1, &txo);
	}
    
	int getTextureObject() {
		return textureObject;
	}
	
};
}


#endif /* defined(SFGL20Texture__) */
