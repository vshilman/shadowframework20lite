package shadow.renderer.contents.tests;

import shadow.math.SFMatrix3f;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.transforms.SFRigidTransformData;
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
public class Test0011_MoreReferences extends SFAbstractTest{

	private static final String FILENAME = "test0011";

	public static void main(String[] args) {
		execute(new Test0011_MoreReferences());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();

		viewNode("MushroomScene08");
		
	}
	
	@Override
	public void buildTestData() {

		copyAssets("test0010", library, "Mushroom",
				"BasicMatColours","BlueMushroom","OrangeMushroom");
	
		SFReferenceNodeData scene2=new SFReferenceNodeData();
			scene2.setTransform(new SFTranslateFixed16Data(0.7f, 0, 0));
			scene2.addNode("OrangeMushroom");
			library.put("MushroomScene02", scene2);
			
		SFReferenceNodeData scene3=new SFReferenceNodeData();
			scene3.setTransform(new SFTranslateFixed16Data(-0.7f, 0, 0));
			scene3.addNode("BlueMushroom");
			library.put("MushroomScene03", scene3);
			
		SFReferenceNodeData scene4=new SFReferenceNodeData();
			scene4.setTransform(new SFTranslateFixed16Data(0, 0, -0.5f));
			scene4.addNode("BlueMushroom");
			library.put("MushroomScene04", scene4);
			
		SFReferenceNodeData scene5=new SFReferenceNodeData();
			scene5.setTransform(new SFTranslateFixed16Data(0, 0, 0.5f));
			scene5.addNode("OrangeMushroom");
			library.put("MushroomScene05", scene5);
			
		SFReferenceNodeData scene6=new SFReferenceNodeData();
			scene6.setTransform(new SFRigidTransformData(0, 0, 0,1 ,SFMatrix3f.getRotationX(1.57f*0.33f)));
			scene6.addNode("MushroomScene02");
			scene6.addNode("MushroomScene03"); 
			scene6.addNode("MushroomScene04"); 
			scene6.addNode("MushroomScene05"); 
			library.put("MushroomScene06", scene6);
		
		SFReferenceNodeData scene7=new SFReferenceNodeData();
			scene7.setTransform(new SFTranslateFixed16Data(0, -0.5f, 0));
			scene7.addNode("MushroomScene06"); 
			library.put("MushroomScene07", scene7);
			
		SFReferenceNodeData scene8=new SFReferenceNodeData();
			scene8.addNode("MushroomScene06"); 
			scene8.addNode("MushroomScene07"); 
			library.put("MushroomScene08", scene8);
			
		store(library);
	}
}
