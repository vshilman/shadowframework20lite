package sfogl2;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLES2;

public class SFOGLRenderBuffer {

	private int renderBufferObject;
	
	public void setup(GL2ES2 gl, int width, int height){
		this.renderBufferObject=createRenderBuffer(gl, width, height);
	}
	
	public int getRenderBufferObject() {
		return renderBufferObject;
	}

	public void destroy(GL2ES2 gl){
		destroyRenderBuffer(gl,renderBufferObject);
	}

	public static int createRenderBuffer(GL2ES2 gl, int width, int height) {
		//Create a RenderBuffer to Be Used As Depth Buffer in the FrameBufferObject
		int rbo[]=new int[1];
		gl.glGenRenderbuffers(1, rbo, 0);
		int rb=rbo[0];
		gl.glBindRenderbuffer(GL.GL_RENDERBUFFER, rb);
		gl.glRenderbufferStorage(GL.GL_RENDERBUFFER, GLES2.GL_DEPTH_COMPONENT16, width, height);
		gl.glBindRenderbuffer(GL.GL_RENDERBUFFER, 0);
		return rb;
	}
	
	public static void destroyRenderBuffer(GL2ES2 gl, int renderBufferObject) {
		int[] textures = { renderBufferObject };
		gl.glDeleteRenderbuffers(GL.GL_TEXTURE_2D, textures, 1);
	}
}
