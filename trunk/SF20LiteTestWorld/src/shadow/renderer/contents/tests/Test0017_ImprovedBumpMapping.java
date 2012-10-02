package shadow.renderer.contents.tests;

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
public class Test0017_ImprovedBumpMapping extends SFAbstractTest{

	private static final String FILENAME = "test0017";

	public static void main(String[] args) {
		execute(new Test0017_ImprovedBumpMapping());
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
		
		copyAssets("test0015", library,  "OriginalNoise", "PerlinTexture", "PerlinTexture2",
				"PebblesModel","FullScreenRectangle",
				"PebblesTextureModel","PebblesGround",
				"PebblesTextures","BumpMappingsMushroom");

		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.setGeometry("BumpMappingsMushroom");
			objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.6f, 0, 2.4f));
			objectModel.getTransformComponent().setProgram("BasicBumpMapTransform");
			objectModel.getMaterialComponent().setProgram("ImprovedBumpMappedMat");
			objectModel.getMaterialComponent().addTexture( 1, "PebblesTextures");
			objectModel.getMaterialComponent().addTexture( 0, "PebblesTextures");
			library.put("StoneMushroom", objectModel);
			
		store(library);
	}
}
