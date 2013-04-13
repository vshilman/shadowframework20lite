package viewer;
import controller.*;
import commons.*;
import interfaces.*;
import viewer.*;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import shadow.pipeline.openGL20.SFGL2;
import shadow.system.SFDrawable;
import shadow.system.SFInitiator;

import com.jogamp.opengl.util.FPSAnimator;

public class SFDrawableFrame extends JFrame{
	
	private static final long serialVersionUID=0;
	private InputHandler handler;
	
	public SFDrawableFrame(String title,int width,int height,SFDrawable drawable,SFFrameController... controllers) {
		
		setTitle(title);
		setSize(width, height);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GLCapabilities capabilities=new GLCapabilities(GLProfile.get(GLProfile.GL2));
		capabilities.setStencilBits(8);
		GLCanvas canvas=new GLCanvas(capabilities);
		
		try {
			handler = new InputHandler();
		} catch (AWTException e) {
			
			System.out.println("Can't initialize the input handler\n");
			e.printStackTrace();
			
		}
	    canvas.addMouseMotionListener(handler);
		canvas.addKeyListener(handler);
		canvas.setFocusable(true);
		canvas.requestFocus();
		
		canvas.addGLEventListener(new SFDrawableFrameListener(drawable));
		FPSAnimator animator=new FPSAnimator(canvas,60);
	//	BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
     //   Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	//	this.getContentPane().setCursor(blankCursor);
		animator.start();
		getContentPane().add(canvas);
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
