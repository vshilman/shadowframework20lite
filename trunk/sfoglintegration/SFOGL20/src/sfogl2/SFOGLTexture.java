package sfogl2;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

public class SFOGLTexture {
	
	public static final int NULL_OBJECT = -1;
	
	protected int textureObject=NULL_OBJECT;
	protected int textureModel=-1;

	public SFOGLTexture(int textureModel) {
		this.textureModel=textureModel;
	}

	public SFOGLTexture() {
	}
	
	public void setTextureModel(int textureModel) {
		this.textureModel = textureModel;
	}
	
	public void delete(GL2ES2 gl){
		int txo[]=new int[1];
		txo[0]=textureObject;
		gl.glDeleteTextures(1, txo, 0);
	}
	
	public void apply(GL2ES2 gl,int textureLevel){
		gl.glActiveTexture(GL.GL_TEXTURE0+textureLevel);
		gl.glBindTexture(GL.GL_TEXTURE_2D, textureObject);//TODO : generalized type for Cube Maps 
	}

	public int getTextureObject() {
		return textureObject;
	}
	
	public int getTextureModel() {
		return textureModel;
	}
	
	public void setupParameters(GL2ES2 gl){
		SFOGLTextureModel.applyModel(gl,GL.GL_TEXTURE_2D,textureModel);
	}

	public static void generateMipmap(GL2ES2 gl, int target, int textureObject) {
		gl.glBindTexture(target, textureObject);
		gl.glGenerateMipmap(target);
		gl.glBindTexture(target, 0);
	}

	public static int generateTexture(GL2ES2 gl) {
		int txo[]=new int[1];
		gl.glGenTextures(1, txo, 0);
		int tx=txo[0];
		return tx;
	}
}