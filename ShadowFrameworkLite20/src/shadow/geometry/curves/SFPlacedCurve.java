package shadow.geometry.curves;

import shadow.geometry.SFCurve;
import shadow.math.SFValuenf;

public class SFPlacedCurve<T extends SFValuenf> extends SFUnOptimizedCurve<T> implements SFCurve{

	private float tMin;
	private float tMax;
	private SFCurve curve;
	
	
	public SFPlacedCurve(float tMin, float tMax, SFCurve curve) {
		super();
		this.tMin = tMin;
		this.tMax = tMax;
		this.curve = curve;
	}
	
	protected float gettMin() {
		return tMin;
	}
	
	@Override
	public SFValuenf generateValue() {
		return curve.generateValue();
	}

	protected void settMin(float tMin) {
		this.tMin = tMin;
	}

	protected float gettMax() {
		return tMax;
	}

	protected void settMax(float tMax) {
		this.tMax = tMax;
	}

	protected SFCurve getCurve() {
		return curve;
	}

	protected void setCurve(SFCurve curve) {
		this.curve = curve;
	}

	@Override
	public void getDev2Dt(float t, SFValuenf read) {
		curve.getDev2Dt(t*(tMax-tMin)+tMin, read);
	}
	
	@Override
	public void getDevDt(float t, SFValuenf read) {
		curve.getDevDt(t*(tMax-tMin)+tMin, read);
	}
	
	@Override
	public void getVertex(float t, SFValuenf read) {
		curve.getVertex(t*(tMax-tMin)+tMin, read);
	}
	
	public float getTMin() {
		return curve.getTMin()*(tMax-tMin)+tMin;
	}
	
	public float getTMax() {
		return curve.getTMax()*(tMax-tMin)+tMin;
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
