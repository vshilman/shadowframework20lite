package shadow.geometry.curves.data;

import java.util.ArrayList;
import java.util.Iterator;

import shadow.geometry.SFCurve;
import shadow.geometry.curves.SFSpline;
import shadow.math.SFValuenf;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFDataList;

public class SFSplineData<T extends SFValuenf> extends SFDataAsset<SFSpline<T>> {

	private SFDataList<SFCurveData<T>> curves;
	private SFCurveData<T> type;
	
	public SFSplineData(SFCurveData<T> type) {
		curves = new SFDataList<SFCurveData<T>>(type);
		this.type = type;
		setData(curves);
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFSplineData<T>(type);
	}
	
	@Override
	public String getType() {
		return super.getType()+":"+type.toString();
	}
	
	public ArrayList<SFCurveData<T>> getCurvesList(){
		return curves.getDataObject();
	}
	
	private class ConcreteSFSpline implements SFSpline<T>{
		private ArrayList<SFCurve<T>> spline=new ArrayList<SFCurve<T>>();
		
		public ConcreteSFSpline() {
			super();
			for (SFCurveData<T> curveData : curves) {
				spline.add(curveData.getCurve());
			}
		}

		@Override
		public void init() {
			
		}

		@Override
		public Iterator<SFCurve<T>> getCurves() {
			return spline.iterator();
		}
	}
	
	@Override
	protected ConcreteSFSpline buildResource() {
		return new ConcreteSFSpline();
	}
	
	
}
