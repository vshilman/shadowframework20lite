package sfogl2;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;

public class SFOGLFrameBuffer {

	private int frameBufferObject;
	
	public void prepare(GL2ES2 gl){
		this.frameBufferObject=createFrameBuffer(gl);
	}
	
	public int getFrameBufferObject() {
		return frameBufferObject;
	}
	
	public void apply(GL2ES2 gl){
		gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, frameBufferObject);
	}
	
	public void unapply(GL2ES2 gl){
		gl.glBindFramebuffer(GL2.GL_FRAMEBUFFER,0);
	}
	
	public void attachRenderBuffer(GL2ES2 gl,int attachment,SFOGLRenderBuffer renderBuffer){
		gl.glFramebufferRenderbuffer(GL.GL_FRAMEBUFFER, attachment, GL.GL_RENDERBUFFER, renderBuffer.getRenderBufferObject());
	}
	
	public void attachTexture(GL2ES2 gl,int attachment,SFOGLTexture2D texture){
		gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER, attachment, GL.GL_TEXTURE_2D, texture.getTextureObject(), 0);
	}

	public void attachCubeMap(GL2ES2 gl,int attachment,SFOGLCubeMap cubmap,int index){
		gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER, attachment, SFOGLCubeMap.MAPS[index], cubmap.getTextureObject(), 0);
	}	
	
	public void destroy(GL2ES2 gl){
		destroyFrameBuffer(gl, frameBufferObject);
	}
	

	public static int createFrameBuffer(GL2ES2 gl) {
		int fbo[]=new int[1];
		gl.glGenFramebuffers(1, fbo, 0);
		int frameBuffer=fbo[0];
		gl.glBindFramebuffer(GL.GL_FRAMEBUFFER,frameBuffer);
		return frameBuffer;
	}
	
	public static void destroyFrameBuffer(GL2ES2 gl,int frameBufferObject) {
		int nfbo[]=new int[1];
		nfbo[0]=frameBufferObject;
		gl.glDeleteFramebuffers(1, nfbo, 0);
	}
	
	public void attachRenderBuffer(GL2ES2 gl,int attachment,int renderBuffer){
		gl.glFramebufferRenderbuffer(GL.GL_FRAMEBUFFER, attachment, GL.GL_RENDERBUFFER, renderBuffer);
	}

	public static void checkFrameBuffer(GL2ES2 gl) {
		if(gl.glCheckFramebufferStatus(GL.GL_FRAMEBUFFER) != GL.GL_FRAMEBUFFER_COMPLETE){
			System.err.println("Framebuffer is not complete");
			throw new RuntimeException();
		}
	}
}
