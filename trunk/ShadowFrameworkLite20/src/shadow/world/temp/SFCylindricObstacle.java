package shadow.world.temp;

import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;
import shadow.renderer.SFNode;


public class SFCylindricObstacle implements SFObstacle{

	SFVertex3f position=new SFVertex3f();
	float ray,height;
	
	public SFCylindricObstacle(SFVertex3f position, float ray, float height) {
		super();
		this.position = position;
		this.ray = ray;
		this.height = height;
	}
//	@Override
//	public void draw() {
////		gl.glPushMatrix();
////		gl.glTranslatef(position.x, position.y, position.z);
////			gl.glRotatef(-90,1,0,0);
////				GLU glu=new GLU();
////				glu.gluCylinder(glu.gluNewQuadric(), ray, ray, height, 10,10);
//////			gl.glRotatef(90,1,0,0);	
//////		gl.glTranslatef(-position.x, -position.y, -position.z);
////		gl.glPopMatrix();
//	}

	@Override
	public int occludersCount() {
		return 0;
	}

	@Override
	public SFOccluder getOccluder(int index) {
		return null;
	}
	
	
	@Override
	public boolean intersect(SFVertex3f P, float ray, float h) {

		if(P.getY()+h<position.getY())
			return false;
		if(P.getY()>position.getY()+height)
			return false;
		
		float dx=P.getX()-position.getX();
		float dz=P.getZ()-position.getZ();
		float r=ray+this.ray;
		if(dx*dx+dz*dz<=r*r){
			return true;
		}

		return false;
	}

	@Override
	public boolean isObjectInCameraBounds(SFCamera camera) {
		
		return SFRectangularObstacle.isParallelepipedInCameraBound(camera, 
				new SFVertex3f(position.getX()-ray,position.getY(),position.getZ()-ray),
				new SFVertex3f(ray*2,height,ray*2));
	}
	

	public SFNode scene;

	public SFNode getScene() {
		return scene;
	}

	public void setScene(SFNode scene) {
		this.scene = scene;
	}

	public boolean isObjectOccluded(SFOccluder occluder,SFVertex3f F){
		return SFRectangularObstacle.isParallelepipedInOccluderShadow(F,occluder,
				new SFVertex3f(position.getX()-ray,position.getY(),position.getZ()-ray),
				new SFVertex3f(ray*2,height,ray*2));
	}
}
