package shadow.geometry.functions;

import shadow.geometry.geometries.SFDerivedTexCoordFunctionuv;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;

public class SFNormalBasedObjPlaneTexCoordGeometry implements SFDerivedTexCoordFunctionuv{

	private float a,da;
	private float av,bv,cv,dv;
	

	public SFNormalBasedObjPlaneTexCoordGeometry(float a, float da, float av, float bv, float cv,
			float dv) {
		super();
		this.a = a;
		this.da = da;
		this.av = av;
		this.bv = bv;
		this.cv = cv;
		this.dv = dv;
	}

	@Override
	public SFVertex2f getTexCoord(float x, float y, float z, float nx, float ny, float nz) {
		SFVertex3f dirV=new SFVertex3f(av,bv,cv);
		SFVertex3f normal=new SFVertex3f(nx,ny,nz);
		SFVertex3f dirU=normal.cross(dirV);
		dirU.normalize3f();
		return new SFVertex2f(a*(dirU.getX() * x + dirU.getY() * y + dirU.getZ()*z)+da, av * x + bv * y + cv * z + dv);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init() {
		//Do nothing
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

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public float getDa() {
		return da;
	}

	public void setDa(float da) {
		this.da = da;
	}

	public float getDv() {
		return dv;
	}

	public void setDv(float dv) {
		this.dv = dv;
	}

	
	
}
