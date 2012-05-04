package shadow.world.temp;

import shadow.math.SFVertex3f;


public abstract class ShadowPickable{

	public SFVertex3f p;
	public SFVertex3f dim;
	
	public ShadowPickable(SFVertex3f p, SFVertex3f dim) {
		super();
		this.p = p;
		this.dim = dim;
	}
	
	public abstract void enter();
	public abstract void exit();
	public abstract void press();
	public abstract void release();
}
