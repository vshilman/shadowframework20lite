package shadow.geometry.geometries;

import shadow.geometry.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.math.SFVertex2f;

public class SFSimpleTexCoordGeometryuv implements SFSurfaceGeometryTexCoordFunctionuv{

	private float du,dv;
	
	public SFSimpleTexCoordGeometryuv(float du, float dv) {
		super();
		this.du = du;
		this.dv = dv;
	}

	@Override
	public SFVertex2f getTexCoord(float u, float v, float x, float y, float z) {
		return new SFVertex2f(u*du, v*dv);
	}
	
	@Override
	public void init() {
		//Do nothing
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
