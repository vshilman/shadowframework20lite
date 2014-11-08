//
//  SFGL20RenderedTexture.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20RenderedTexture__
#define SFGL20RenderedTexture__

#include "SFRenderedTexture.h"


namespace sf{
class SFGL20RenderedTexture{
    
	int fbo;
	float vp[4];
	
public:
	void initShadowTexture(SFRenderedTexture data) {
        
        /*
		SFBufferData depthData=data.getDepthBuffer();
		
		if(depthData!=null)
			((SFGL20ImageObject)depthData).build();
		
		SFBufferData stencilData=data.getStencilBuffer();
		if(stencilData!=null)
			((SFGL20ImageObject)stencilData).build();
		
		for (Iterator<SFBufferData> iterator = data.getColorsData().iterator(); iterator.hasNext();) {
			SFBufferData colorBuffer = iterator.next();
			((SFGL20ImageObject)colorBuffer).build();
		}
        
		//Step 5: generate frame buffer. Bind to buffer
		int nfbo[]=new int[1];
		glGenFramebuffers(1, nfbo,0);
		fbo=nfbo[0];
        
		glBindFramebuffer(GL_FRAMEBUFFER,fbo);
		
		//Step 6: Viewport storing
		glGetFloatv(GL_VIEWPORT,vp,0);
		
		//Step 7: New Viewport for texture generation
        
		glViewport(0, 0, data.getColorsData().get(0).getWidth(),data.getColorsData().get(0).getHeight());
        
		int buffers[] = new int[data.getColorsData().size()];
		for (int i = 0; i < data.getColorsData().size(); i++) {
			buffers[i]= GL_COLOR_ATTACHMENT0+i;
		}
		glDrawBuffers(buffers.length, buffers,0);
        
		int index=0;
		for (Iterator<SFBufferData> iterator = data.getColorsData().iterator(); iterator.hasNext();) {
			SFBufferData colorBuffer = iterator.next();
			int texture_object=((SFGL20Texture)colorBuffer).getTextureObject();
			glFramebufferTexture2D(GL_FRAMEBUFFER,
                                      GL_COLOR_ATTACHMENT0+index,
                                      GL_TEXTURE_2D,texture_object,0);
			((SFGL20ImageObject)colorBuffer).build();
			index++;
		}
        
        //		glFramebufferTexture2D(GL_FRAMEBUFFER,
        //				GL_COLOR_ATTACHMENT0,
        //				GL_TEXTURE_2D,texture_object,0);
        
		//Step 8: texture bindings (txo and txo4)
        //		glActiveTexture(GL_TEXTURE0);
        //		glBindTexture(GL_TEXTURE_2D, depthBuffer);
        //		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_MODE, GL_COMPARE_R_TO_TEXTURE);
        //		glTexParameteri(GL_TEXTURE_2D, GL_GENERATE_MIPMAP, GL_TRUE);
        //		glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT,data.getWidth(), data.getHeight(), 0,
        //	             GL_DEPTH_COMPONENT,GL_FLOAT, null);
        
		if(depthData!=null){
            //			int textureObject=((SFGL20RenderBuffer)depthData).renderBuffer;
            //			glBindFramebuffer(GL_FRAMEBUFFER,fbo);
            //			glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT,
            //                    GL_RENDERBUFFER, textureObject);
		}
        
		if(stencilData!=null){
			int textureObject=((SFGL20RenderBuffer)stencilData).renderBuffer;
			glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_STENCIL_ATTACHMENT,
                                          GL_RENDERBUFFER, textureObject);
		}
		
		glDisable(GL_TEXTURE_2D);
		glClearColor(1,1,1,0);
		glClearDepth(2);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glEnable(GL_DEPTH_TEST);
        
		*/
	}
	
	
	void closeShadowTexture() {
        
        /*
		GL2 gl=SFgetGL();
        
		glDisable(GL_DEPTH_TEST);
        
		glBindFramebuffer(GL_FRAMEBUFFER,0);
        
		glBindRenderbuffer(GL_RENDERBUFFER,0);
        
        //		glActiveTexture(GL_TEXTURE0);
        //		glBindTexture(GL_TEXTURE_2D, texture_object);
        //		glGenerateMipmap(GL_TEXTURE_2D);
		int nfbo[]=new int[1];
		nfbo[0]=fbo;
		glDeleteFramebuffers(1, nfbo, 0);
		
		//restoring of the viewport
		glViewport((int)vp[0], (int)vp[1], (int)vp[2], (int)vp[3]);
        
        */
	}
    
	int getFbo() {
		return fbo;
	}
    
};

}

#endif /* defined(SFGL20RenderedTexture__) */
