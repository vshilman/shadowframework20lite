package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.geometry.vertices.SFVertexListDataUnit8;
import shadow.math.SFVertex2f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.viewer.SFViewer;

/**
 * If you need to align this test Data, run the {@link SFGenerateAllTestData} utility once.
 * No data will be generated (so nothing will work) until you do that; as an
 * alternative, you can set SFAbstractTest.storeData to true, and then run each test
 * one by one in test number order.
 * <br/>
 * Go to {@link SFAbstractTest} for general informations about this tests.
 * <br/>
 * Open the related FILENAME.xml file for a detailed view of this test contents. 
 * <br/>
 * Objective: verify the capability to store a Surface Geometry, load it and show
 * it on a Viewer.
 * 
 * 
 * @see SFAbstractTest, SFObjectModel, SFStructureArray, SFStructureReference, SFRegularSurfaceFunction, SFCurvedTubeFunctionData, SFCurvedTubeFunction, SFBasisSplineData, SFViewer
 * 
 * @author Alessandro Martinelli
 */
public class Test0001_MushroomGeometry extends SFAbstractTest{

	private static final String FILENAME="test0001";
	
	public static void main(String[] args) {
		execute(new Test0001_MushroomGeometry());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	

	@Override
	public void viewTestData() {
		// 3) Retrieve the Surface Function from file 'testsData\test0001.sf'
						
		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFCurvedTubeFunctionData retrievedData = loadDataset();

		//resource will be instance of 'SFCurvedTubeFunction'; we are no more using 'SFCurvedTubeFunctionData'
		SFSurfaceFunction resource=retrievedData.getResource();
		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PN");
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry(primitive.getConstructionPrimitive(), 8, 8,false,false);
		SFParametrizedGeometry geometry=new SFParametrizedGeometry(primitive,gridGeometry);
		geometry.setFunction(SFPrimitiveBlock.NORMAL, resource);
		geometry.setFunction(SFPrimitiveBlock.POSITION, resource);
		geometry.init();
		
		SFObjectModel node=new SFObjectModel();
		
		node.getModel().setRootGeometry(geometry);

			//Note: material Data and material program are different, because the same material data can be used with different programs
			float[][] colours={{0.5f,0,0}};
			SFStructureArray array=CommonMaterial.generateMaterial(colours); 
		
			node.getModel().getTransformComponent().setProgram("BasicPNTransform");
			node.getModel().getMaterialComponent().setProgram("BasicMat");
			//node.getModel().addTransform("BasicP","BasicN");
			
			//Set the selected material program to the node
			//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
			node.getModel().getMaterialComponent().addData(new SFStructureReference(array, 0));
			
		//4) Show the Surface Function on an SFViewer	
			
		SFViewer.generateFrame(node);
	}

	@Override
	public void buildTestData() {
		// 1) Create a Surface Function (Mushroom) and edit its Data.
		// At Editing time we need to work directly on Data version of each
		// Asset
		//Note: Here we use 'SFCurvedTubeFunctionData'
		SFCurvedTubeFunctionData function = new SFCurvedTubeFunctionData();	
		
		function.setFirstCurve(new SFBasisSplineData(new SFVertexListDataUnit8(),
				new SFVertex2f(0, 0),
				new SFVertex2f(0.0f, 0.1f),
				new SFVertex2f(0.025f, 0.2f),
				new SFVertex2f(0.0f, 0.3f),
				new SFVertex2f(0.0f, 0.4f),
				new SFVertex2f(0.0f, 0.5f)));
		
		function.setSecondCurve(new SFBasisSplineData(new SFVertexListDataUnit8(),
				new SFVertex2f(0.1f, 0),
				new SFVertex2f(0.2f, 0.1f),
				new SFVertex2f(0.2f, 0.2f),
				new SFVertex2f(0.05f, 0.3f),
				new SFVertex2f(0.05f, 0.4f),
				new SFVertex2f(0.45f, 0.5f),
				new SFVertex2f(0.05f, 0.5f)));
		
		store(function);
	}
	

}
