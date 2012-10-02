package shadow.geometry.curves.data;

import java.util.List;

import shadow.geometry.SFCurve;
import shadow.geometry.curves.SFBezier3;
import shadow.geometry.curves.SFCurves;
import shadow.geometry.curves.SFUniformCurvesSpline;
import shadow.geometry.data.SFFixedFloat16;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataObjectsList;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.SFWritableDataObject;
import shadow.system.data.java.SFStringTokenizerInputStream;
import shadow.system.data.java.SFStringWriterStream;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.SFPrimitiveType;

public class SFUniformBezier33fData extends SFDataAsset<SFCurve>{

	private class SFBezier3fDataObject extends SFBinaryDataList<SFFixedFloat16> implements SFWritableDataObject{
		public SFBezier3fDataObject(float... values) {
			super(new SFFixedFloat16());
			for (int i = 0; i < values.length; i++) {
				getDataObject().add(new SFFixedFloat16(values[i]));
			}
		}
		@Override
		public SFPrimitiveType clone() {
			return new SFBezier3fDataObject();
		}
		
		@Override
		public void setStringValue(String value) {
			SFStringTokenizerInputStream stream = new SFStringTokenizerInputStream(value);
			int size = stream.readInt();
			for (int i = 0; i < size; i++) {
				addCharSetObjects(stream.readString());
			}
		}
		
		@Override
		public String toStringValue() {
			SFStringWriterStream stream = new SFStringWriterStream();
			stream.writeInt(getSize());
			for (int i = 0; i < getSize(); i++) {
				stream.writeString(getCharSetObjectString(i));
			}
			return stream.getString();
		}
	}
	
	private SFDataObjectsList<SFBezier3fDataObject> data=new SFDataObjectsList<SFBezier3fDataObject>(new SFBezier3fDataObject());
	
	public SFUniformBezier33fData() {
		SFNamedParametersObject namesParameters=new SFNamedParametersObject();
		namesParameters.addObject("bezierData", data);
		setData(namesParameters);
	}
	
	@Override
	protected SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>> buildResource() {
		SFUniformCurvesSpline<SFVertex3f,SFBezier3<SFVertex3f>>  spline=new SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>>();
		
		for (int i = 0; i < data.size(); i++) {
			SFBezier3fDataObject curveData=data.get(i);
			List<SFFixedFloat16> data=curveData.getDataObject();
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
