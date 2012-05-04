package shadow.geometry.curves;

import shadow.geometry.SFCurve;
import shadow.math.SFValuenf;

public abstract class SFStandardAbstractCurve<T extends SFValuenf>  implements SFCurve<T>{

	protected SFValuenf vertices[];
	
	protected SFStandardAbstractCurve(int n) {
		super();
		vertices = new SFValuenf[n];
	}

	public SFValuenf[] getVertices() {
		return vertices;
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

}