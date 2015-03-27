package sfogl2;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;

import shadow.graphics.SFBitmap;

public class SFOGLTexture2D extends SFOGLTexture  {

	public SFOGLTexture2D( ) {
		super();
	}
	
	public SFOGLTexture2D(int textureModel) {
		super(textureModel);
	}
	
	public void bind(GL2ES2 gl){
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textureObject);
	}
	
	public void setup(GL2ES2 gl, int width, int height){
		this.textureObject=createTexture2D(gl, textureModel, width, height);
	}

	public void setup(GL2ES2 gl, SFBitmap bitmap){
		this.textureObject=createTexture2D(gl, textureModel, bitmap);
	}

	public void generateMipmap(GL2ES2 gl) {
		generateMipmap(gl,GL.GL_TEXTURE_2D,textureObject);
	}
	
	public void destroy(GL2ES2 gl){
		destroyTexture(gl,textureObject);
	}
	
	
	public static void destroyTexture(GL2ES2 gl,int textureObject){
		if(textureObject!=-1){
			int[] textures={textureObject};
			gl.glDeleteTextures(GL.GL_TEXTURE_2D, textures, 0);
		}
	}

	public static int createTexture2D(GL2ES2 gl, int textureModel, int width, int height) {
		int tx = generateTexture(gl);
		gl.glBindTexture(GL.GL_TEXTURE_2D, tx);
		SFOGLTextureModel.applyModel(gl,GL.GL_TEXTURE_2D,textureModel);
		int internalFormat = SFOGLTextureModel.getInternalFormat(gl, textureModel);
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, internalFormat, width, height, 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, null);
		gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
		gl.glBindTexture(GL.GL_TEXTURE_2D, 0);
		return tx;
	}
	
	public static int createTexture2D(GL2ES2 gl, int textureModel, SFBitmap bitmap) {
		int tx = generateTexture(gl);
		gl.glBindTexture(GL.GL_TEXTURE_2D, tx);
		SFOGLTextureModel.applyModel(gl,GL.GL_TEXTURE_2D,textureModel);
		int internalFormat = SFOGLTextureModel.getInternalFormat(bitmap.getFormat());
		int type=SFOGLTextureModel.getType(bitmap.getFormat());
		int format=SFOGLTextureModel.getFormat(bitmap.getFormat());
		
//		System.err.println("internalFormat "+internalFormat+" "+GL.GL_RGB);
//		System.err.println("type "+type+" "+GL.GL_RGB);
//		System.err.println("format "+format+" "+GL.GL_UNSIGNED_BYTE);
//		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, 
//				bitmap.getWidth(), bitmap.getHeight(), 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, (ByteBuffer)bitmap.getData());
		
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, internalFormat, bitmap.getWidth(), bitmap.getHeight(), 0, type, format, bitmap.getData());
		gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
		gl.glBindTexture(GL.GL_TEXTURE_2D, 0);
		return tx;
	}
}
