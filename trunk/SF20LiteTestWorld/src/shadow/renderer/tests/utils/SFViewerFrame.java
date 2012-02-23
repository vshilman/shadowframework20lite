package shadow.renderer.tests.utils;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import shadow.pipeline.openGL20.SFGL2;
import shadow.system.SFDrawable;
import shadow.system.SFInitiator;

import com.sun.opengl.util.FPSAnimator;

public class SFViewerFrame extends JFrame{
	
	private static final long serialVersionUID=0;

	public SFViewerFrame(String title,int width,int height,SFDrawable drawable) {
		
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GLCapabilities capabilities=new GLCapabilities(GLProfile.get(GLProfile.GL2));
		capabilities.setStencilBits(8);
		GLCanvas canvas=new GLCanvas(capabilities);
		
		canvas.addGLEventListener(new SFViewerFrameListener(drawable));
		FPSAnimator animator=new FPSAnimator(canvas,30);
		animator.start();
		
		getContentPane().add(canvas);
	}
	
	private static class SFViewerFrameListener implements GLEventListener{
		private SFDrawable drawable;

		public SFViewerFrameListener(SFDrawable drawable) {
			super();
			this.drawable = drawable;
		}
		
		@Override
		public void display(GLAutoDrawable arg0) {
			SFGL2.setGl((GL2)(arg0.getGL()));
			
			SFInitiator.solveInitiables();
			
			drawable.draw();
		}

		@Override
		public void init(GLAutoDrawable arg0) {
			drawable.init();
		}
		
		@Override
		public void dispose(GLAutoDrawable arg0) {
			
		}
		
		@Override
		public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
				int arg4) {
			
		}
	}
}
