package shadow.pipeline.openGL20.tutorials.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.openGL20.SFGL2;
import shadow.system.SFInitiator;

import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

/**
 * controls:
 * <ul>
 * 		<li><b>W</b>: switch to wireframe mode and back</li>
 * </ul>
 * @author Alessandro
 */
public abstract class SFTutorial extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = 0;

	private boolean wireframe;
	
	public abstract void init();
	
	public abstract void render();
	
	public static float rotateX=0,rotateY=0,rotateZ=0;
	
	public void prepareFrame(String frameName,int width,int height){

	
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(frameName);
		
		GLCapabilities capabilities=new GLCapabilities(GLProfile.get(GLProfile.GL2));
		capabilities.setStencilBits(8);
		GLCanvas canvas=new GLCanvas(capabilities);
		canvas.addGLEventListener(new SFEventListener());
		FPSAnimator animator=new FPSAnimator(canvas,30);
		animator.start();
		
		getContentPane().add(canvas);
		
		setVisible(true);
		
		canvas.addKeyListener(this);
		addKeyListener(this);
	}

	public class SFEventListener implements GLEventListener{
		
		@Override
		public void display(GLAutoDrawable arg0) {
			
			GL2 gl=(GL2)(arg0.getGL());
			gl.glClearColor(1,1,1,1);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT | GL.GL_STENCIL_BUFFER_BIT);
			
			gl.glEnable(GL.GL_DEPTH_TEST);
			
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK,wireframe?GL2.GL_LINE:GL2.GL_FILL);
			SFPipeline.getSfPipelineGraphics().rotateModel(rotateX, rotateY, rotateZ);
			
			SFGL2.setGl((GL2)arg0.getGL());
			
			SFInitiator.solveInitiables();
			
			render();
		}
		
		@Override
		public void dispose(GLAutoDrawable arg0) {
			
		}
		
		@Override
		public void init(GLAutoDrawable arg0) {
			
			SFGL2.setGl((GL2)arg0.getGL());
			
			SFInitiator.solveInitiables();
			
			SFTutorial.this.init();
		}
		
		@Override
		public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
				int arg4) {
			
		}
		
	}
	
	public int loadImageTexture(String imageFile){
		try {
			Texture texture=TextureIO.newTexture(new File(imageFile),true);
			texture.bind();
			
			return texture.getTextureObject();
			
		} catch (GLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W){
			wireframe=!wireframe;
		}
		rotateX = updateRotation(e, KeyEvent.VK_X, rotateX);
		rotateY = updateRotation(e, KeyEvent.VK_Y, rotateY);
		rotateZ = updateRotation(e, KeyEvent.VK_Z, rotateZ);
	}

	private float updateRotation(KeyEvent e, int keyCode, float value) {
		if(e.getKeyCode()==keyCode){
			if(e.isShiftDown())
				value+=0.1f;
			else
				value-=0.1f;
		}
		return value;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
