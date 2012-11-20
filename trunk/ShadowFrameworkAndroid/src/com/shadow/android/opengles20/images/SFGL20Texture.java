package com.shadow.android.opengles20.images;

import java.nio.ByteBuffer;

import shadow.image.SFBitmap;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import android.opengl.GLES20;

/**
 * An object wrapping most of Texturing functionalities
 * 
 * @author Alessandro
 */
public class SFGL20Texture extends SFPipelineTexture implements SFGL20ImageObject{

	private int textureObject=-1;

	public SFGL20Texture(int width, int height, SFImageFormat format,
			Filter filters, WrapMode wrapS, WrapMode wrapT) {
		super(width, height, format, filters, wrapS, wrapT);
	}

	private int getGLMinFilter(){
		switch (getFilters()) {
			case NEAREST: return GLES20.GL_NEAREST;
			case LINEAR: return GLES20.GL_LINEAR;
			case LINEAR_MIPMAP: return GLES20.GL_LINEAR_MIPMAP_LINEAR;
		default:
			return GLES20.GL_LINEAR_MIPMAP_LINEAR;
		}
	}
	
	private int getGLMagFilter(){
		switch (getFilters()) {
			case NEAREST: return GLES20.GL_NEAREST;
			case LINEAR: return GLES20.GL_LINEAR;
			case LINEAR_MIPMAP: return GLES20.GL_LINEAR;
		default:
			return GLES20.GL_LINEAR;
		}
	}
	
	private int getGLWrap(WrapMode mode){
		switch (mode) {
			case REPEAT: return GLES20.GL_REPEAT;
			case MIRRORED_REPEAT: return GLES20.GL_MIRRORED_REPEAT;
			case CLAMP_TO_EDGE: return GLES20.GL_CLAMP_TO_EDGE;
		default:
			return GLES20.GL_REPEAT;
		}
	}
	
	@Override
	public void build() {

		if(textureObject==-1){
			int txo[]=new int[1];
			GLES20.glGenTextures(1,txo,0);
			textureObject=txo[0];
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureObject);
			GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
			GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
			GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
			GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
			//setupParameters();
			GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGB, getWidth(), getHeight(), 0,
					GLES20.GL_RGB,GLES20.GL_UNSIGNED_BYTE, null);	setupParameters();
		}

	}

	private void setupParameters() {
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, getGLMinFilter());
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, getGLMagFilter());
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, getGLWrap(getWrapS()));
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, getGLWrap(getWrapS()));
	}
	
	@Override
	public void apply(int textureLevel) {
		build();
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0+textureLevel);
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureObject);
	}
	
	public void loadBitmap(SFBitmap bitmap) {

		if(textureObject==-1){
			int txo[]=new int[1];
			GLES20.glGenTextures(1,txo,0);
			textureObject=txo[0];
		}
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureObject);
		setupParameters();
		if(bitmap.getFormat()==SFImageFormat.GRAY8){
			GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_LUMINANCE, 
					bitmap.getWidth(), bitmap.getHeight(), 0, GLES20.GL_LUMINANCE, GLES20.GL_UNSIGNED_BYTE, (ByteBuffer)bitmap.getData());
		}else{
			GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGB, 
					bitmap.getWidth(), bitmap.getHeight(), 0, GLES20.GL_RGB, GLES20.GL_UNSIGNED_BYTE, (ByteBuffer)bitmap.getData());
		}
		GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
	}
	
	@Override
	public void destroy() {
		int[] textures={textureObject};
		GLES20.glDeleteTextures(GLES20.GL_TEXTURE_2D, textures, 1);
	}

	public int getTextureObject() {
		return textureObject;
	}
	
	
}
