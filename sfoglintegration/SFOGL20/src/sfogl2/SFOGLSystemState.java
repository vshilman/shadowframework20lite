package sfogl2;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

public class SFOGLSystemState {

	public static void cleanupColorDepth(GL2ES2 gl,float red,float green,float blue,float alpha){
		gl.glClearColor(red, green, blue, alpha);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL.GL_DEPTH_TEST);
	}
}
