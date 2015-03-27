package sfogl2;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;

import shadow.graphics.SFBitmap;

public class SFOGLCubeMap extends SFOGLTexture {
	
	public static final int[] MAPS={
		GL.GL_TEXTURE_CUBE_MAP_POSITIVE_X,
		GL.GL_TEXTURE_CUBE_MAP_POSITIVE_Y,
		GL.GL_TEXTURE_CUBE_MAP_POSITIVE_Z,
		GL.GL_TEXTURE_CUBE_MAP_NEGATIVE_X,
		GL.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y,
		GL.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z,
	};
	
	public SFOGLCubeMap( ) {
		super();
	}
	
	public SFOGLCubeMap(int textureModel) {
		super(textureModel);
	}

	public void destroy(GL2ES2 gl){
		destroyTexture(gl,textureObject);
	}
	
	
	public static void destroyTexture(GL2ES2 gl,int textureObject){
		if(textureObject!=-1){
			int[] textures={textureObject};
			gl.glDeleteTextures(GL.GL_TEXTURE_CUBE_MAP, textures, 0);
		}
	}
	
	public void generateMipmap(GL2ES2 gl) {
		generateMipmap(gl,GL2.GL_TEXTURE_CUBE_MAP,textureObject);
	}
	
	public void bind(GL2ES2 gl){
		gl.glBindTexture(GL2.GL_TEXTURE_CUBE_MAP, textureObject);
	}
	
	public void setup(GL2ES2 gl, int width, int height){
		this.textureObject=createTextureCuberMap(gl, textureModel, width, height);
	}
	

	public void setup(GL2ES2 gl, SFBitmap[] bitmap){
		this.textureObject=createTextureCuberMap(gl, textureModel, bitmap);
	}
	
	public int getTextureObject() {
		return textureObject;
	}
	
	public static void generateMipmap(GL2ES2 gl, int textureObject) {
		gl.glBindTexture(GL.GL_TEXTURE_CUBE_MAP, textureObject);
		gl.glGenerateMipmap(GL.GL_TEXTURE_CUBE_MAP);
		gl.glBindTexture(GL.GL_TEXTURE_CUBE_MAP, 0);
	}
	
	public static int createTextureCuberMap(GL2ES2 gl, int width, int height) {
		return createTextureCuberMap(gl, -1, width, height);
	}

	public static int createTextureCuberMap(GL2ES2 gl, int textureModel, int width, int height) {
		int tx = generateTexture(gl);
		gl.glBindTexture(GL.GL_TEXTURE_CUBE_MAP, tx);
		SFOGLTextureModel.applyModel(gl,GL.GL_TEXTURE_2D,textureModel);
		for (int i = 0; i < MAPS.length; i++) {
			gl.glTexImage2D(MAPS[i], 0, GL2.GL_RGBA8, width, height, 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, null);
		}
		gl.glGenerateMipmap(GL.GL_TEXTURE_CUBE_MAP);
		gl.glBindTexture(GL.GL_TEXTURE_CUBE_MAP, 0);
		return tx;
	}

	public static int createTextureCuberMap(GL2ES2 gl, int[] width, int[] height) {
		return createTextureCuberMap(gl,-1,width,height);
	}
	
	public static int createTextureCuberMap(GL2ES2 gl, int textureModel, int[] width, int[] height) {
		int tx = generateTexture(gl);
		gl.glBindTexture(GL.GL_TEXTURE_CUBE_MAP, tx);
		SFOGLTextureModel.applyModel(gl,GL.GL_TEXTURE_2D,textureModel);
		for (int i = 0; i < MAPS.length; i++) {
			gl.glTexImage2D(MAPS[i], 0, GL2.GL_RGBA8, width[i], height[i], 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, null);
		}
		gl.glGenerateMipmap(GL.GL_TEXTURE_CUBE_MAP);
		gl.glBindTexture(GL.GL_TEXTURE_CUBE_MAP, 0);
		return tx;
	}
	
	public static int createTextureCuberMap(GL2ES2 gl, int textureModel, SFBitmap[] bitmap) {
		int tx = generateTexture(gl);
		gl.glBindTexture(GL.GL_TEXTURE_CUBE_MAP, tx);
		SFOGLTextureModel.applyModel(gl,GL.GL_TEXTURE_2D,textureModel);
		int internalFormat = SFOGLTextureModel.getInternalFormat(gl, textureModel);
		for (int i = 0; i < MAPS.length; i++) {
			int width=bitmap[i].getWidth();
			int height=bitmap[i].getHeight();
			int type=SFOGLTextureModel.getType(bitmap[i].getFormat());
			int format=SFOGLTextureModel.getType(bitmap[i].getFormat());
			gl.glTexImage2D(MAPS[i], 0, internalFormat, width, height, 0, type, format, bitmap[i].getData());
		}
		gl.glGenerateMipmap(GL.GL_TEXTURE_CUBE_MAP);
		gl.glBindTexture(GL.GL_TEXTURE_CUBE_MAP, 0);
		return tx;
	}
}
