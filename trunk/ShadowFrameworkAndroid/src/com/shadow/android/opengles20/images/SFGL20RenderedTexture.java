package com.shadow.android.opengles20.images;

import shadow.image.SFRenderedTexture;
import android.opengl.GLES20;

public class SFGL20RenderedTexture{

	private int fbo;
	private int rbo;
	private int texture_object;
	private float vp[]=new float[4];
	
	public void initShadowTexture(SFRenderedTexture data) {

		//Step 5: generate frame buffer. Bind to buffer
		int nfbo[]=new int[1];
		GLES20.glGenFramebuffers(1, nfbo,0);
		fbo=nfbo[0];

		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER,fbo);

		
		/*SFBufferData depthData=data.getDepthBuffer();
		
		if(depthData!=null)
			((SFGL20ImageObject)depthData).build();
		
		SFBufferData stencilData=data.getStencilBuffer();
		if(stencilData!=null)
			((SFGL20ImageObject)stencilData).build();*/
		
		int width=0;
		int height=0;
		for (int i = 0; i < 1/*data.getColorsData().size()*/; i++) {
			((SFGL20ImageObject)data.getColorsData().get(i)).build();
			width=data.getColorsData().get(i).getWidth();
			height=data.getColorsData().get(i).getHeight();
		}
		
		int rbo[]=new int[1];
		GLES20.glGenRenderbuffers(1, rbo,0);
		this.rbo=rbo[0];
		GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, rbo[0]);
		GLES20.glRenderbufferStorage(GLES20.GL_RENDERBUFFER, GLES20.GL_DEPTH_COMPONENT16, width, height);
			

		SFGL20Texture colorBuffer =(SFGL20Texture) data.getColorsData().get(0);
		int texture_object=colorBuffer.getTextureObject();
		this.texture_object=texture_object;
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,
	            GLES20.GL_CLAMP_TO_EDGE);
	    GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
	            GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, 
				texture_object, 0);
		GLES20.glFramebufferRenderbuffer(GLES20.GL_FRAMEBUFFER, GLES20.GL_DEPTH_ATTACHMENT, GLES20.GL_RENDERBUFFER, 
				this.rbo);


        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, -1);
        GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, -1);
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, -1);

        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER,this.fbo);
		
		//Step 6: Viewport storing
		GLES20.glGetFloatv(GLES20.GL_VIEWPORT,vp,0);
		
		//Step 7: New Viewport for texture generation

		GLES20.glViewport(0, 0, width,height);

		
		//int buffers[] = new int[data.getColorsData().size()];
		//for (int i = 0; i < data.getColorsData().size(); i++) {
		//	buffers[i]= GLES20.GL_COLOR_ATTACHMENT0+i;	
		//}
		//GLES20.glDrawBuffers(buffers.length, buffers,0);

//		int index=0;
//		for (Iterator<SFBufferData> iterator = data.getColorsData().iterator(); iterator.hasNext();) {
//			SFBufferData colorBuffer = iterator.next();
//			int texture_object=((SFGL20Texture)colorBuffer).getTextureObject();
//			GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER,
//					GLES20.GL_COLOR_ATTACHMENT0+index,
//					GLES20.GL_TEXTURE_2D,texture_object,0);
//			((SFGL20ImageObject)colorBuffer).build();
//			index++;
//		}

//		gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER,
//				GL.GL_COLOR_ATTACHMENT0,
//				GL2.GL_TEXTURE_2D,texture_object,0);

		//Step 8: texture bindings (txo and txo4) 	
//		gl.glActiveTexture(GL.GL_TEXTURE0);
//		gl.glBindTexture(GL.GL_TEXTURE_2D, depthBuffer);
//		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL2.GL_TEXTURE_COMPARE_MODE, GL2.GL_COMPARE_R_TO_TEXTURE);
//		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL2.GL_GENERATE_MIPMAP, GL.GL_TRUE);
//		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL2.GL_DEPTH_COMPONENT,data.getWidth(), data.getHeight(), 0,
//	             GL2.GL_DEPTH_COMPONENT,GL.GL_FLOAT, null);

//		if(depthData!=null){
////			int textureObject=((SFGL20RenderBuffer)depthData).renderBuffer;
////			gl.glBindFramebuffer(GL.GL_FRAMEBUFFER,fbo);
////			gl.glFramebufferRenderbuffer(GL.GL_FRAMEBUFFER, GL2.GL_DEPTH_ATTACHMENT,
////                   GL. GL_RENDERBUFFER, textureObject);
//		}
//
//		if(stencilData!=null){
//			int textureObject=((SFGL20RenderBuffer)stencilData).renderBuffer;
//			GLES20.glFramebufferRenderbuffer(GLES20.GL_FRAMEBUFFER, GLES20.GL_STENCIL_ATTACHMENT,
//					GLES20. GL_RENDERBUFFER, textureObject);
//		}
		
		GLES20.glDisable(GLES20.GL_TEXTURE_2D);
		GLES20.glClearColor(1,1,1,1);
		GLES20.glClearDepthf(2.0f);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
	
	}
	
	
	public void closeShadowTexture() {

		//GL2 gl=SFGL2.getGL();
		
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,this.texture_object);
		GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);


		GLES20.glDisable(GLES20.GL_DEPTH_TEST);

		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER,0);

		GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER,0);

		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, this.texture_object);

		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

		
////		/*
////		 * FIXME : actually i can't use mipmapping on texture, must be solved, which is too important.
////		 */
////		gl.glActiveTexture(GL.GL_TEXTURE0);
////		gl.glBindTexture(GL.GL_TEXTURE_2D, texture_object);
////		gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
//		int nfbo[]=new int[1];
//		nfbo[0]=fbo;
//		GLES20.glDeleteFramebuffers(1, nfbo, 0);
		
		//restoring of the viewport
		GLES20.glViewport((int)vp[0], (int)vp[1], (int)vp[2], (int)vp[3]);
		
	}

	public int getFbo() {
		return fbo;
	}

}
