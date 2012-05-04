package shadow.geometry.functions;

import shadow.geometry.SFCurve;
import shadow.geometry.SFSurfaceFunction;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;

/**
 * @author Alessandro
 */
public class SFRadialSurfaceFunction  extends SFSurfaceFunction {

	private SFCurve<SFValuenf> borderCurve;
	private SFCurve<SFValuenf> rayCurve;
	private SFVertex3f r0=new SFVertex3f();
	private SFVertex3f r1=new SFVertex3f();
	private SFVertex3f pv=new SFVertex3f();
	private float A,B;
	
	public SFRadialSurfaceFunction() {
		
	}
	
	public SFRadialSurfaceFunction(SFCurve<SFValuenf> borderCurve,
			SFCurve<SFValuenf> rayCurve) {
		super();
		setBorderCurve(borderCurve);
		setRayCurve(rayCurve);
	}

	public void setBorderCurve(SFCurve<SFValuenf> borderCurve) {
		this.borderCurve = borderCurve;
	}

	public void setRayCurve(SFCurve<SFValuenf> rayCurve) {
		this.rayCurve = rayCurve;
		rayCurve.getVertex(0, r0);
		rayCurve.getVertex(1, r1);
		r1.subtract(r0);
	}
	
	@Override
	public SFVertex3f getDv(float u, float v) {
		SFVertex3f p=super.getDv(u, v);
		//p.mult(-1);
		return p;
	}
	
	@Override
	public SFVertex3f getDu(float u, float v) {
		SFVertex3f p=new SFVertex3f();
		borderCurve.getDevDt(u, p);
		p.mult(-1);
		//float dot=r1.dot3f(r1);
		//A=(r1.getX()*p.getX()-r1.getY()*p.getY())/dot;
		//B=(+r1.getY()*p.getX()+r1.getX()*p.getY())/dot;
		return p;
	}
	
	@Override
	public void init() {
			
	}
	
	private void evaluateAB(float u){
		SFVertex3f p=new SFVertex3f();
		borderCurve.getVertex(/*u*borderCurve.getTMax()+borderCurve.getTMin()*(1-u)*/u, p);
		p.subtract(r0);
		float dot=r1.dot3f(r1);
		A=(r1.getX()*p.getX()-r1.getY()*p.getY())/dot;
		B=(+r1.getY()*p.getX()+r1.getX()*p.getY())/dot;
	}

	@Override
	public float getX(float u, float v) {
		evaluateAB(u);
		rayCurve.getVertex(/*u*rayCurve.getTMax()+rayCurve.getTMin()*(1-u)*/v, pv);
		pv.subtract(r0);
		return A*pv.getX()-B*pv.getY()+r0.getX();
	}
	
	@Override
	public float getY(float u, float v) {
		return B*pv.getX()+A*pv.getY()+r0.getY();
	}
	
	@Override
	public float getZ(float u, float v) {
		return pv.getZ();
	}
}
