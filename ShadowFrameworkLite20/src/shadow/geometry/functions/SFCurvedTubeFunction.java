package shadow.geometry.functions;

import shadow.geometry.SFCurve;
import shadow.math.SFVertex3f;

public class SFCurvedTubeFunction extends SFUnoptimizedSurfaceFunction {
	
	private SFCurve centralCurve;
	private SFCurve rayCurve;
	
	private SFVertex3f Ccv=new SFVertex3f();
	private SFVertex3f Vec1v=new SFVertex3f();
	private SFVertex3f Vec2v=new SFVertex3f();
	private SFVertex3f DVec1v=new SFVertex3f();
	private SFVertex3f DVec2v=new SFVertex3f();
	private SFVertex3f dCcdv=new SFVertex3f();
	
	public SFCurvedTubeFunction() {
		super();
	}
	
	public SFCurvedTubeFunction(SFCurve centralCurve,
			SFCurve rayCurve) {
		super();
		this.centralCurve = centralCurve;
		this.rayCurve = rayCurve;
	}
	
	public SFVertex3f getDu() {
		SFVertex3f du=new SFVertex3f(getDXDu(),getDYDu(),getDZDu());
		du.normalize3f();
		return du;
	}

	public SFVertex3f getDv() {
		SFVertex3f dv=new SFVertex3f(getDXDv(),getDYDv(),getDZDv());
		dv.normalize3f();
		return dv;
	}

	public SFVertex3f getPosition() {
		return new SFVertex3f(getX(),getY(),getZ());
	}

	
	@Override
	public void init() {
		//Do nothing
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}

	private float lastV=-1;
	private float cos=0;
	private float sin=0;
	
	
	private void evalAll(float v){
		if(v!=lastV){
			centralCurve.getVertex(v, Ccv);
			centralCurve.getDevDt(v, dCcdv);
			rayCurve.getVertex(v, Vec1v);
			Vec1v.subtract3f(Ccv);
			Vec2v.set3f(dCcdv.cross(Vec1v));
			Vec2v.mult((float)(Math.sqrt(Vec1v.dot3f(Vec1v)/Vec2v.dot3f(Vec2v))));

			SFVertex3f dCcdv2=new SFVertex3f();
			centralCurve.getDev2Dt(v, dCcdv2);
			rayCurve.getDevDt(v, DVec1v);
			DVec1v.subtract3f(dCcdv);
			//DVec1v.normalize3f();
			DVec2v.set3f(dCcdv.cross(DVec1v));
			DVec2v.add3f(dCcdv2.cross(Vec1v));
			//DVec2v.normalize3f();
			
			lastV=v;
		}
	}
	
	public float getX(float u,float v) {
		evalAll(v);
		cos=(float)(Math.cos(2*Math.PI*u));
		sin=(float)(Math.sin(2*Math.PI*u));
		return Ccv.getX()+cos*Vec1v.getX()+sin*Vec2v.getX();
	}
	
	public float getY(float u,float v) {
		return Ccv.getY()+cos*Vec1v.getY()+sin*Vec2v.getY();
	}
	
	public float getZ(float u,float v) {
		return Ccv.getZ()+cos*Vec1v.getZ()+sin*Vec2v.getZ();
	}
	
	
	public float getX() {
		return Ccv.getX()+cos*Vec1v.getX()+sin*Vec2v.getX();
	}
	
	public float getY() {
		return Ccv.getY()+cos*Vec1v.getY()+sin*Vec2v.getY();
	}
	
	public float getZ() {
		return Ccv.getZ()+cos*Vec1v.getZ()+sin*Vec2v.getZ();
	}
	
	public float getDXDv() {
		return dCcdv.getX()+cos*DVec1v.getX()+sin*DVec2v.getX();
	}
	
	public float getDYDv() {
		return dCcdv.getY()+cos*DVec1v.getY()+sin*DVec2v.getY();
	}
	
	public float getDZDv() {
		return dCcdv.getZ()+cos*DVec1v.getZ()+sin*DVec2v.getZ();
	}
	
	public float getDXDu() {
		return -sin*Vec1v.getX()+cos*Vec2v.getX();
	}
	
	public float getDYDu() {
		return -sin*Vec1v.getY()+cos*Vec2v.getY();
	}
	
	public float getDZDu() {
		return -sin*Vec1v.getZ()+cos*Vec2v.getZ();
	}

	public SFCurve getCentralCurve() {
		return centralCurve;
	}

	public void setCentralCurve(SFCurve centralCurve) {
		this.centralCurve = centralCurve;
	}

	public SFCurve getRayCurve() {
		return rayCurve;
	}

	public void setRayCurve(SFCurve rayCurve) {
		this.rayCurve = rayCurve;
	}

	

}