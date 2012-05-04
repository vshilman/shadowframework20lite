package shadow.geometry.curves.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import shadow.geometry.SFCurve;
import shadow.geometry.curves.SFSpline;
import shadow.math.SFVertex2f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.SFCompositeDataArray;

/**
 * A Spline 2D defined upon a space of binary values
 * 
 * @author Alessandro Martinelli
 */
public class SFBinarySpline2D extends SFDataAsset<SFSpline<SFVertex2f>>{

	private class SFBinarySpline2DData extends SFCompositeDataArray{
		
		private SFBinaryDataList<SFPoint2DData> description;
		
		@Override
		public void generateData() {
			description=new SFBinaryDataList<SFPoint2DData>(new SFPoint2DData(0,0));
			addDataObject(description);
		}
		
		@Override
		public SFCompositeDataArray clone() {
			return new SFBinarySpline2DData();
		}
	} 
	
	private SFBinarySpline2DData data=new SFBinarySpline2DData();
	
	
	public SFBinarySpline2D() {
		super();
		setData(data);
	}
	
	public ArrayList<SFPoint2DData> getPoints(){
		return data.description.getDataObject();
	}

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFBinarySpline2D();
	}
	
	public void evaluate(){
		//TODO 
	}
	
	private class CurvesSpline implements SFSpline<SFVertex2f>{ 
		private LinkedList<SFCurve<SFVertex2f>> data=new LinkedList<SFCurve<SFVertex2f>>();
		
		public CurvesSpline() {
			super();
			
			//short buildingCurve = -1;
			
//			while (curve == null && index<points.size()){
//				
//				short operation = points.get(index).getWeight();
//				
//				switch (operation) {
//					case SFSplinePoint2D.OPERATION_LINETO:
//						points.get(index).getVertex(B);
//						curve = new SFLine<SFVertex2f>(A,B);
//					break;
//					case SFSplinePoint2D.OPERATION_QUADTO:
//						points.get(index).getVertex(B);
//						buildingCurve = SFSplinePoint2D.OPERATION_QUADTO;
//						break;
//					case SFSplinePoint2D.OPERATION_CUBICT0:
//						points.get(index).getVertex(B);
//						buildingCurve = SFSplinePoint2D.OPERATION_CUBICT0;
//						break;
//					case SFSplinePoint2D.OPERATION_HOLD:
//						if(buildingCurve == SFSplinePoint2D.OPERATION_QUADTO){
//							points.get(index).getVertex(C);
//							curve = new SFBezier2<SFVertex2f>(A, B, C);
//						}
//						if(buildingCurve == SFSplinePoint2D.OPERATION_CUBICT0){
//							points.get(index).getVertex(C);
//							operation = (short) 0x100;
//						}
//						if(buildingCurve == 0x100){
//							SFVertex2f D=new SFVertex2f(0,0);
//							points.get(index).getVertex(D);
//							curve = new SFBezier3<SFVertex2f>(A, B, C,D);
//						}
//						break;
//					case SFSplinePoint2D.OPERATION_MOVETO:
//							points.get(index).getVertex(A);
//							index++;
//						break;
//					default:
//						break;
//				}
//				
//			}
		}

		@Override
		public void init() {
			evaluate();
		}
		
		@Override
		public Iterator<SFCurve<SFVertex2f>> getCurves() {
			return data.iterator();
		}
		
	}
	
	
	
	@Override
	protected CurvesSpline buildResource() {
		return new CurvesSpline();
	}
	
}
