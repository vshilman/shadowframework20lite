package sfogl2;

import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.jogamp.opengl.util.Animator;

public class SFJOGLFrame {

	public static void createFrame(String name,int width,int height,GLEventListener listener){
		JFrame frame=new JFrame();
		
		frame.setSize(width,height);
		frame.setTitle(name);

		//Costruisco un GLCanvas
		GLCanvas canvas = new GLCanvas();
		//Associo il listener al canvas
		canvas.addGLEventListener(listener);
		//Il canvas è un componente grafico!! Posso aggiungerlo al Frame
		frame.getContentPane().add(canvas);
		Animator an = new Animator(canvas);
		an.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
