package shadow.geometry.curves;

import shadow.math.SFValuenf;

public class SFBezier2<T extends SFValuenf> extends SFStandardAbstractCurve<T>{

	public SFBezier2(SFValuenf a, SFValuenf b, SFValuenf c) {
		super(3);
		vertices[0] = a;
		vertices[1] = b;
		vertices[2] = c;
	}
	
	@Override
	public void getDev2Dt(float t, SFValuenf read) {
		read.set(vertices[0]);
		read.mult(2);
		read.addMult(-4,vertices[1]);
		read.addMult(2,vertices[2]);
	}
	
	
	
	@Override
	public void getDevDt(float t, SFValuenf read) {
		read.set(vertices[0]);
		read.mult(-2*(1-t));
		read.addMult(2-4*t,vertices[1]);
		read.addMult(2*t,vertices[2]);
	}
	
	public void getVertex(float t, SFValuenf read) {
		float tm = 1-t;
		read.set(vertices[0]);
		read.mult(tm*tm);
		read.addMult(2*t*tm,vertices[1]);
		read.addMult(t*t,vertices[2]);
	}

	@Override
	public String toString() {
		return "B2 : "+vertices[0]+" "+vertices[1]+" "+vertices[2];
	}
	
	@Override
	protected SFBezier2<T> clone() {
		return new SFBezier2<T>(vertices[0], vertices[1], vertices[2]);
	}
}
