package shadow.geometry.curves;

import java.util.ArrayList;

import shadow.geometry.SFCurve;
import shadow.math.SFValuenf;

public class SFUniformCurvesSpline<T extends SFValuenf,G extends SFCurve> extends SFUnOptimizedCurve<T> implements SFControlPointsCurve{

	private ArrayList<G> curves=new ArrayList<G>();
	
	private ArrayList<SFValuenf> controlPoints=new ArrayList<SFValuenf>();
	
	public void addCurve(G curve){
		curves.add(curve);
	}
	
	@Override
	public int getControlPointSize() {
		return controlPoints.size();
	}
	
	@Override
	public SFValuenf getControlPoint(int index) {
		return controlPoints.get(index);
	}
	
	public void addControlPoint(SFValuenf vertex){
		controlPoints.add(vertex);
	}
	
	@Override
	public void getDev2Dt(float T, SFValuenf read) {
		float t=T*curves.size();
		int index=(int)t;
		if(index==curves.size())
			index=curves.size()-1;
		curves.get(index).getDev2Dt(t-index,read);
	}
	
	public void getDevDt(float T, SFValuenf read) {
		float t=T*curves.size();
		int index=(int)t;
		if(index==curves.size())
			index=curves.size()-1;
		curves.get(index).getDevDt(t-index,read);
	}
	
	@Override
	public SFValuenf generateValue() {
		return curves.get(0).generateValue();
	}
	
	@Override
	public float getTMax() {
		return 1;
	}
	
	@Override
	public float getTMin() {
		return 0;
	}
	
	@Override
	public void getVertex(float T, SFValuenf read) {
		float t=T*curves.size();
		int index=(int)t;
		if(index>=curves.size())
			index=curves.size()-1;
		curves.get(index).getVertex(t-index,read);
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
