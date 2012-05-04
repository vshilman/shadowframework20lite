package shadow.geometry.geometries;

import shadow.geometry.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.math.SFVertex2f;

public class SFSimpleObjPlaneTexCoordGeometry implements SFSurfaceGeometryTexCoordFunctionuv{

	private float au,bu,cu;
	private float av,bv,cv;
	
	public SFSimpleObjPlaneTexCoordGeometry(float au, float bu, float cu,
			float av, float bv, float cv) {
		super();
		this.au = au;
		this.bu = bu;
		this.cu = cu;
		this.av = av;
		this.bv = bv;
		this.cv = cv;
	}

	@Override
	public SFVertex2f getTexCoord(float u, float v, float x, float y, float z) {
		return new SFVertex2f(au * x + bu * y + cu, av * x + bv * y + cv);
	}
	
	@Override
	public void init() {
		//Do nothing
	}

	protected float getAu() {
		return au;
	}

	protected void setAu(float au) {
		this.au = au;
	}

	protected float getBu() {
		return bu;
	}

	protected void setBu(float bu) {
		this.bu = bu;
	}

	protected float getCu() {
		return cu;
	}

	protected void setCu(float cu) {
		this.cu = cu;
	}

	protected float getAv() {
		return av;
	}

	protected void setAv(float av) {
		this.av = av;
	}

	protected float getBv() {
		return bv;
	}

	protected void setBv(float bv) {
		this.bv = bv;
	}

	protected float getCv() {
		return cv;
	}

	protected void setCv(float cv) {
		this.cv = cv;
	}

	
}
