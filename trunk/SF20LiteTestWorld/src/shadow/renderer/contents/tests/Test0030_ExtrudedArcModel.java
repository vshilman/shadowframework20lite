package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFUniformBezier33fData;

public class Test0030_ExtrudedArcModel extends SFAbstractTest{

	private static final String FILENAME="test0030";
	
	public static void main(String[] args) {
		execute(new Test0030_ExtrudedArcModel());
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

		String xmlFileName="Test0030";

		compileAndLoadXmlFile(xmlFileName);
		
		copyAssets("test0027", library);
		
		SFUniformBezier33fData spline1Data=new SFUniformBezier33fData();
		spline1Data.addBezier33f(-0.7f, -0.5f, 0, -0.5f, 0, 0, 0, 0.6f, 0);
		spline1Data.addBezier33f(0, 0.6f, 0, 0.5f, 0, 0, 0.5f, -0.5f, 0);
		SFUniformBezier33fData spline2Data=new SFUniformBezier33fData();
		spline2Data.addBezier33f(0.0f, -0.5f, 0, -0.5f, -0.2f, 0, 0, 0.5f, 0);
		spline2Data.addBezier33f(0, 0.5f, 0, 0.5f, -0.2f, 0, 0.4f, -0.5f, 0);
		
		library.put("ExternalFrameworkContour",spline1Data);
		library.put("InternalFrameworkContour",spline2Data);
		
		store(library);
	}
}
