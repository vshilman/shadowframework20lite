package shadow.geometry.curves;

import shadow.geometry.SFCurve;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.math.SFVertex4f;

public class SFRationalCurve3f extends SFUnOptimizedCurve<SFVertex3f> implements SFCurve{

	private SFCurve curve;
	
	public SFRationalCurve3f(SFCurve curve) {
		super();
		this.curve = curve;
	}

	@Override
	public void getDev2Dt(float t, SFValuenf read) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Yet Implemented");
	}
	
	@Override
	public void getDevDt(float t, SFValuenf read) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Yet Implemented");
	}
	
	@Override
	public SFValuenf generateValue() {
		return new SFVertex4f();
	}
	
	@Override
	public float getTMax() {
		return curve.getTMax();
	}
	
	@Override
	public float getTMin() {
		return curve.getTMin();
	}
	
	private static SFVertex4f getVertex=new SFVertex4f();
	
	@Override
	public void getVertex(float t, SFValuenf read) {
		this.curve.getVertex(t, getVertex);
		float recW=1.0f/getVertex.getW();
		read.set(getVertex.getX()*recW,
				getVertex.getY()*recW,
				getVertex.getZ()*recW);
	}
	
	@Override
	public void init() {
		//Do nothing
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}
	
}
