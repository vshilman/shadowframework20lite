package shadow.geometry.functions;


public class SFRectangle2DFunction  extends SFUnoptimizedSurfaceFunction{

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
		//Do nothing
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
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
