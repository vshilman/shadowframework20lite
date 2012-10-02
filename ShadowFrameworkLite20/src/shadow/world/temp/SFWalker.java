package shadow.world.temp;

import shadow.math.SFVertex3f;


/**
 * A Walker was a cylinder?
 * 
 * @author Alessandro 
 */
public class SFWalker {

	private SFVertex3f position=new SFVertex3f();
	private float h1;
	private float ray;
	
	public SFWalker(float posx,float posy,float posz,
			float h1,float ray) {
		super();
		position.set(posx,posy,posz);
		this.h1 = h1;
		this.ray=ray;
	}
	
	public SFWalker(SFWalker walker) {
		super();
		position.set(walker.position);
		h1=walker.h1;
		ray=walker.ray;
	}

	public SFVertex3f getPosition() {
		return position;
	}

	public void setPosition(SFVertex3f position) {
		this.position = position;
	}

	public float getH1() {
		return h1;
	}

	public void setH1(float h1) {
		this.h1 = h1;
	}

	public float getRay() {
		return ray;
	}

	public void setRay(float ray) {
		this.ray = ray;
	}
	
	
}
