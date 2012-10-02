package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.SFSimpleTexCoordGeometryuv;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.renderer.SFObjectModel;
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
 * Objective: verify the capability to store a RenderedTexture, load it,
 * apply it to some geometry and show the result on a Viewer.
 * 
 * @author Alessandro Martinelli
 */
public class Test0003_BitmapTexture extends SFAbstractTest{

	private static final String FILENAME="test0003";
	private static final String GEOMETRY_FILENAME="test0001";
	
	/**
	 */
	public static void main(String[] args) {
		execute(new Test0003_BitmapTexture());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		SFRenderedTexturesSet resource = getAlreadyAvailableDatasetResource("PerlinTexture");
		
		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFCurvedTubeFunctionData retrievedGeometryFunction = loadDataset(GEOMETRY_FILENAME);

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

			node.getModel().getMaterialComponent().getTextures().add( new SFTexture(resource,0));

			node.getModel().getTransformComponent().setProgram("BasicPNTx0Transform");
			node.getModel().getMaterialComponent().setProgram("TexturedMat");
			
		SFViewer.generateFrame(node);
	}

	public void buildTestData() {
		
		String xmlFileName="Test0003";

		compileAndLoadXmlFile(xmlFileName);
			
		store(library);
	}


}
