package shadow.renderer.contents.tests;

import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.transforms.SFTranslateFixed16Data;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;

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
 * Objective:TODO
 * 
 * @author Alessandro Martinelli
 */
public class Test0006_ReferenceNode extends SFAbstractTest{

	private static final String FILENAME="test0006";
	
	public static void main(String[] args) {
		execute(new Test0006_ReferenceNode());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		viewNode("Scene01");
	}

	@Override
	public void buildTestData() {

		copyAssets("test0004", library, "PerlinTexture", "TexturedMushroom");
		
		//copying "ReddishGrayAndBrightPerlinNoise" from Test0005 to library
		SFViewerObjectsLibrary test0005Library=new SFViewerObjectsLibrary(root, "test0005.sf");
			library.put("ReddishGrayAndBrightPerlinNoise", test0005Library.getLibrary().retrieveDataset("ReddishGrayAndBrightPerlinNoise"));

		//First Object 
		SFObjectModelData objectModel1=new SFObjectModelData();//Asset
			objectModel1.setGeometry("TexturedMushroom");
			objectModel1.setTransform(new SFTranslateFixed16Data(-0.5f, -0.2f, 0));
			objectModel1.getMaterialComponent().addTexture( 0,"ReddishGrayAndBrightPerlinNoise");
			objectModel1.getTransformComponent().setProgram("BasicPNTx0Transform");
			objectModel1.getMaterialComponent().setProgram("TexturedMat");
			library.put("MushroomObject01", objectModel1);
		
		//Second Object 
		SFObjectModelData objectModel2=new SFObjectModelData();//Asset
			objectModel2.setGeometry("TexturedMushroom");
			objectModel2.setTransform(new SFTranslateFixed16Data(0.5f, -0.7f, 0));
			objectModel2.getMaterialComponent().addTexture(  1, "ReddishGrayAndBrightPerlinNoise");
			objectModel2.getTransformComponent().setProgram("BasicPNTx0Transform");
			objectModel2.getMaterialComponent().setProgram("TexturedMat");
			library.put("MushroomObject02", objectModel2);

		//Third Object 	
		SFObjectModelData objectModel3=new SFObjectModelData();//Asset
			objectModel3.setGeometry("TexturedMushroom");
			objectModel3.setTransform(new SFTranslateFixed16Data( 0.5f, +0.3f, 0));
			objectModel3.getMaterialComponent().addTexture( "PerlinTexture");
			objectModel3.getTransformComponent().setProgram("BasicPNTx0Transform");
			objectModel3.getMaterialComponent().setProgram("TexturedMat");
			library.put("MushroomObject03", objectModel3);

		//Second Object 
		SFReferenceNodeData scene=new SFReferenceNodeData();
			scene.addNode("MushroomObject01");
			scene.addNode("MushroomObject02");
			scene.addNode("MushroomObject03");
			library.put("Scene01", scene);
			
		store(library);
	}
}
