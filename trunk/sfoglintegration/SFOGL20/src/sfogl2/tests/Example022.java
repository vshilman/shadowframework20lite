package sfogl2.tests;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

import sfogl2.SFJOGLFrame;
import sfogl2.SFJOGLFrameListener;
import sfogl2.SFOGLDrawable;
import sfogl2.SFOGLRenderedTexture;
import sfogl2.SFOGLSystemState;
import sfogl2.tests.objects.BlurredScreenObject;
import sfogl2.tests.objects.SphereObject;

/**
 * Objectives : scene with Blur Effect  
 * 
 * @author Alessandro Martinelli
 */
public class Example022 {

	public static void main(String[] args) {
		SFJOGLFrame.createFrame("Example001", 600, 600, new SFJOGLFrameListener(new GraphicsListener()));
	}
	
	public static final int FRAMEBUFFERSIZE=512;
	private static SFOGLRenderedTexture renderedTexture=new SFOGLRenderedTexture(FRAMEBUFFERSIZE,FRAMEBUFFERSIZE,true);

	public static class GraphicsListener implements SFOGLDrawable{
		
		private SphereObject object=new SphereObject();
		private BlurredScreenObject screen=new BlurredScreenObject(FRAMEBUFFERSIZE,renderedTexture);

		public void draw(GL2ES2 gl) {

			//State setup
			int[] viewport=new int[4];
			gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
			
			SFOGLSystemState.cleanupColorDepth(gl, 1, 1, 1, 1);

			//apply rendered texure
			renderedTexture.apply(gl);
			
			//draw element
			object.draw(gl);

			//change state
			renderedTexture.unapply(gl);
			
			//State setup
		    gl.glViewport(0, 0, viewport[2],viewport[3]);
		    gl.glActiveTexture(GL2ES2.GL_TEXTURE0);
			
		    //draw element
			screen.draw(gl);
			
		}

		/* init method
		 * */
		public void init(GL2ES2 gl) {
			
			object.init(gl);

			renderedTexture.prepare(gl);

			screen.init(gl);
		}
		
		
		@Override
		public void dispose(GL2ES2 gl) {
		}
		
	}
}
