package shadow.renderer.viewer.tests;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexListDataUnit8;
import shadow.math.SFVertex3f;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFStructureArrayDataUnit8;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.system.data.SFObjectsLibrary;

public class SFGenerateLibrary {

	public static final String root = "libraries";
	public static final String filename = "library0001.sf";
	
	public static void main(String[] args) {

		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline.prepare();
		
		float[][] colours = CommonMaterial.generateColours();
		SFStructureArrayDataUnit8 material = CommonMaterial.generateMaterialData(colours);
			library.put("Materials", material);
			
		generateMushroomGeometry(library);
		generateLateralTubeGeometry(library);
		generateGlassGeometry(library);

		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, filename, library);
		SFDataUtility.saveXMLFile(root, filename, library);
	}

	public static void generateMushroomGeometry(SFObjectsLibrary library) {
		SFCurvedTubeFunctionData function = new SFCurvedTubeFunctionData();	
		function.setFirstCurve(new SFBasisSplineData(new SFVertexListDataUnit8(),
				new SFVertex3f(0, 0, 0),
				new SFVertex3f(0.0f, 0.1f, 0),
				new SFVertex3f(0.025f, 0.2f, 0),
				new SFVertex3f(0.0f, 0.3f, 0),
				new SFVertex3f(0.0f, 0.4f, 0),
				new SFVertex3f(0.0f, 0.5f, 0)));
		
		function.setSecondCurve(new SFBasisSplineData(new SFVertexListDataUnit8(),
				new SFVertex3f(0.1f, 0, 0),
				new SFVertex3f(0.2f, 0.1f, 0),
				new SFVertex3f(0.2f, 0.2f, 0),
				new SFVertex3f(0.05f, 0.3f, 0),
				new SFVertex3f(0.05f, 0.4f, 0),
				new SFVertex3f(0.45f, 0.5f, 0),
				new SFVertex3f(0.05f, 0.5f, 0)));
		
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(function, 8, 8,"Triangle2PN");
			library.put("Mushroom", geometry);
	}
	

	public static void generateLateralTubeGeometry(SFObjectsLibrary library) {
		SFCurvedTubeFunctionData function = new SFCurvedTubeFunctionData();	
		
		function.setFirstCurve(new SFBasisSplineData(new SFVertexListDataUnit8(),
				new SFVertex3f(0, 0, 0),
				new SFVertex3f(0.0f,0.1f,0),
				new SFVertex3f(0.0f,0.2f,0),
				new SFVertex3f(0.0f, 0.3f, 0),
				new SFVertex3f(0.0f, 0.4f, 0),
				new SFVertex3f(0.0f, 0.5f, 0),
				new SFVertex3f(0.1f, 0.5f, 0),
				new SFVertex3f(0.2f, 0.5f, 0),
				new SFVertex3f(0.3f, 0.5f, 0)));
		
		function.setSecondCurve(new SFBasisSplineData(new SFVertexListDataUnit8(),
				new SFVertex3f(0.1f, 0, 0),
				new SFVertex3f(0.2f, 0.1f, 0),
				new SFVertex3f(0.1f, 0.2f, 0),
				new SFVertex3f(0.1f, 0.3f, 0),
				new SFVertex3f(0.1f, 0.4f, 0),
				new SFVertex3f(0.1f, 0.4f, 0),
				new SFVertex3f(0.1f, 0.4f, 0),
				new SFVertex3f(0.2f, 0.4f, 0),
				new SFVertex3f(0.2f, 0.4f, 0)));
		
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(function, 8, 8,"Triangle2PN");
			library.put("Tube", geometry);
	}
	
	public static void generateGlassGeometry(SFObjectsLibrary library) {
		SFCurvedTubeFunctionData function = new SFCurvedTubeFunctionData();	
		
		function.setFirstCurve(new SFBasisSplineData(new SFVertexListDataUnit8(),
				new SFVertex3f(0, 0, 0),
				new SFVertex3f(0.0f,0.1f,0),
				new SFVertex3f(0.025f,0.2f,0),
				new SFVertex3f(0.0f, 0.3f, 0),
				new SFVertex3f(0.0f, 0.4f, 0),
				new SFVertex3f(0.0f, 0.5f, 0),
				new SFVertex3f(0.0f, 0.6f, 0),
				new SFVertex3f(0.0f, 0.7f, 0),
				new SFVertex3f(0.0f, 0.8f, 0)));
		
		function.setSecondCurve(new SFBasisSplineData(new SFVertexListDataUnit8(),
				new SFVertex3f(0.1f, 0, 0),
				new SFVertex3f(0.2f, 0.1f, 0),
				new SFVertex3f(0.2f, 0.2f, 0),
				new SFVertex3f(0.05f, 0.4f, 0),
				new SFVertex3f(0.05f, 0.4f, 0),
				new SFVertex3f(0.2f, 0.5f, 0),
				new SFVertex3f(0.2f, 0.6f, 0),
				new SFVertex3f(0.5f, 0.7f, 0),
				new SFVertex3f(0.4f, 0.8f, 0)));
				
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(function, 8, 8,"Triangle2PN");
			library.put("Glass", geometry);
	}
}
