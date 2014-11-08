//
//  SFGL20RenderBuffer.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20RenderBuffer__
#define SFGL20RenderBuffer__


#include "SFGraphicsHeaders.h"

#include "../../../image/SFBufferData.h"
#include "SFGL20ImageObject.h"

namespace sf{
class SFGL20RenderBuffer : public SFBufferData,SFGL20ImageObject {
    
private:
	int renderBuffer;
    
public:
	SFGL20RenderBuffer(int width, int height, SFImageFormat format):
        SFBufferData(width,height,format){
		renderBuffer=-1;
	}
    
	/*
	 * (non-Javadoc)
	 *
	 * @see shadow.pipeline.openGL20.images.SFGL20ImageObject#build()
	 */
	void build() {
        
		if (renderBuffer == -1) {
			
            GLuint rbo;
			glGenRenderbuffers(1, &rbo);
			renderBuffer = rbo;
			glBindRenderbuffer(GL_RENDERBUFFER, rbo);
			glRenderbufferStorage(GL_RENDERBUFFER,
                                  GL_DEPTH_COMPONENT16,
                                                //SFGL20RenderedTextureFactory.getGLFormat(getFormat()),
                                                getWidth(), getHeight());
			glBindRenderbuffer(GL_RENDERBUFFER, 0);
		}
	}
    
	/*
	 * (non-Javadoc)
	 *
	 * @see shadow.pipeline.openGL20.images.SFGL20ImageObject#destroy()
	 */void destroy() {
		GLuint textures =renderBuffer;
		glDeleteRenderbuffers(1, &textures);
	}
};
}



#endif /* defined(SFGL20RenderBuffer__) */
