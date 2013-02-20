package shadow.renderer.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import shadow.pipeline.openGL20.SFGL2;
import shadow.system.SFDrawable;
import shadow.system.SFInitiator;
import shadow.system.SFUpdater;

import com.sun.opengl.util.FPSAnimator;

public class SFDrawableFrame extends JFrame{
	
	private static final long serialVersionUID=0;
	
	private GLCanvas canvas;

	public SFDrawableFrame(String title,int width,int height,SFDrawable drawable,SFFrameController... controllers) {
		
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GLCapabilities capabilities=new GLCapabilities(GLProfile.get(GLProfile.GL2));
		capabilities.setStencilBits(8);
		this.canvas=new GLCanvas(capabilities);
		
		canvas.addGLEventListener(new SFDrawableFrameListener(drawable));
		FPSAnimator animator=new FPSAnimator(canvas,60);
		animator.start();
		
		JMenuBar bar=new JMenuBar();
		
		for (int i = 0; i < controllers.length; i++) {
			bar.add(generateMenu(controllers[i]));
		}
		
		setJMenuBar(bar);
		
		getContentPane().add(canvas);
	}
	
	
	
	public GLCanvas getGLCanvas() {
		return canvas;
	}



	public JMenu generateMenu(final SFFrameController controller){
		JMenu menu=new JMenu(controller.getName());
		for (int i = 0; i < controller.getAlternatives().length; i++) {
			final int alternativeIndex = i;
			JMenuItem item=new JMenuItem(controller.getAlternatives()[i]);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.select(alternativeIndex);
				}
			});
			menu.add(item);
		}
		return menu;
	}
	
	private static class SFDrawableFrameListener implements GLEventListener{
		private SFDrawable drawable;

		public SFDrawableFrameListener(SFDrawable drawable) {
			super();
			this.drawable = drawable;
		}
		//private long timeOld;
		
		@Override
		public void display(GLAutoDrawable arg0) {
			SFGL2.setGl((GL2)(arg0.getGL()));

			SFUpdater.refresh();
			SFInitiator.solveInitiables();
			
			/*long time1=System.nanoTime();
			float time=(time1-timeOld)*0.001f*0.001f;
			System.out.println("Cross Time "+time+" "+(time1-timeOld));*/
			
			drawable.draw();
			
			/*long time2=System.nanoTime();
			timeOld=time1;
			time=(time2-time1)*0.001f*0.001f;
			System.out.println("Time "+time+" "+(time2-time1));*/
		}

		@Override
		public void init(GLAutoDrawable arg0) {
			SFGL2.setGl((GL2)(arg0.getGL()));

			SFUpdater.refresh();
			SFInitiator.solveInitiables();
			
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
