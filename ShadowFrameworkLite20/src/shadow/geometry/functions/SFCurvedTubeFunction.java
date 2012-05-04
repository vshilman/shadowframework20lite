package shadow.geometry.functions;

import shadow.geometry.SFCurve;
import shadow.geometry.SFSurfaceFunction;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;

public class SFCurvedTubeFunction extends SFSurfaceFunction {
	
	private SFCurve<SFValuenf> centralCurve;
	private SFCurve<SFValuenf> rayCurve;
	
	private SFVertex3f Ccv=new SFVertex3f();
	private SFVertex3f Vec1v=new SFVertex3f();
	private SFVertex3f Vec2v=new SFVertex3f();
	private SFVertex3f dCcdv=new SFVertex3f();
	private SFVertex3f Rcv=new SFVertex3f();
	
	public SFCurvedTubeFunction() {
		super();
	}
	
	public SFCurvedTubeFunction(SFCurve<SFValuenf> centralCurve,
			SFCurve<SFValuenf> rayCurve) {
		super();
		this.centralCurve = centralCurve;
		this.rayCurve = rayCurve;
	}

	@Override
	public void init() {
	}

	private float lastV=-1;
	private float cos=0;
	private float sin=0;
	
	
	private void evalAll(float v){
		if(v!=lastV){
			centralCurve.getVertex(v, Ccv);
			centralCurve.getDevDt(v, dCcdv);
			rayCurve.getVertex(v, Rcv);
			Vec1v.set3f(Rcv);
			Vec1v.subtract3f(Ccv);
			
			Vec2v.set3f(dCcdv.cross(Vec1v));
			Vec2v.normalize3f();
			Vec2v.mult((float)(Math.sqrt(Vec1v.dot3f(Vec1v))));
						
			lastV=v;
		}
	}
	
	
	@Override
	public float getX(float u, float v) {
		evalAll(v);
		cos=(float)(Math.cos(2*Math.PI*u));
		sin=(float)(Math.sin(2*Math.PI*u));
		return Ccv.getX()+cos*Vec1v.getX()+sin*Vec2v.getX();
	}
	
	@Override
	public float getY(float u, float v) {
		return Ccv.getY()+cos*Vec1v.getY()+sin*Vec2v.getY();
	}
	
	@Override
	public float getZ(float u, float v) {
		return Ccv.getZ()+cos*Vec1v.getZ()+sin*Vec2v.getZ();
	}

	public SFCurve<SFValuenf> getCentralCurve() {
		return centralCurve;
	}

	public void setCentralCurve(SFCurve<SFValuenf> centralCurve) {
		this.centralCurve = centralCurve;
	}

	public SFCurve<SFValuenf> getRayCurve() {
		return rayCurve;
	}

	public void setRayCurve(SFCurve<SFValuenf> rayCurve) {
		this.rayCurve = rayCurve;
	}

	
	
}