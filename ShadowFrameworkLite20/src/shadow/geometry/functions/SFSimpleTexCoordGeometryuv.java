package shadow.geometry.functions;

import shadow.math.SFVertex2f;

public class SFSimpleTexCoordGeometryuv extends SFUnoptimizedSurfaceFunctionUV{

	private float du,dv;
	
	public SFSimpleTexCoordGeometryuv(float du, float dv) {
		super();
		this.du = du;
		this.dv = dv;
	}

	@Override
	public SFVertex2f getTexCoord(float u, float v) {
		return new SFVertex2f(u*du, v*dv);
	}

	@Override
	public void init() {
		//Do nothing
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}
	

	protected float getDu() {
		return du;
	}

	protected void setDu(float du) {
		this.du = du;
	}

	protected float getDv() {
		return dv;
	}

	protected void setDv(float dv) {
		this.dv = dv;
	}
}
