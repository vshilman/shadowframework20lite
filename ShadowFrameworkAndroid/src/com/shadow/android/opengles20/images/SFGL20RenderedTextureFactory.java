package com.shadow.android.opengles20.images;

import shadow.image.SFBitmap;
import shadow.image.SFBufferData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTextureFactory;
import android.opengl.GLES20;

public class SFGL20RenderedTextureFactory implements SFRenderedTextureFactory{

	public static int getGLFormat(SFImageFormat format){
		switch (format) {
			case ARGB8: return GLES20.GL_RGBA4;
			case DEPTH16: return GLES20.GL_DEPTH_COMPONENT16;
			case DEPTH8: return GLES20.GL_DEPTH_COMPONENT16;
			case RGB565: return GLES20.GL_RGB565;
			case STENCIL8: return GLES20.GL_STENCIL_INDEX8;
			case ARGB4: return GLES20.GL_RGBA4;
			case GRAY8: return GLES20.GL_LUMINANCE;
			case RGB8: return GLES20.GL_RGB;
	
		default:
			return GLES20.GL_RGBA4;
		}
	}

	@Override
	public SFBufferData generatePlainBuffer(int width, int height) {
		SFGL20RenderBuffer renderBuffer = new SFGL20RenderBuffer(width, height, SFImageFormat.ARGB8);
		renderBuffer.build();
		return renderBuffer;
	}
	
	@Override
	public SFBufferData generatePlainBuffer(int width, int height,
			SFImageFormat format) {
		SFGL20RenderBuffer renderBuffer = new SFGL20RenderBuffer(width, height, format);
		renderBuffer.build();
		return renderBuffer;
	}
	
	@Override
	public SFPipelineTexture generateTextureBuffer(int width, int height,
			SFImageFormat format, Filter filters, WrapMode wrapS, WrapMode wrapT) {
		SFGL20Texture texture=new SFGL20Texture(width, height, format, filters, wrapS, wrapT);
		texture.build();
		return texture;
	}
	
	@Override
	public SFPipelineTexture generateBitmapTexture(SFBitmap bitmap, Filter filters,
			WrapMode wrapS, WrapMode wrapT) {
		SFGL20Texture texture=new SFGL20Texture(bitmap.getWidth(), bitmap.getHeight(), bitmap.getFormat(), filters, wrapS, wrapT);
		texture.loadBitmap(bitmap);
		return texture;
	}
	
	@Override
	public void destroyBuffer(SFBufferData bufferData) {
		if(bufferData instanceof SFGL20Texture)
			((SFGL20Texture)bufferData).destroy();
		if(bufferData instanceof SFGL20RenderBuffer)
			((SFGL20RenderBuffer)bufferData).destroy();	
	}
}
