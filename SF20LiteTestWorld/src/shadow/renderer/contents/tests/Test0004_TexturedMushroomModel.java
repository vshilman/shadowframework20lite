package shadow.renderer.contents.tests;

import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
import shadow.renderer.data.utils.SFDataUtility;

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
 * 
 * 
 * Objective: verify the capability to store a RenderedTexture, load it,
 * apply it to some geometry and show the result on a Viewer.
 * 
 * 
 * @author Alessandro Martinelli
 */
public class Test0004_TexturedMushroomModel extends SFAbstractTest{

	private static final String FILENAME="test0004";
	
	public static void main(String[] args) {
		execute(new Test0004_TexturedMushroomModel());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	

	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		viewNode("PerlinMushroom");
	}
	
	@Override
	public void buildTestData() {

		// 1) Create a Surface Function (Mushroom) and edit its Data.
		// At Editing time we need to work directly on Data version of each
		// Asset
		//Geometry Function for this Object Model is retrieved from Test0001
		copyAssets("test0003", library);
		
		SFCurvedTubeFunctionData retrievedFunction = (SFCurvedTubeFunctionData) SFDataUtility
				.loadDataset(root, "test0001.sf");
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8, new SFSimpleTexCoordGeometryuvData(), "Triangle2PNTxO");
			library.put("TexturedMushroom", geometry);
		
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.setGeometry("TexturedMushroom");
			objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.5f, 0, 2.5f));
			objectModel.getMaterialComponent().addTexture("PerlinTexture");
			objectModel.getTransformComponent().setProgram("BasicPNTx0Transform");
			objectModel.getMaterialComponent().setProgram("TexturedMat");
			//we insert the material in the library
			library.put("PerlinMushroom", objectModel);
				
		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0004.sf", library);
		
		SFDataUtility.saveXMLFile(root, "test0004", library);
	}
	

}
