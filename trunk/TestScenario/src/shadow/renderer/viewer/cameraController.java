package shadow.renderer.viewer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;


// changes camera  

public class cameraController implements InputObserver{
	  
	 
	  private   int centreX;
	  private   int centreY;
      private int cx = 0;
      private int cy = 0;
      private int spost_x = 0;
      private int spost_y = 0;
      private int WIDTH = 600;
      private int HEIGHT = 600;
      private  Robot robot;
	  private SFCamera camera;
	  private MyCharModel myChar;
	  private SFMatrix3f matrix;
	  public cameraController(SFCamera cam) throws AWTException{
	    
		 camera = cam;
		 initCamera();
		 robot = new Robot();
         centreX = WIDTH/2 ;
         centreY = HEIGHT/2;
         robot.mouseMove(centreX, centreY);
	}

	

	@Override
	public void keyUpdate(KeyEvent e) {
	
		float code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP){
			SFVertex3f position = new SFVertex3f();

			camera.getF().addMult(0.05f, camera.getDir());
			position.setX(camera.getF().getX());
			position.setZ(camera.getF().getZ());
			position.setY(0f);
			myChar.setMyPosition(position);

		}else if( code == KeyEvent.VK_DOWN){
			
			SFVertex3f position = new SFVertex3f();
			camera.getF().addMult(-0.05f, camera.getDir());
			
			position.setX(camera.getF().getX());
			position.setZ(camera.getF().getZ());
			position.setY(0f);
			myChar.setMyPosition(position);
		}
		
	}

	@Override
	public void mouseUpdate(MouseEvent e) {
		
		
		 cx = e.getXOnScreen();
         cy = e.getYOnScreen();
         spost_x = (cx - centreX);
         spost_y = (cy - centreY);   
         robot.mouseMove(centreX, centreY);
         matrix = SFMatrix3f.getRotationY((float)((spost_x * 0.0005f) * Math.PI));
         camera.setDir( matrix.Mult(camera.getDir()) ); 
         camera.setLeft( matrix.Mult(camera.getLeft()) );
         camera.setUp( matrix.Mult(camera.getUp()) ); 
	
       
	}

	
	

	private void initCamera() {
		
		myChar = MyCharModel.getInstance();
		camera.setF(new SFVertex3f(0,0.3,-2));
		//setup Camera's Dir, that's the Direction to which camera is oriented or camera z-axis
		camera.setDir(new SFVertex3f(0,0,0.86));
		//setup Camera's Left, that's the camera x-axis
		camera.setLeft(new SFVertex3f(1,0,0));
		//setup Camera's Up, that's the camera y-axis
		//viewer.getCamera().setUp(new SFVertex3f(0,0.86,0.5));
		camera.setUp(new SFVertex3f(0,0.86,0.1));
		camera.setPerspective(true);
		camera.update();
		myChar.setMyPosition(new SFVertex3f(0f,0f, -2f));
	}

}
