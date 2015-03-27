package sfogl2;

import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class SFJOGLFrameListener implements GLEventListener{

	private int width,height;
	private SFOGLDrawable drawable;
	
	public SFJOGLFrameListener(SFOGLDrawable drawable) {
		super();
		this.drawable = drawable;
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		this.drawable.draw((GL2ES2)(drawable.getGL()));
	}
	
	@Override
	public void dispose(GLAutoDrawable drawable) {
		this.drawable.dispose((GL2ES2)(drawable.getGL()));
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		this.drawable.init((GL2ES2)(drawable.getGL()));
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		this.width=width;
		this.height=height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
