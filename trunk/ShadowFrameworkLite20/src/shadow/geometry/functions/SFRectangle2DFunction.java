package shadow.geometry.functions;

import shadow.geometry.SFSurfaceFunction;

public class SFRectangle2DFunction extends SFSurfaceFunction{

	private float x,y,w,h;

	public SFRectangle2DFunction(float x, float y, float w, float h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	@Override
	public float getX(float u, float v) {
		return x+u*w;
	}
	
	@Override
	public float getY(float u, float v) {
		return y+v*h;
	}
	
	@Override
	public float getZ(float u, float v) {
		return 0;
	}
	
	@Override
	public void init() {
		//nothing to do
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}
	
	
}
