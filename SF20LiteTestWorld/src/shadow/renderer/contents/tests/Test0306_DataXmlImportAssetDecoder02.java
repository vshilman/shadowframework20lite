package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.SFSimpleTexCoordGeometryuv;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.image.SFTexture;
import shadow.image.data.SFBitmapTextureData;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.java.SFAssetDecoder;
import shadow.renderer.data.java.SFXMLDataInterpreter;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;

public class Test0306_DataXmlImportAssetDecoder02 extends SFAbstractTest{

	private final String FILENAME = "test0306";

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
		
		Test0306_DataXmlImportAssetDecoder02 test=new Test0306_DataXmlImportAssetDecoder02();

		// Never remove this line of code!
		test.setupAmbient();
		
		// REMOVE this line of code WHEN you don't want to Build and Store Test Data (for instance 
		// when you have already done that and you simply want to show again the result)
		test.buildTestData();

		// REMOVE this line of code WHEN you don't want to Load and View Test Data
		test.viewTestData();
	}
	
	private final String root = "testsData";
	private final String xmlFilename = "test0003Asset";

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void buildTestData() {
		SFAssetDecoder decoder=new SFAssetDecoder();
		
		SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(decoder);
		interpreter.generateInterpretation("testsDataInput/"+xmlFilename+".xml");
		
		SFDataUtility.saveDataset(root, FILENAME+".sf", decoder.getDecodedAsset());
	}
	
	@Override
	public void viewTestData() {
		// 3) Retrieve the Surface Function from file 'testsData\test0001.sf'
						
		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFBitmapTextureData retrievedData = (SFBitmapTextureData) SFDataUtility
				.loadDataset(root, FILENAME+".sf");

		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFCurvedTubeFunctionData retrievedGeometryFunction = (SFCurvedTubeFunctionData) SFDataUtility
				.loadDataset(root, "test0001.sf");

		//resource will be instance of 'SFCurvedTubeFunction'; we are no more using 'SFCurvedTubeFunctionData'
		SFSurfaceFunction geometryResource=retrievedGeometryFunction.getResource();

		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PNTxO");
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry(primitive.getConstructionPrimitive(), 8, 8,false,false);
		SFParametrizedGeometry geometry=new SFParametrizedGeometry(primitive,gridGeometry);
		geometry.setFunction(SFPrimitiveBlock.NORMAL, geometryResource);
		geometry.setFunction(SFPrimitiveBlock.POSITION, geometryResource);
		geometry.setFunction(SFPrimitiveBlock.TXO, new SFSimpleTexCoordGeometryuv(2,2));
		geometry.init();
		
		SFObjectModel node=new SFObjectModel();
		
		node.getModel().setRootGeometry(geometry);

			node.getModel().getMaterialComponent().getTextures().add(0, new SFTexture(retrievedData.getResource(),0));
			
			//Set the selected material program to the node
			//Note: available material programs depends upon the programs loaded into the graphics Pipeline.

			node.getModel().getTransformComponent().setProgram("BasicPNTx0Transform");
			node.getModel().getMaterialComponent().setProgram("TexturedMat");

		SFViewer.generateFrame(node,SFViewer.getZoomController(),SFViewer.getRotationController());
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
