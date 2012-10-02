package shadow.geometry.curves;

import shadow.geometry.SFCurve;
import shadow.math.SFTransform3f;
import shadow.math.SFValuenf;

public class SFTransformedCurve implements SFCurve{

	private SFCurve curve;
	private SFTransform3f trasnform;
	
	public SFTransformedCurve() {
		super();
	}

	public SFTransformedCurve(SFCurve curve, SFTransform3f trasnform) {
		super();
		this.curve = curve;
		this.trasnform = trasnform;
	}



	public void setCurve(SFCurve curve) {
		this.curve = curve;
	}



	public void setTrasnform(SFTransform3f trasnform) {
		this.trasnform = trasnform;
	}



	@Override
	public void destroy() {
		
	}  
	
	@Override
	public SFValuenf generateValue() {
		return curve.generateValue();
	}
	
	@Override
	public void get(float[] ts, SFValuenf[] vertices, SFValuenf[] derivatives) {
		curve.get(ts, vertices, derivatives);
		transfom(vertices, derivatives);
	}

	public void transfom(SFValuenf[] vertices, SFValuenf[] derivatives) {
		for (int i = 0; i < derivatives.length; i++) {
			trasnform.transformDir(derivatives[i]);
		}
		for (int i = 0; i < vertices.length; i++) {
			trasnform.transform(vertices[i]);
		}
	}
	
	@Override
	public void get(float[] ts, SFValuenf[] vertices, SFValuenf[] derivatives,
			SFValuenf[] derivatives2) {
		curve.get(ts, vertices, derivatives,derivatives2);
		for (int i = 0; i < derivatives2.length; i++) {
			trasnform.transformDir(derivatives2[i]);
		}
		transfom(vertices, derivatives);
	}
	
	
	@Override
	public void getDev2Dt(float ts, SFValuenf read) {
		curve.getDev2Dt(ts,read);
		trasnform.transformDir(read);
	}
	
	@Override
	public void getDev2Dt(float[] ts, SFValuenf[] read) {
		curve.getDev2Dt(ts, read);
		for (int i = 0; i < read.length; i++) {
			trasnform.transformDir(read[i]);
		}
	}
	
	@Override
	public void getDevDt(float t, SFValuenf read) {
		curve.getDevDt(t, read);
		trasnform.transformDir(read);
	}
	
	@Override
	public void getDevDt(float[] ts, SFValuenf[] read) {
		curve.getDevDt(ts, read);
		for (int i = 0; i < read.length; i++) {
			trasnform.transformDir(read[i]);
		}
	}
	
	@Override
	public float getTMax() {
		return curve.getTMax();
	}
	
	@Override
	public float getTMin() {
		return curve.getTMin();
	}
	
	@Override
	public void getVertex(float t, SFValuenf read) {
		curve.getVertex(t, read);
		trasnform.transform(read);
	}
	
	@Override
	public void getVertices(float[] ts, SFValuenf[] read) {
		curve.getVertices(ts, read);
		for (int i = 0; i < read.length; i++) {
			trasnform.transform(read[i]);
		}
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
