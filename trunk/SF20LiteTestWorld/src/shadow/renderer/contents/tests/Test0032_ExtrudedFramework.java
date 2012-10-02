package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFPolygonalSplineData;
import shadow.geometry.vertices.SFVertexListData16;

public class Test0032_ExtrudedFramework extends SFAbstractTest{

	private static final String FILENAME="test0032";
	
	public static void main(String[] args) {
		execute(new Test0032_ExtrudedFramework());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		viewNode("FrameworkModel");	
	}

	@Override
	public void buildTestData() {

		String xmlFileName="Test0032";

		compileAndLoadXmlFile(xmlFileName);
		
		copyAssets("test0027", library);

		float dimX1=0.4f;
		float dimX2=0.5f;
		float dimY1=0.5f;
		float dimY2=0.6f;
		
		SFPolygonalSplineData polygonaSpline1=new SFPolygonalSplineData();
			SFVertexListData16 spline1Vertices=new SFVertexListData16();
			spline1Vertices.addVertices(dimX1,dimY1);
			spline1Vertices.addVertices(-dimX1,dimY1);
			spline1Vertices.addVertices(-dimX1,-dimY1);
			spline1Vertices.addVertices(dimX1,-dimY1);
			spline1Vertices.addVertices(dimX1,dimY1);
			polygonaSpline1.setVertices(spline1Vertices);
			
		SFPolygonalSplineData polygonaSpline2=new SFPolygonalSplineData();
			SFVertexListData16 spline2Vertices=new SFVertexListData16();
			spline2Vertices.addVertices(dimX2,dimY2);
			spline2Vertices.addVertices(-dimX2,dimY2);
			spline2Vertices.addVertices(-dimX2,-dimY2);
			spline2Vertices.addVertices(dimX2,-dimY2);
			spline2Vertices.addVertices(dimX2,dimY2);
			polygonaSpline2.setVertices(spline2Vertices);

		library.put("ExternalFrameworkContour",polygonaSpline1);
		library.put("InternalFrameworkContour",polygonaSpline2);
		
		store(library);
	}
}
