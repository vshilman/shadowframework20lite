package util;

import shadow.math.SFVertex3f;

public class Check {

	private SFVertex3f vertex;
	private float ao;

	public Check() {
		this.vertex = new SFVertex3f(0, 0, 0);
		this.ao = 0;
	}

	public SFVertex3f getVertex() {
		return this.vertex;
	}

	public float getAO() {
		return this.ao;
	}

	public void setVertex(SFVertex3f v) {
		this.vertex = v;
	}

	public void setAO(float f) {
		this.ao = f;
	}

}
