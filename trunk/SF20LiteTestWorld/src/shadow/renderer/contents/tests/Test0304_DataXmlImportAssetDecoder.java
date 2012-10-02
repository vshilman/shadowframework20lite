package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.java.SFAssetDecoder;
import shadow.renderer.data.java.SFXMLDataInterpreter;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;

public class Test0304_DataXmlImportAssetDecoder extends SFAbstractTest{

	private static final String FILENAME = "test0304";

	/**
	 * buildAndStoreTestData:
	 * 
	 * 1) Create a Surface Object (Mushroom) and edit its Data. 
	 * 2) Store the Surface Function in file 'testsData\test0001.sf'
	 * 3) Store the Surface Function in file 'testsData\test0001.xml'
	 * 
	 * loadAndViewTestData: 
	 * 
	 * 4) Recompile 
	 * 5) Retrieve the Surface Function from file 'testsData\test0001.sf' 
	 * 6) Show the Surface Function on an SFViewer
	 * 
	 */
	public static void main(String[] args) {
		
		Test0304_DataXmlImportAssetDecoder test=new Test0304_DataXmlImportAssetDecoder();

		// Never remove this line of code!
		test.setupAmbient();
		
		// REMOVE this line of code WHEN you don't want to Build and Store Test Data (for instance 
		// when you have already done that and you simply want to show again the result)
		test.buildTestData();

		// REMOVE this line of code WHEN you don't want to Load and View Test Data
		test.viewTestData();
	}
	
	private final String root = "testsData";
	private final String xmlFilename = "test0001";

	
	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return FILENAME;
	}
	
	
	@Override
	public void buildTestData() {
		SFAssetDecoder decoder=new SFAssetDecoder();
		
		SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(decoder);
		interpreter.generateInterpretation("testsData/"+xmlFilename+".xml");
		
		// 2) Store the Surface Function in file 'testsData\test0001.sf'
		SFDataUtility.saveDataset(root, FILENAME+".sf", decoder.getDecodedAsset());
	}
	
	@Override
	public void viewTestData() {
		// 3) Retrieve the Surface Function from file 'testsData\test0001.sf'
						
		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFCurvedTubeFunctionData retrievedData = (SFCurvedTubeFunctionData) SFDataUtility
				.loadDataset(root, FILENAME+".sf");

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
		
			//Extract the index-th material from file libraries/library
			SFStructureReference materialReference=new SFStructureReference(array, 0);

			node.getModel().getTransformComponent().setProgram("BasicPNTransform");
			node.getModel().getMaterialComponent().setProgram("BasicMat");
			node.getModel().getMaterialComponent().addData(materialReference);


		//4) Show the Surface Function on an SFViewer	
			
		SFViewer.generateFrame(node);
	}

	@Override
	public void setupAmbient() {
		//Preparation
		
		// In order to work properly load utilities at step (3) will need the Datacenter to
		// contain a valid DatasetFactory...
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		//... and a complete ShadowFramework Pipeline must be prepared.
		CommonPipeline.prepare();
	}

	
}
