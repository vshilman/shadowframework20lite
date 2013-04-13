package viewer;

import interfaces.ModelMyAvatar;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

import model.MyAvatarHandler;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;

// changes camera  

public class CameraHandler implements ModelMyAvatar {

	private MyAvatarHandler myChar;
	private SFMatrix3f matrix;
	private SFCamera camera;
   private float inc = 0;
	public CameraHandler() throws AWTException {

		this.camera = new SFCamera(new SFVertex3f(0, 0, -2), new SFVertex3f(0,
				0, 1), new SFVertex3f(1, 0, 0), new SFVertex3f(0, 1, 0), 1, 1,
				100);
		initCamera();

		myChar = MyAvatarHandler.getInstance(this);

	}

	

	public SFCamera getCamera() {

		return camera;

	}

	private void initCamera() {
		camera.setPerspective(true);
		camera.setF(new SFVertex3f(0, 0.2, 0));

		camera.setLeft(new SFVertex3f(1, 0, 0));
		camera.setDir(new SFVertex3f(0, 0, 1));
		camera.setUp(new SFVertex3f(0, 1, 0));
		camera.setLeftL(0.3f);
		camera.setUpL(0.3f);
		camera.setDelta(0.1f);
		camera.setDistance(100f);
		camera.update();
	}

	

	@Override
	public void notifyConnection() {
		// TODO Auto-generated method stub
		initCamera();

	}

	@Override
	public void notifyChgState(float code) {
	
		if(code == KeyEvent.VK_UP){
			
			camera.getF().addMult(0.1f/10, camera.getDir());
			System.out.println("camera: " +camera.getF());

		}else if(code == KeyEvent.VK_DOWN){
			camera.getF().addMult(-0.1f/10, camera.getDir());
			
			
		}
		else if(code == KeyEvent.VK_RIGHT){
		     SFMatrix3f matrix = SFMatrix3f.getRotationY((float)( Math.PI*0.01f));
	
			camera.setDir(matrix.Mult(camera.getDir()));
            camera.setLeft(matrix.Mult(camera.getLeft()));
            camera.setUp(matrix.Mult(camera.getUp()));
		
		}else if(code == KeyEvent.VK_LEFT){
		     SFMatrix3f matrix = SFMatrix3f.getRotationY((float)(- Math.PI*0.01f));
		 	
			camera.setDir(matrix.Mult(camera.getDir()));
            camera.setLeft(matrix.Mult(camera.getLeft()));
            camera.setUp(matrix.Mult(camera.getUp()));

		}else if(code == KeyEvent.VK_B){
			
			camera.setF(new SFVertex3f(0, 0.3, -3));
			camera.setDir(new SFVertex3f(0,0,1));
			camera.setLeft(new SFVertex3f(1,0,0));
			camera.setUp (new SFVertex3f(0,1,0));
			camera.update();
		}
		camera.update();
		//camera.setLeft(myChar.getMyDirection().Mult(camera.getLeft()));
		//camera.setUp(myChar.getMyDirection().Mult(camera.getUp()));
	

	}
}
