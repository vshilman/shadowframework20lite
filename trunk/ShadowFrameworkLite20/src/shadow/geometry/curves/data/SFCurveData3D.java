package shadow.geometry.curves.data;

import java.util.HashMap;

import shadow.geometry.SFCurve;
import shadow.geometry.curves.SFStandardAbstractCurve;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFString;

//NOTE: this is still not good, there is duplicated code with SFCurveData2D
public class SFCurveData3D extends SFCurveData<SFVertex3f>{

	private SFBinaryDataList<SFPoint3DData> points;
	private SFString curveName;

	private static HashMap<String, SFStandardAbstractCurve<SFVertex3f>> registedCurves=new HashMap<String, SFStandardAbstractCurve<SFVertex3f>>();
	
	public static void registerCurve(String name, SFStandardAbstractCurve<SFVertex3f> curve){
		registedCurves.put(name, curve);
	}
	
	
	public SFCurveData3D() {
		
	}
	
	public SFCurveData3D(String name,SFVertex3f... vertices) {
		super();
		curveName.setString(name);
		for (int i = 0; i < vertices.length; i++) {
			points.getDataObject().add(new SFPoint3DData(vertices[i]));
		}
	}

	@Override
	public void generateData() {
		points = new SFBinaryDataList<SFPoint3DData>(new SFPoint3DData(0, 0, 0));
		curveName = new SFString("");
		
		addDataObject(points);
		addDataObject(curveName);
	}
	
	@Override
	public SFCompositeDataArray clone() {
		return new SFCurveData3D();
	}
	
	@Override
	public SFCurve<SFVertex3f> getCurve() {
		SFStandardAbstractCurve<SFVertex3f> curve = registedCurves.get(curveName.getString());
		SFValuenf[] vertices = curve.getVertices();
		for (int i = 0; i < vertices.length; i++) {
			points.getDataObject().get(i).getVertex((SFVertex3f)vertices[i]);
		}
		return curve;
	}
}
