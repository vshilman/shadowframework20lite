package shadow.math;

import shadow.system.SFInitiable;

public class SFTransform3f extends SFValuenf implements SFInitiable{

	public SFTransform3f() {
		super(12);
		v[0]=1;
		v[4]=1;
		v[8]=1;
	}

	@Override
	public SFValuenf cloneValue() {
		SFTransform3f transform = new SFTransform3f();
		transform.set(this);
		return transform;
	}

	public void setPosition(float x, float y, float z) {
		v[9] = x;
		v[10] = y;
		v[11] = z;
	}

	public void setMatrix(float a, float b, float c, float d, float e, float f,
			float g, float h, float i) {
		v[0] = a;
		v[1] = b;
		v[2] = c;
		v[3] = d;
		v[4] = e;
		v[5] = f;
		v[6] = g;
		v[7] = h;
		v[8] = i;
	}

	public void setMatrix(SFMatrix3f matrix) {
		for (int i = 0; i < matrix.v.length; i++) {
			this.v[i] = matrix.v[i];
		}
	}

	public void setPosition(SFVertex3f position) {
		for (int i = 0; i < position.v.length; i++) {
			this.v[i + 9] = position.v[i];
		}
	}

	public void getMatrix(SFMatrix3f matrix) {
		for (int i = 0; i < matrix.v.length; i++) {
			matrix.v[i] = this.v[i];
		}
	}

	public void getPosition(SFVertex3f position) {
		for (int i = 0; i < position.v.length; i++) {
			position.v[i] = this.v[i + 9];
		}
	}
	
	
	public void transform(SFValuenf position) {
		float x=position.get()[0];
		float y=position.get()[1];
		float z=position.get()[2];
		position.get()[0]=x*v[0]+y*v[1]+z*v[2]+v[9];
		position.get()[1]=x*v[3]+y*v[4]+z*v[5]+v[10];
		position.get()[2]=x*v[6]+y*v[7]+z*v[8]+v[11];
	}
	
	public void transformDir(SFValuenf dir) {
		float x=dir.get()[0];
		float y=dir.get()[1];
		float z=dir.get()[2];
		dir.get()[0]=x*v[0]+y*v[1]+z*v[2];
		dir.get()[1]=x*v[3]+y*v[4]+z*v[5];
		dir.get()[2]=x*v[6]+y*v[7]+z*v[8];
	}

	private static float[] multTmpVal=new float[12];
	public synchronized void mult(SFTransform3f transform){
		
		float[] val=transform.v;
		
		multTmpVal[0]=v[0]*val[0]+v[1]*val[3]+v[2]*val[6];
		multTmpVal[1]=v[0]*val[1]+v[1]*val[4]+v[2]*val[7];
		multTmpVal[2]=v[0]*val[2]+v[1]*val[5]+v[2]*val[8];
		
		multTmpVal[3]=v[3]*val[0]+v[4]*val[3]+v[5]*val[6];
		multTmpVal[4]=v[3]*val[1]+v[4]*val[4]+v[5]*val[7];
		multTmpVal[5]=v[3]*val[2]+v[4]*val[5]+v[5]*val[8];
		
		multTmpVal[6]=v[6]*val[0]+v[7]*val[3]+v[8]*val[6];
		multTmpVal[7]=v[6]*val[1]+v[7]*val[4]+v[8]*val[7];
		multTmpVal[8]=v[6]*val[2]+v[7]*val[5]+v[8]*val[8];
		
		multTmpVal[9]=v[0]*val[9]+v[1]*val[10]+v[2]*val[11]+v[9];
		multTmpVal[10]=v[3]*val[9]+v[4]*val[10]+v[5]*val[11]+v[10];
		multTmpVal[11]=v[6]*val[9]+v[7]*val[10]+v[8]*val[11]+v[11];
		
		for (int i = 0; i < multTmpVal.length; i++) {
			this.v[i]=multTmpVal[i];
		}
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init() {
		
	}
}
