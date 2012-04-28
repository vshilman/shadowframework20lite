package test.jogl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.sun.opengl.util.Animator;

public class Test_sv9 implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {

	private Test_sv9Drawer drawer = new Test_sv9Drawer();

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("test_sv9");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLEventListener listener = new Test_sv9();
		GLCanvas canvas = new GLCanvas();

		canvas.addGLEventListener(listener);

		frame.getContentPane().add(canvas);
		canvas.setFocusable(true);
		canvas.addKeyListener((KeyListener) listener);
		canvas.addMouseListener((MouseListener) listener);
		canvas.addMouseMotionListener((MouseMotionListener) listener);

		Animator animator = new Animator(canvas);
		animator.start();

		frame.setVisible(true);
	}

	@Override
	public void display(GLAutoDrawable arg0) {
		GL2 gl = arg0.getGL().getGL2();

		drawer.drawScene(gl);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {

	}

	@Override
	public void init(GLAutoDrawable arg0) {
		GL2 gl = arg0.getGL().getGL2();
		drawer.initShaders(gl);
		drawer.initBuffers(gl);
		try {
			drawer.initTexture(gl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		drawer.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		drawer.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		drawer.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		drawer.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
