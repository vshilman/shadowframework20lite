package shadow.renderer.viewer;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;

import sfogl.integration.SFGL2;
import shadow.system.SFDrawable;
import shadow.system.SFEngine;

import com.jogamp.opengl.util.FPSAnimator;

public class SFDrawableCanvas extends GLCanvas{

	private static final long serialVersionUID = 0;
	
	private SFResizeListener resizeListener;
	
	public SFDrawableCanvas(GLCapabilities capabilities,SFDrawable drawable) {
		super(capabilities);
		
		addGLEventListener(new SFDrawableCanvasListener(drawable));
		FPSAnimator animator=new FPSAnimator(this,60);
		animator.start();
	}
	
	public void setResizeListener(SFResizeListener resizeListener) {
		this.resizeListener = resizeListener;
	}
	
	private class SFDrawableCanvasListener implements GLEventListener{
		private SFEngine sfEngine;

		public SFDrawableCanvasListener(SFDrawable drawable) {
			super();
			this.sfEngine = new SFEngine(drawable);
		}
		//private long timeOld;
		
		@Override
		public void display(GLAutoDrawable arg0) {

			GL2 gl = (GL2)(arg0.getGL());
			SFGL2.setGl(gl);
			
			gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

			try {
				sfEngine.draw();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void init(GLAutoDrawable arg0) {
			try {
				GL2 gl = (GL2)(arg0.getGL());
				SFGL2.setGl(gl);
				
				sfEngine.solveInitiables();
				sfEngine.refresh();
				
				//((SFInitiable)drawable).init();

				float d[] = new float[4];
				gl.glGetFloatv(GL2.GL_VIEWPORT, d, 0);
				int w=(int)d[2];
				int h=(int)d[3];
				if(resizeListener!=null)
					resizeListener.resize(w, h);
			} catch (Exception e) {
				e.printStackTrace();
				//System.exit(0);
			}
		}
		
		@Override
		public void dispose(GLAutoDrawable arg0) {
			
		}
		
		@Override
		public void reshape(GLAutoDrawable arg0, int x, int y, int w,
				int h) {
			if(resizeListener!=null)
				resizeListener.resize(w, h);
		}
	}
}
