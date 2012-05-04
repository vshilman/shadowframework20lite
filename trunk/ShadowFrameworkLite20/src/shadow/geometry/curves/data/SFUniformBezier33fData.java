package shadow.geometry.curves.data;

import java.util.List;

import shadow.geometry.SFCurve;
import shadow.geometry.curves.SFBezier3;
import shadow.geometry.curves.SFCurves;
import shadow.geometry.curves.SFUniformCurvesSpline;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFPrimitiveType;

public class SFUniformBezier33fData extends SFDataAsset<SFCurve<SFVertex3f>>{

	private class SFBezier3fDataObject extends SFBinaryDataList<SFFixedFloat>{
		public SFBezier3fDataObject(float... values) {
			super(new SFFixedFloat());
			for (int i = 0; i < values.length; i++) {
				getDataObject().add(new SFFixedFloat(values[i]));
			}
		}
		@Override
		public SFPrimitiveType clone() {
			return new SFBezier3fDataObject();
		}
	}
	
	private SFDataList<SFBezier3fDataObject> data=new SFDataList<SFBezier3fDataObject>(new SFBezier3fDataObject());
	
	public SFUniformBezier33fData() {
		setData(data);
	}
	
	@Override
	protected SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>> buildResource() {
		SFUniformCurvesSpline<SFVertex3f,SFBezier3<SFVertex3f>>  spline=new SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>>();
		
		for (int i = 0; i < data.size(); i++) {
			SFBezier3fDataObject curveData=data.get(i);
			List<SFFixedFloat> data=curveData.getDataObject();
			if(data.size()==6){
				SFBezier3<SFVertex3f> curve=SFCurves.generateBezier33f(data.get(0).getFloat(), data.get(1).getFloat(), data.get(2).getFloat(), 
						data.get(3).getFloat(), data.get(4).getFloat(), data.get(5).getFloat());
				spline.addCurve(curve);
			}
			if(data.size()==9){
				SFBezier3<SFVertex3f> curve=SFCurves.generateBezier33f(data.get(0).getFloat(), data.get(1).getFloat(), data.get(2).getFloat(), 
						data.get(3).getFloat(), data.get(4).getFloat(), data.get(5).getFloat(), 
						data.get(6).getFloat(), data.get(7).getFloat(), data.get(8).getFloat());
				spline.addCurve(curve);
			}
			
		}

		return spline;
	}
	
	
	public void addBezier33f(float... values) {
		data.add(new SFBezier3fDataObject(values));
	}
}
