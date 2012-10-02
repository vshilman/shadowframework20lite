package shadow.world.temp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;


public class SFWalkerControl implements ActionListener{

	private static final double Pi2=2*Math.PI;
	
	private SFWalker walker;
	private SFWalker walker_temp;
	private SFCamera camera;
	
	private SFVertex3f dest=new SFVertex3f();
	
	private boolean hasDest=false;
	
	private float vicinityControl=1.0f;
	
	private float cameraAngle=0;
	private float cameraInclination=0.5f;
	private float cameraDistance=0.7f;
	private float cameraH_watching=0.35f;
	private float cameraD=0.03f;
	
	private SFGenericWalkablesNet net;
	private float speed;
	private float alpha=0;
	private float cos_Dir=1;
	private float sin_Dir=1;
	private float cos_Dir_Camera=1;
	private float sin_Dir_Camera=1;
	
	private int on_walkable=0;
	
	private boolean pressed_left=false;
	private boolean pressed_right=false;
	private boolean pressed_up=false;
	private boolean pressed_down=false;
	
	private boolean changedCamera=true;
	
	public SFWalkerControl(SFWalker walker,SFGenericWalkablesNet net,
			SFCamera camera,float alpha,float speed) {
		super();
		this.walker = walker;
		this.net=net;
		walker_temp=new SFWalker(walker);
		this.speed=speed;
		changeAlpha(alpha);
		on_walkable=net.findWalkable(walker.getPosition());
		this.camera=camera;
		setupCamera();
		
		Timer t=new Timer(30,this);
		t.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(pressed_left){
			cameraAngle*=0.9f;
			updateCameraAngle();
			changeAlpha(0.1f);
			changedCamera=true;
			hasDest=false;
		}
		if(pressed_right){
			cameraAngle*=0.9f;
			updateCameraAngle();
			changeAlpha(-0.1f);
			changedCamera=true;
			hasDest=false;
		}
		if(pressed_up){
			cameraAngle*=0.9f;
			updateCameraAngle();
			move(true);
			changedCamera=true;
			hasDest=false;
		}
		if(pressed_down){
			cameraAngle*=0.9f;
			updateCameraAngle();
			move(false);
			changedCamera=true;
			hasDest=false;
		}
		if(hasDest){
			
			float deltaz=dest.getZ()-walker.getPosition().getZ();
			float deltax=dest.getX()-walker.getPosition().getX();
			
			float alpha_dest=(float)(Math.atan2(
					deltaz,deltax
			));
			
			float deltaAlpha=alpha_dest-alpha;
			while(deltaAlpha>=Pi2*0.5f){
				deltaAlpha-=Pi2;
			}
			while(deltaAlpha<=-Pi2*0.5f){
				deltaAlpha+=Pi2;
			}
			changeAlpha(deltaAlpha*0.3f);
			if(deltaz*deltaz+deltax*deltax<=speed*speed*1.05f){//distanza � minore della speed ti fermi
				hasDest=false;
			}
			move(true);
			changedCamera=true;
		}
		setupCamera();
	}
	
	
	public void setupCamera(){
		
		int trial=0;
		if(changedCamera){
			boolean ok=false;
			
			while(!ok){
				float cameraInclination=this.cameraInclination*vicinityControl;
				float cameraDistance=this.cameraDistance*vicinityControl;
				setupCamera(cameraInclination, cameraDistance);
				int new_on_walkable = getCameraWalkableIndex();
				
				if(new_on_walkable<0){
					if(vicinityControl>0.00001f)
						vicinityControl*=0.95f;
					
				}else {
					if(trial==0){//try to enlarge
						ok=false;
						vicinityControl*=1.1f;
						if(vicinityControl>1){
							vicinityControl=1;
						}else{
							SFVertex3f F_temp=new SFVertex3f(camera.getF());
							SFVertex3f Dir_temp=new SFVertex3f(camera.getDir());
							cameraInclination=this.cameraInclination*vicinityControl;
							cameraDistance=this.cameraDistance*vicinityControl;
							setupCamera(cameraInclination, cameraDistance);
							new_on_walkable = getCameraWalkableIndex();
							
							if(new_on_walkable<0){
								camera.getF().set(F_temp);
								camera.getDir().set(Dir_temp);
								ok=true;
							}else{
								return;
							}
						}
					}
					ok=true;
				}

				trial++;
				if(trial>20)
					break;
			}
		}
		if(!hasDest)
			changedCamera=false;
	}

	private int getCameraWalkableIndex() {
		SFVertex3f v=new SFVertex3f(camera.getF());
		v.add(camera.getDir());
		float ray=camera.getLeftL();
		if(camera.getUpL()>camera.getLeftL())
			ray=camera.getUpL();
		float h=v.getY()+ray-walker.getPosition().getY();		
		int new_on_walkable=net.canWalk(on_walkable,v,ray,h);
		return new_on_walkable;
	}

