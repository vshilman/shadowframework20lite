package shadow.geometry.functions;

import shadow.geometry.geometries.SFDerivedTexCoordFunctionuv;
import shadow.math.SFVertex2f;

public class SFSimpleObjPlaneTexCoordGeometry implements SFDerivedTexCoordFunctionuv{

	private float au,bu,cu,du;
	private float av,bv,cv,dv;
	
	
	public SFSimpleObjPlaneTexCoordGeometry(float au, float bu, float cu, float du, float av,
			float bv, float cv, float dv) {
		super();
		this.au = au;
		this.bu = bu;
		this.cu = cu;
		this.du = du;
		this.av = av;
		this.bv = bv;
		this.cv = cv;
		this.dv = dv;
	}

	@Override
	public SFVertex2f getTexCoord(float x, float y, float z, float nx, float ny, float nz) {
		return new SFVertex2f(au * x + bu * y + cu * z + du, av * x + bv * y + cv * z + dv);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
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

	public float getDu() {
		return du;
	}

	public void setDu(float du) {
		this.du = du;
	}

	public float getDv() {
		return dv;
	}

	public void setDv(float dv) {
		this.dv = dv;
	}
	
	
}
