package shadow.renderer.contents.tests;

import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.transforms.SFTranslateFixed16Data;

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
public class Test0010_References extends SFAbstractTest{

	private static final String FILENAME = "test0010";
	
	public static void main(String[] args) {
		execute(new Test0010_References());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		viewNode("MushroomsScene01");
		
	}
	
	@Override
	public void buildTestData() {

		copyAssets("test0002", library, "Mushroom","BasicMatColours");

		SFObjectModelData objectModel1=new SFObjectModelData();
			objectModel1.setGeometry("Mushroom");
			objectModel1.setTransform(new SFTranslateFixed16Data(-0.5f, 0, 0));
			objectModel1.getMaterialComponent().addStructure("BasicMatColours", 10);
			objectModel1.getTransformComponent().setProgram("BasicPNTransform");
			objectModel1.getMaterialComponent().setProgram("BasicMat");
			library.put("OrangeMushroom", objectModel1);
			
		SFObjectModelData objectModel2=new SFObjectModelData();
			objectModel2.setGeometry("Mushroom");
			objectModel2.setTransform(new SFTranslateFixed16Data(0.5f, 0, 0));
			objectModel2.getMaterialComponent().addStructure("BasicMatColours", 16);
			objectModel2.getTransformComponent().setProgram("BasicPNTransform");
			objectModel2.getMaterialComponent().setProgram("BasicMat");
			library.put("BlueMushroom", objectModel2);

		SFReferenceNodeData scene=new SFReferenceNodeData();
			scene.addNode("OrangeMushroom");
			scene.addNode("BlueMushroom");
			library.put("MushroomsScene01", scene);
			
		store(library);
	}
}
