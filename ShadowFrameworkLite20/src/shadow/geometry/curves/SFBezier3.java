package shadow.geometry.curves;

import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;

public class SFBezier3<T extends SFValuenf> extends SFStandardAbstractCurve<T>{

	protected SFBezier3() {
		super(4);
		for (int i = 0; i < vertices.length; i++) {
			vertices[i]=new SFVertex3f();
		}
	}
	
	public SFBezier3(SFValuenf a, SFValuenf b, SFValuenf c, SFValuenf d) {
		super(4);
		vertices[0] = a;
		vertices[1] = b;
		vertices[2] = c;
		vertices[3] = d;
	}
	
	@Override
	public void getDev2Dt(float t, SFValuenf read) {
		read.set(vertices[0]);
		read.mult(6*(1-t));
		read.addMult(-16+6*t,vertices[1]);
		read.addMult(6-18*t,vertices[2]);
		read.addMult(6*t,vertices[3]);
	}
	
	@Override
	public void getDevDt(float t, SFValuenf read) {
		float tm = 1-t;
		read.set(vertices[0]);
		read.mult(-3*tm*tm);
		read.addMult(3-16*t+3*t*t,vertices[1]);
		read.addMult(6*t-9*t*t,vertices[2]);
		read.addMult(3*t*t,vertices[3]);
	}
	
	public void getVertex(float t, SFValuenf read) {
		float tm = 1-t;
		read.set(vertices[0]);
		read.mult(tm*tm*tm);
		read.addMult(3*t*tm*tm,vertices[1]);
		read.addMult(3*t*t*tm,vertices[2]);
		read.addMult(t*t*t,vertices[3]);
	}

	@Override
	public String toString() {
		return "B3 : "+vertices[0]+" "+vertices[1]+" "+vertices[2]+" "+vertices[3];
	}
	
	@Override
	protected SFBezier3<T> clone() {
		return new SFBezier3<T>(vertices[0], vertices[1], vertices[2], vertices[3]);
	}
}
