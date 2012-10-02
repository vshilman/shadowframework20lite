package shadow.renderer.contents.tests;

import shadow.math.SFMatrix3f;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.transforms.SFRigidTransformData;

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
public class Test0010b_References extends SFAbstractTest{

	private static final String FILENAME = "test0010b";
	
	/**
	 * 
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		execute(new Test0010b_References());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		loadLibraryAsDataCenter();

		viewNode("MushroomsScene01b");
	}
	
	@Override
	public void buildTestData() {

		copyAssets("test0002", library, "Mushroom","BasicMatColours");
		
		SFReferenceNodeData scene=new SFReferenceNodeData();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int index=i*4+j;
				String name="ColouredMushroom"+index;
				float x=-0.7f+0.45f*j;
				float y=-0.85f+0.45f*i;
				
				SFObjectModelData objectModel1=new SFObjectModelData();
				objectModel1.setGeometry("Mushroom");
				objectModel1.setTransform(new SFRigidTransformData(x, y, 0,0.6f,SFMatrix3f.getRotationY((float)(index*Math.PI/8))));
				objectModel1.getMaterialComponent().addStructure( "BasicMatColours", index);
				objectModel1.getTransformComponent().setProgram("BasicPNTransform");
				objectModel1.getMaterialComponent().setProgram("BasicMat");
				library.put(name, objectModel1);
				scene.addNode(name);	
			}
		}	
		library.put("MushroomsScene01b", scene);
			
		store(library);
	}
}
