package com.shadow.android.opengles20.images;

import javax.microedition.khronos.opengles.GL;

import shadow.image.SFBufferData;
import shadow.image.SFImageFormat;

import android.opengl.GLES20;

import com.shadow.android.opengles20.SFGL2;

/**
 * An object wrapping most of RenderBuffers functionalities
 * 
 * @author Alessandro
 */
public class SFGL20RenderBuffer extends SFBufferData implements
		SFGL20ImageObject {

	int renderBuffer = -1;

	public SFGL20RenderBuffer(int width, int height, SFImageFormat format) {
		super(width, height, format);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.pipeline.openGL20.images.SFGL20ImageObject#build()
	 */
	@Override
	public void build() {

		if (renderBuffer == -1) {
			int rbo[] = new int[1];
			
			
			GLES20.glGenRenderbuffers(1, rbo, 0);
			renderBuffer = rbo[0];
			GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, rbo[0]);
			GLES20.glRenderbufferStorage(GLES20.GL_RENDERBUFFER,
					GLES20.GL_DEPTH_COMPONENT16,
					//SFGL20RenderedTextureFactory.getGLFormat(getFormat()),
					getWidth(), getHeight());
			GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, 0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.pipeline.openGL20.images.SFGL20ImageObject#destroy()
	 */
	@Override
	public void destroy() {
		int[] textures = { renderBuffer };
		GLES20.glDeleteRenderbuffers(GLES20.GL_TEXTURE_2D, textures, 1);
	}
}
