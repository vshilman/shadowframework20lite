package shadow.geometry.curves;

import shadow.math.SFValuenf;

public class SFLine<T extends SFValuenf> extends SFStandardAbstractCurve<T>{

	public SFLine(SFValuenf a, SFValuenf b) {
		super(2);
		vertices[0] = a;
		vertices[1] = b;
	}
	
	public SFLine(int size) {
		super(2);
		vertices[0] = new SFValuenf(size);
		vertices[1] = new SFValuenf(size);
	}

	@Override
	public void getDev2Dt(float t, SFValuenf read) {
		float[] data = read.get();
		for (int i = 0; i < data.length; i++) {
			data[i]=0;
		}
	}
	
	@Override
	public void getDevDt(float t, SFValuenf read) {
		read.set(vertices[1]);
		read.addMult(-1,vertices[0]);
	}
	
	@Override
	public void getVertex(float t, SFValuenf read) {
		read.set(vertices[0]);
		read.mult(1-t);
		read.addMult(t,vertices[1]);
	}

	@Override
	public String toString() {
		return "LINE : "+vertices[0]+" "+vertices[1];
	}
	
	@Override
	protected SFLine<T> clone() {
		return new SFLine<T>(vertices[0], vertices[1]);
	}
}
