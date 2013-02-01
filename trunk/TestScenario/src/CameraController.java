
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;


// changes camera  

public class CameraController implements InputObserver{
	  
	 
	  private   int centreX;
	  private   int centreY;
      private int cx = 0;
      private int cy = 0;
      private int spost_x = 0;
      private int spost_y = 0;
      private int WIDTH = 600;
      private int HEIGHT = 600;
      private  Robot robot;
	  private MyCharModel myChar;
	  private SFMatrix3f matrix;
	private SFCamera camera;
	  
	  
	  public CameraController() throws AWTException{
	    
			 this.camera = new SFCamera(new SFVertex3f(0, 0, -2),
					new SFVertex3f(0, 0, 1), new SFVertex3f(1, 0, 0),
					new SFVertex3f(0, 1, 0), 1, 1, 20);
		 myChar = MyCharModel.getInstance(this);
		//initCamera();
	
	}

	

	@Override
	public void keyUpdate(KeyEvent e) {
	
		float code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP){
			SFVertex3f position = new SFVertex3f();
	
			camera.getF().addMult(0.1f, camera.getDir());	
			camera.update();
			
			
			position.setX(camera.getF().getX());
			position.setZ(camera.getF().getZ());
			position.setY(-0.5f);
			myChar.setMyPosition(position);
			
		}else if( code == KeyEvent.VK_DOWN){
			
			SFVertex3f position = new SFVertex3f();
			camera.getF().addMult(-0.1f, camera.getDir());
			camera.update();
			
			position.setX(camera.getF().getX());
			position.setZ(camera.getF().getZ());
			position.setY(-0.5f);
			myChar.setMyPosition(position);
			
		}else if( code == KeyEvent.VK_RIGHT){
			
			SFVertex3f position = new SFVertex3f();
			 matrix = SFMatrix3f.getRotationY((float)( Math.PI*0.01f));
		    camera.setDir( matrix.Mult(camera.getDir()) ); 
			 camera.setLeft(matrix.Mult(camera.getLeft()));
		    camera.update();
			position.setX(camera.getF().getX());
			position.setZ(camera.getF().getZ());
			position.setY(-0.5f);
			myChar.setMyPosition(position);
		}else if( code == KeyEvent.VK_LEFT){
			
			SFVertex3f position = new SFVertex3f();
			 matrix = SFMatrix3f.getRotationY((float)( -Math.PI*0.01f));
		    camera.setDir( matrix.Mult(camera.getDir()) ); 
			 camera.setLeft(matrix.Mult(camera.getLeft()));
		   camera.update();
			position.setX(camera.getF().getX());
			position.setZ(camera.getF().getZ());
			position.setY(-0.5f);
			myChar.setMyPosition(position);
		}
		
	}

	@Override
	public void mouseUpdate(MouseEvent e) {
		
		
	}

	
	public SFCamera getCamera(){
		
		return this.camera;
		
	}
	
	

	private void initCamera() {

		camera.setPerspective(true);
	   	camera.setLeft(new SFVertex3f(1,0,0));
	   	camera.setDir(new SFVertex3f(0,0,1));
		camera.setUp(new SFVertex3f(0,1,0));
	    camera.setLeftL(0.3f);
		camera.setUpL(0.3f);
        camera.setF(myChar.getMyPosition());
		camera.setDelta(1f);
		camera.setDistance(2000f);
	    camera.update();
	}

	void modelNotif(){
		
		//position received
		initCamera();
		
	}
}
