package shadow.geometry.curves.data;

import java.util.HashMap;

import shadow.geometry.SFCurve;
import shadow.geometry.curves.SFStandardAbstractCurve;
import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFString;

/**
 * @author Alessandro Martinelli
 */
public class SFCurveData2D extends SFCurveData<SFVertex2f>{

	private SFBinaryDataList<SFPoint2DData> points;
	private SFString curveName;

	private static HashMap<String, SFStandardAbstractCurve<SFVertex2f>> registedCurves=new HashMap<String, SFStandardAbstractCurve<SFVertex2f>>();
	
	public static void registerCurve(String name, SFStandardAbstractCurve<SFVertex2f> curve){
		registedCurves.put(name, curve);
	}
	
	public SFCurveData2D() {
		
	}
	
	public SFCurveData2D(String name,SFVertex2f... vertices) {
		super();
		curveName.setString(name);
		for (int i = 0; i < vertices.length; i++) {
			points.getDataObject().add(new SFPoint2DData(vertices[i]));
		}
	}

	@Override
	public void generateData() {
		points = new SFBinaryDataList<SFPoint2DData>(new SFPoint2DData(0, 0));
		curveName = new SFString("");
		
		addDataObject(points);
		addDataObject(curveName);
	}
	
	@Override
	public SFCompositeDataArray clone() {
		return new SFCurveData2D();
	}
	
	@Override
	public SFCurve<SFVertex2f> getCurve() {
		SFStandardAbstractCurve<SFVertex2f> curve = registedCurves.get(curveName.getString());
		SFValuenf[] vertices = curve.getVertices();
		for (int i = 0; i < vertices.length; i++) {
			points.getDataObject().get(i).getVertex((SFVertex2f)vertices[i]);
		}
		return curve;
	}
}
