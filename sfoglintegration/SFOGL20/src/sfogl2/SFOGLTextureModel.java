package sfogl2;

import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLES2;

import shadow.graphics.SFImageFormat;

public class SFOGLTextureModel {
	
	private int textureWrapS;
	private int textureWrapT;
	private int minFilter;
	private int maxFilter;
	
	private int internalFormat;
	
	private static ArrayList<SFOGLTextureModel> models=new ArrayList<SFOGLTextureModel>();
	
	private SFOGLTextureModel(int internalFormat, int textureWrapS, int textureWrapT,
			int minFilter, int maxFilter) {
		super();
		this.textureWrapS = textureWrapS;
		this.textureWrapT = textureWrapT;
		this.minFilter = minFilter;
		this.maxFilter = maxFilter;
		this.internalFormat = internalFormat;
	}
	
	public static int getInternalFormat(GL2ES2 gl, int model){
		return models.get(model).internalFormat;
	}
	
	public static void applyModel(GL2ES2 gl, int target,int model){
		models.get(model).generateTexturesParameters(gl,target);
	}
	
	public static void clearAllModel(){
		models.clear();
	}
	
	public static int generateTextureObjectModel(SFImageFormat internalFormat, int textureWrapS, int textureWrapT,
			int minFilter, int maxFilter){
		SFOGLTextureModel textureModel=new SFOGLTextureModel(getInternalFormat(internalFormat),textureWrapS, textureWrapT, minFilter, maxFilter);
		models.add(textureModel);
		return models.size()-1;
	}
	
	public void generateTexturesParameters(GL2ES2 gl,int target){
		setupTextureParameters(gl, target, textureWrapS, textureWrapT,
				minFilter, maxFilter);
	}

	private void setupTextureParameters(GL2ES2 gl, int target,
			int textureWrapS, int textureWrapT, int minFilter, int maxFilter) {
		gl.glTexParameteri(target, GL.GL_TEXTURE_MIN_FILTER, minFilter);
		gl.glTexParameteri(target, GL.GL_TEXTURE_MAG_FILTER, maxFilter);
		gl.glTexParameteri(target, GL.GL_TEXTURE_WRAP_S, textureWrapS);
		gl.glTexParameteri(target, GL.GL_TEXTURE_WRAP_T, textureWrapT);
	}
	
	public static int getType(SFImageFormat format){
		switch (format) {
			case ALPHA: return GLES2.GL_ALPHA;
			case GRAY: return GLES2.GL_LUMINANCE;
			case GRAY_ALPHA: return GLES2.GL_LUMINANCE_ALPHA;
			case RGB: return GLES2.GL_RGB;
			case RGB565: return GLES2.GL_RGB;
			case RGBA: return GLES2.GL_RGBA;
			case RGBA4: return GLES2.GL_RGBA;
			case RGBA5551: return GLES2.GL_RGBA;
			default: return GLES2.GL_RGBA;
			
		}
	}
	
	public static int getInternalFormat(SFImageFormat format){
		switch (format) {
			case ALPHA: return GLES2.GL_ALPHA;
			case GRAY: return GLES2.GL_LUMINANCE;
			case GRAY_ALPHA: return GLES2.GL_LUMINANCE_ALPHA;
			case RGB: return GLES2.GL_RGB;
			case RGB565: return GLES2.GL_RGB;
			case RGBA: return GLES2.GL_RGBA;
			case RGBA4: return GLES2.GL_RGBA;
			case RGBA5551: return GLES2.GL_RGBA;
			default: return GLES2.GL_RGBA;
		}
	}
	
	public static int getFormat(SFImageFormat format){
		switch (format) {
			case ALPHA: return GLES2.GL_UNSIGNED_BYTE;
			case GRAY: return GLES2.GL_UNSIGNED_BYTE;
			case GRAY_ALPHA: return GLES2.GL_UNSIGNED_BYTE;
			case RGB: return GLES2.GL_UNSIGNED_BYTE;
			case RGB565: return GLES2.GL_UNSIGNED_SHORT_5_6_5;
			case RGBA: return GLES2.GL_UNSIGNED_BYTE;
			case RGBA4: return GLES2.GL_UNSIGNED_SHORT_4_4_4_4;
			case RGBA5551: return GLES2.GL_UNSIGNED_SHORT_5_5_5_1;
			default: return GLES2.GL_RGBA;
		}
	}
}
