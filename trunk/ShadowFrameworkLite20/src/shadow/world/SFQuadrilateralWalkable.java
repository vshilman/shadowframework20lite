package shadow.world;

import shadow.math.SFVertex3f;


/**
 * 
 * @author Alessandro
 *
 */
public class SFQuadrilateralWalkable {
	
	private SFVertex3f position=new SFVertex3f();
	private SFVertex3f D1=new SFVertex3f();
	private SFVertex3f D2=new SFVertex3f();
	private float h1;
	private float h2;
	private float h3;
	private float h4;

	private float Dmat[];
	
	

	public SFQuadrilateralWalkable(float posx,float posy,float posz, float d1x,float d1y,float d1z,
			float d2x,float d2y,float d2z, 
			float h1, float h2, float h3, float h4) {
		super();
		this.position = new SFVertex3f(posx,posy,posz);
		D1 = new SFVertex3f(d1x,d1y,d1z);
		D2 = new SFVertex3f(d2x,d2y,d2z);
		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;
	}

	public SFVertex3f getPosition() {
		return position;
	}

	private void evaluateProjection(){
		Dmat=new float[4];
		float d1x=D1.getX();
		float d1z=D1.getZ();
		float d2x=D2.getX();
		float d2z=D2.getZ();
		float delta=d1x*d2z-d1z*d2x;
		if(delta!=0){
			delta=1.0f/delta;
			Dmat[0]=d2z*delta;
			Dmat[1]=-d2x*delta;
			Dmat[2]=-d1z*delta;
			Dmat[3]=d1x*delta;
		}
		
	}
	
	public void get2DPlacementCoord(float x,float z,float coords[]){
		if(Dmat==null)
			evaluateProjection();
		x-=getPosition().getX();
		z-=getPosition().getZ();
		coords[0]=Dmat[0]*x+Dmat[1]*z;
		coords[1]=Dmat[2]*x+Dmat[3]*z;
	}
	
	public void setPosition(SFVertex3f position) {
		this.position = position;
	}

	public SFVertex3f getD1() {
		return D1;
	}

	public void setD1(SFVertex3f d1) {
		D1 = d1;
	}

	public SFVertex3f getD2() {
		return D2;
	}

	public void setD2(SFVertex3f d2) {
		D2 = d2;
	}

	public float getH1() {
		return h1;
	}

	public void setH1(float h1) {
		this.h1 = h1;
	}

	public float getH2() {
		return h2;
	}

	public void setH2(float h2) {
		this.h2 = h2;
	}

	public float getH3() {
		return h3;
	}

	public void setH3(float h3) {
		this.h3 = h3;
	}

	public float getH4() {
		return h4;
	}

	public void setH4(float h4) {
		this.h4 = h4;
	}
}