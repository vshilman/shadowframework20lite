package shadow.world.temp;

import shadow.math.SFVertex3f;

/**
 * An occluder is a Quadrilateral occluding 
 * some part of a scene.
 * 
 * @author Alessandro
 */
public class SFOccluder {

	private SFVertex3f p=new SFVertex3f();
	private SFVertex3f D1=new SFVertex3f();
	private SFVertex3f D2=new SFVertex3f();
	
	public SFOccluder(float posx,float posy,float posz, float d1x,float d1y,float d1z,
			float d2x,float d2y,float d2z) {
		super();
		getP().set3f(posx,posy,posz);
		getD1().set3f(d1x,d1y,d1z);
		getD2().set3f(d2x,d2y,d2z);	
	}

	public void setP(SFVertex3f p) {
		this.p = p;
	}

	public SFVertex3f getP() {
		return p;
	}

	public void setD1(SFVertex3f d1) {
		D1 = d1;
	}

	public SFVertex3f getD1() {
		return D1;
	}

	public void setD2(SFVertex3f d2) {
		D2 = d2;
	}

	public SFVertex3f getD2() {
		return D2;
	}
}
