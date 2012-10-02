package shadow.geometry.functions;

import shadow.geometry.SFCurve;
import shadow.geometry.curves.SFControlPointsCurve;
import shadow.geometry.curves.SFStandardAbstractCurve;
import shadow.math.SFValuenf;


public class SFTensorProductSurface extends SFUnoptimizedSurfaceFunction{

	private SFCurve[] guideLines;
	private SFControlPointsCurve productCurve;
	
	private SFValuenf lastVertex;
	
	public SFTensorProductSurface(){
		
	}
	
	public void setGuideLines(SFCurve[] guideLines) {
		this.guideLines = guideLines;
	}

	public void setProductCurve(SFControlPointsCurve productCurve) {
		this.productCurve = productCurve;
		lastVertex=productCurve.generateValue();
	}
	
	private void evaluateLastVertex(float u,float v) {
		for (int i = 0; i < guideLines.length; i++) {
			SFValuenf vertex=productCurve.getControlPoint(i);
			guideLines[i].getVertex(u, vertex);
		}
		productCurve.getVertex(v, lastVertex);
	}

	@Override
	public float getX(float u, float v) {
		evaluateLastVertex(u,v);
		return lastVertex.get()[0];
	}
	
	@Override
	public float getY(float u, float v) {
		return lastVertex.get()[1];
	}
	
	@Override
	public float getZ(float u, float v) {
		return lastVertex.get()[2];
	}
	
	@Override
	public void destroy() {
		//nothing to do
	}
	
	@Override
	public void init() {
		//nothing to do
	}
}
