package shadow.pipeline.openGL20.images;

import java.nio.ByteBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.image.SFBitmap;
import shadow.image.SFFormat;
import shadow.image.SFTextureData;
import shadow.pipeline.openGL20.SFGL2;

/**
 * An object wrapping most of Texturing functionalities
 * 
 * @author Alessandro
 */
public class SFGL20Texture extends SFTextureData implements SFGL20ImageObject{

	private int textureObject=-1;

	public SFGL20Texture(int width, int height, SFFormat format,
			Filter filters, WrapMode wrapS, WrapMode wrapT) {
		super(width, height, format, filters, wrapS, wrapT);
	}

	private int getGLMinFilter(){
		switch (getFilters()) {
			case NEAREST: return GL.GL_NEAREST;
			case LINEAR: return GL.GL_LINEAR;
			case LINEAR_MIPMAP: return GL.GL_LINEAR_MIPMAP_LINEAR;
		default:
			return GL.GL_LINEAR_MIPMAP_LINEAR;
		}
	}
	
	private int getGLMagFilter(){
		switch (getFilters()) {
			case NEAREST: return GL.GL_NEAREST;
			case LINEAR: return GL.GL_LINEAR;
			case LINEAR_MIPMAP: return GL.GL_LINEAR;
		default:
			return GL.GL_LINEAR;
		}
	}
	
	private int getGLWrap(WrapMode mode){
		switch (mode) {
			case REPEAT: return GL.GL_REPEAT;
			case MIRRORED_REPEAT: return GL.GL_MIRRORED_REPEAT;
			case CLAMP_TO_EDGE: return GL.GL_CLAMP_TO_EDGE;
		default:
			return GL.GL_REPEAT;
		}
	}
	
	@Override
	public void build() {

		if(textureObject==-1){
			GL2 gl=SFGL2.getGL();
			int txo[]=new int[1];
			gl.glGenTextures(1,txo,0);
			textureObject=txo[0];
			gl.glBindTexture(GL.GL_TEXTURE_2D, textureObject);
			setupParameters(gl);
			gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, SFGL20RenderedTextureFactory.getGLFormat(getFormat()), getWidth(), getHeight(), 0,
					GL.GL_RGB,GL.GL_UNSIGNED_BYTE, null);
		}

	}

	private void setupParameters(GL2 gl) {
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, getGLMinFilter());
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, getGLMagFilter());
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, getGLWrap(getWrapS()));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, getGLWrap(getWrapS()));
	}
	
	@Override
	public void apply(int textureLevel) {
		GL2 gl=SFGL2.getGL();
		build();
		gl.glActiveTexture(GL.GL_TEXTURE0+textureLevel);
		gl.glEnable(GL.GL_TEXTURE_2D);
		SFGL2.getGL().glBindTexture(GL.GL_TEXTURE_2D, textureObject);
	}
	
	public void loadBitmap(SFBitmap bitmap) {

		GL2 gl=SFGL2.getGL();
		if(textureObject==-1){
			int txo[]=new int[1];
			gl.glGenTextures(1,txo,0);
			textureObject=txo[0];
		}
		gl.glBindTexture(GL.GL_TEXTURE_2D, textureObject);
		setupParameters(gl);
		if(bitmap.getFormat()==SFFormat.GRAY8){
			gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL2.GL_LUMINANCE, 
					bitmap.getWidth(), bitmap.getHeight(), 0, GL2.GL_RED, GL.GL_UNSIGNED_BYTE, (ByteBuffer)bitmap.getData());
		}else{
			gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, 
					bitmap.getWidth(), bitmap.getHeight(), 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, (ByteBuffer)bitmap.getData());
		}
		gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
	}
	
	@Override
	public void destroy() {

		GL2 gl=SFGL2.getGL();
		int[] textures={textureObject};
		gl.glDeleteTextures(GL.GL_TEXTURE_2D, textures, 1);
	}

	public int getTextureObject() {
		return textureObject;
	}
	
	
}
