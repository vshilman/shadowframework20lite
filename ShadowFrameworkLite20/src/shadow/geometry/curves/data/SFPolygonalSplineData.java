package shadow.geometry.curves.data;

import shadow.geometry.SFCurve;
import shadow.geometry.SFValuesList;
import shadow.geometry.curves.SFLine;
import shadow.geometry.curves.SFUniformCurvesSpline;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFNamedParametersObject;

public class SFPolygonalSplineData extends SFDataAsset<SFCurve>{

	private SFLibraryReference<SFValuesList<SFValuenf>> vertices=new SFLibraryReference<SFValuesList<SFValuenf>>();
	
	public SFPolygonalSplineData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}
	
	public void setVertices(SFDataAsset<SFValuesList<SFValuenf>> vertices){
		this.vertices.setDataset(vertices);
	}
	
	@Override
	protected SFCurve buildResource() {
		
		SFUniformCurvesSpline<SFVertex3f, SFLine<SFVertex3f>> spline1 = new SFUniformCurvesSpline<SFVertex3f, SFLine<SFVertex3f>>();

		SFValuesList<SFValuenf> vertices=this.vertices.getDataset().getResource();
		
		for (int i = 1; i < vertices.getSize(); i++) {
			SFVertex3f vertex0=new SFVertex3f();
			SFVertex3f vertex1=new SFVertex3f();
			vertices.getValue(i-1, vertex0);
			vertices.getValue(i, vertex1);
			spline1.addCurve(new SFLine<SFVertex3f>(vertex0,vertex1));
		}
			
		return spline1;
	}
}
