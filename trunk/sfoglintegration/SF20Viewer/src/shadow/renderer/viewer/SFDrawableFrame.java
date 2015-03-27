package shadow.renderer.viewer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import shadow.system.SFDrawable;

public class SFDrawableFrame extends JFrame{
	
	private static final long serialVersionUID=0;
	
	private SFDrawableCanvas canvas;

	private JMenuBar bar=new JMenuBar();
	
//	private SFResizeListener resizeListener;
	
	/**
	 * Create a Frame able to show some SFDrawable 
	 * 
	 * @param title the Title of the frame
	 * @param width the width of the frame
	 * @param height the height of the frame
	 * @param drawable the Drawable to be show
	 * @param controllers (optional) a list of controllers which can be used to alter the drawable content
	 */
	public SFDrawableFrame(String title,int width,int height,SFDrawable drawable,SFFrameController... controllers) {
		
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GLCapabilities capabilities=new GLCapabilities(GLProfile.get(GLProfile.GL2));
		capabilities.setStencilBits(8);
		capabilities.setNumSamples(8);
		capabilities.setSampleBuffers(true);
		this.canvas=new SFDrawableCanvas(capabilities,drawable);
		
		
		setJMenuBar(bar);

		KeyListener listener=(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
					
					BufferedImage image=new BufferedImage(canvas.getWidth(), 
							canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics graphics=image.getGraphics();
					canvas.paintAll(graphics);
					
					try {
						ImageIO.write(image, "png", new File("screenshot.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		canvas.addKeyListener(listener);
		this.addKeyListener(listener);
		
		getContentPane().add(canvas);

		for (int i = 0; i < controllers.length; i++) {
			bar.add(generateMenu(controllers[i]));
		}
	}
	
	
	public void addController(SFFrameController controller){
		bar.add(generateMenu(controller));
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
	
	
//	public static SFGui generateFrame(String name,int width,int height){
//		SFGui viewer=new SFGui();
//		setupFrame(viewer, name, width, height);
//		return viewer;
//	}
//	
//
//	public static void setupFrame(SFGui viewer,String name, int width, int height){
//		SFDrawableFrame frame=new SFDrawableFrame(name, width, height, viewer);
//		frame.setResizeListener(viewer);
//		frame.addKeyListener(viewer);
//		frame.getGLCanvas().addKeyListener(viewer);
//		SFMouseAdapter adapter=new SFMouseAdapter(viewer);
//		frame.getGLCanvas().addMouseListener(adapter);
//		frame.getGLCanvas().addMouseMotionListener(adapter);
//		frame.getGLCanvas().addMouseWheelListener(adapter);
//		SFGL20Pipeline.setup();
//		frame.setVisible(true);
//		viewer.resize(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
//	}
}
