package shadow.renderer.contents.tests;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFObjectsLibrary;

public class Test0011_MoreReferences {

	private static final String root = "testsData";
	
	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		
		//Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline.prepare();

		SFViewerObjectsLibrary test0002Library=new SFViewerObjectsLibrary(root, "test0010.sf");
			library.put("Mushroom", test0002Library.getLibrary().retrieveDataset("Mushroom"));
			library.put("BasicMatColours", test0002Library.getLibrary().retrieveDataset("BasicMatColours"));
			library.put("BlueMushroom", test0002Library.getLibrary().retrieveDataset("BlueMushroom"));
			library.put("OrangeMushroom", test0002Library.getLibrary().retrieveDataset("OrangeMushroom"));
		
		SFReferenceNodeData scene2=new SFReferenceNodeData();
			scene2.setPosition(new SFVertex3f(0.7f, 0, 0));
			scene2.addNode("OrangeMushroom");
			library.put("MushroomScene02", scene2);
			
		SFReferenceNodeData scene3=new SFReferenceNodeData();
			scene3.setPosition(new SFVertex3f(-0.7f, 0, 0));
			scene3.addNode("BlueMushroom");
			library.put("MushroomScene03", scene3);
			
		SFReferenceNodeData scene4=new SFReferenceNodeData();
			scene4.setPosition(new SFVertex3f(0, 0, -0.5f));
			scene4.addNode("BlueMushroom");
			library.put("MushroomScene04", scene4);
			
		SFReferenceNodeData scene5=new SFReferenceNodeData();
			scene5.setPosition(new SFVertex3f(0, 0, 0.5f));
			scene5.addNode("OrangeMushroom");
			library.put("MushroomScene05", scene5);
			
		SFReferenceNodeData scene6=new SFReferenceNodeData();
			scene6.setOrientation(SFMatrix3f.getRotationX(1.57f*0.33f));
			scene6.addNode("MushroomScene02");
			scene6.addNode("MushroomScene03"); 
			scene6.addNode("MushroomScene04"); 
			scene6.addNode("MushroomScene05"); 
			library.put("MushroomScene06", scene6);
		
		SFReferenceNodeData scene7=new SFReferenceNodeData();
			scene7.setPosition(new SFVertex3f(0, -0.5f, 0));
			scene7.addNode("MushroomScene06"); 
			library.put("MushroomScene07", scene7);
			
		SFReferenceNodeData scene8=new SFReferenceNodeData();
			scene8.addNode("MushroomScene06"); 
			scene8.addNode("MushroomScene07"); 
			library.put("MushroomScene08", scene8);
			
		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0011.sf", library);
		
		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0011.sf"));

		SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomScene08", new SFDataCenterListener<SFReferenceNodeData>() {
			@Override
			public void onDatasetAvailable(String name, SFReferenceNodeData dataset) {
				SFViewer.generateFrame(dataset.getResource());
			}
		});
		
	}
}
