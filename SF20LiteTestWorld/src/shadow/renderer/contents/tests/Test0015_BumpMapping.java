package shadow.renderer.contents.tests;

import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;

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
 * Objective: TODO 
 * 
 * @author Alessandro Martinelli
 */
public class Test0015_BumpMapping extends SFAbstractTest{

	private static final String FILENAME = "test0015";

	public static void main(String[] args) {
		execute(new Test0015_BumpMapping());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();

		viewNode("StoneMushroom");
	}
	
	@Override
	public void buildTestData() {
		
		
		copyAssets("test0014", library, "OriginalNoise", "PerlinTexture", "PerlinTexture2",
				"PebblesModel","FullScreenRectangle",
				"PebblesTextureModel","PebblesGround",
				"PebblesTextures");
		
		SFCurvedTubeFunctionData retrievedFunction = loadDataset("test0001");
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8, new SFSimpleTexCoordGeometryuvData(4,4), "Triangle2BumpMap");
			library.put("BumpMappingsMushroom", geometry);
			
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.setGeometry("BumpMappingsMushroom");
			objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.6f, 0, 2.4f));
			objectModel.getTransformComponent().setProgram("BasicBumpMapTransform");
			objectModel.getMaterialComponent().setProgram("BumpMappedMat");
			objectModel.getMaterialComponent().addTexture( 1, "PebblesTextures");
			objectModel.getMaterialComponent().addTexture( 0, "PebblesTextures");
			library.put("StoneMushroom", objectModel);
			
		store(library);
	}

}
