package shadow.geometry.curves;

import shadow.math.SFValuenf;

public abstract class SFStandardAbstractCurve<T extends SFValuenf> extends SFUnOptimizedCurve<T> implements SFControlPointsCurve{

	protected SFValuenf vertices[];
	
	protected SFStandardAbstractCurve(int n) {
		super();
		vertices = new SFValuenf[n];
	}
	
	public int getControlPointSize(){
		return vertices.length;
	}

	public void setControlPoint(SFValuenf value,int index) {
		vertices[index]=value;
	}
	
	public SFValuenf getControlPoint(int index) {
		return vertices[index];
	}
	
	@Override
	public SFValuenf generateValue() {
		return new SFValuenf(vertices[0].get().length);
	}

	@Override
	public float getTMin() {
		return 0;
	}

	@Override
	public float getTMax() {
		return 1;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}

}