	private void setupCamera(float cameraInclination, float cameraDistance) {
		float cosInc=(float)(Math.cos(cameraInclination));
		float sinInc=(float)(Math.sin(cameraInclination));
		
		camera.getF().setX(walker.getPosition().getX()-cos_Dir_Camera*cameraDistance*cosInc);
		camera.getF().setY(cameraH_watching+walker.getPosition().getY()+cameraDistance*sinInc);
		camera.getF().setZ(walker.getPosition().getZ()-sin_Dir_Camera*cameraDistance*cosInc);
		camera.getDir().setX(cameraD*cos_Dir_Camera*cosInc);
		camera.getDir().setY(-cameraD*sinInc);
		camera.getDir().setZ(cameraD*sin_Dir_Camera*cosInc);
		camera.update();
	}	
	
	public void move(boolean up){
		//I'm considering the ray in this process.
		float up_val=up?1:-1;
		walker_temp.getPosition().set(walker.getPosition());
		walker_temp.getPosition().setX(walker_temp.getPosition().getX()+cos_Dir*speed*up_val);
		walker_temp.getPosition().setZ(walker_temp.getPosition().getZ()+sin_Dir*speed*up_val);
		
		int new_on_walkable=net.canWalk(on_walkable,walker_temp.getPosition(),walker.getRay(),walker.getH1());
		
		if(new_on_walkable>=0){
			on_walkable=new_on_walkable;
			walker.getPosition().set(walker_temp.getPosition());
			
			walker.getPosition().setY(net.getY(on_walkable, walker.getPosition()));
			
			System.err.println("walker.getPosition().getY() NormalMove " + walker.getPosition().getY());
			/*walkable.getPosition().getY()+coords[0]*walkable.getD1().getY()+
				coords[1]*walkable.getD2().getY();*/
		}else if(new_on_walkable!=-2){
			net.moveOnEdge(on_walkable, walker.getPosition(), walker_temp.getPosition(), 
					walker.getRay(),walker.getH1());
			System.err.println("walker.getPosition().getY() EdgeMove " + walker.getPosition().getY());
		}
	}
	
	public void changeAlpha(float deltaAlpha){
		alpha+=deltaAlpha;
		cos_Dir=(float)(Math.cos(alpha));
		sin_Dir=(float)(Math.sin(alpha));
		updateCameraAngle();
	}

	private void updateCameraAngle() {
		while(cameraAngle>=Pi2*0.5f){
			cameraAngle-=Pi2;
		}
		while(cameraAngle<=-Pi2*0.5f){
			cameraAngle+=Pi2;
		}
		cos_Dir_Camera=(float)(Math.cos(alpha+cameraAngle));
		sin_Dir_Camera=(float)(Math.sin(alpha+cameraAngle));
	}
	
	
	public void clickToMove(float x,float y){
		SFVertex3f pixel=new SFVertex3f();
		pixel.add(camera.getDir());
		pixel.addMult(x, camera.getLeft());
		pixel.addMult(y, camera.getUp());
		
		hasDest=net.intersect(on_walkable,camera.getF(),pixel,this.dest);
	}
	
	

	public float getAlpha() {
		return alpha;
	}


	public void setAlpha(float alpha) {
		changeAlpha(alpha-this.alpha);
	}


	public float getCos_Dir() {
		return cos_Dir;
	}


	public void setCos_Dir(float cosDir) {
		cos_Dir = cosDir;
	}


	public float getSin_Dir() {
		return sin_Dir;
	}


	public void setSin_Dir(float sinDir) {
		sin_Dir = sinDir;
	}

	public boolean isPressed_left() {
		return pressed_left;
	}

	public void setPressed_left(boolean pressedLeft) {
		pressed_left = pressedLeft;
	}

	public boolean isPressed_right() {
		return pressed_right;
	}

	public void setPressed_right(boolean pressedRight) {
		pressed_right = pressedRight;
	}

	public boolean isPressed_up() {
		return pressed_up;
	}

	public void setPressed_up(boolean pressedUp) {
		pressed_up = pressedUp;
	}

	public boolean isPressed_down() {
		return pressed_down;
	}

	public void setPressed_down(boolean pressedDown) {
		pressed_down = pressedDown;
	}

	public float getCameraAngle() {
		return cameraAngle;
	}

	public void setCameraAngle(float cameraAngle) {
		this.cameraAngle = cameraAngle;
		updateCameraAngle();
		changedCamera=true;
	}

	public float getCameraInclination() {
		return cameraInclination;
	}

	public void setCameraInclination(float cameraInclination) {
		this.cameraInclination = cameraInclination;
		changedCamera=true;
	}

	public float getCameraDistance() {
		return cameraDistance;
	}

	public void setCameraDistance(float cameraDistance) {
		this.cameraDistance = cameraDistance;
		changedCamera=true;
	}

	public SFVertex3f getDest() {
		return dest;
	}

	public void setDest(SFVertex3f dest) {
		this.dest = dest;
	}
	
	
}
