package shadow.renderer.contents.tests;

import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFObjectsLibrary;

public class Test0010_References {

	private static final String root = "testsData";
	
	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		
		//Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline.prepare();
			
		//copying "Mushroom", "RedMushroom" and "BasicMatColours" from Test0002 to library
		SFViewerObjectsLibrary test0002Library=new SFViewerObjectsLibrary(root, "test0002.sf");
			library.put("Mushroom", test0002Library.getLibrary().retrieveDataset("Mushroom"));
			library.put("BasicMatColours", test0002Library.getLibrary().retrieveDataset("BasicMatColours"));

		SFObjectModelData objectModel1=new SFObjectModelData();
			objectModel1.placeGeometry("Mushroom", -0.5f, 0, 0);
			objectModel1.addMaterial("BasicMat", "BasicMatColours", 10);
			library.put("OrangeMushroom", objectModel1);
			
		SFObjectModelData objectModel2=new SFObjectModelData();
			objectModel2.placeGeometry("Mushroom", 0.5f, 0, 0);
			objectModel2.addMaterial("BasicMat", "BasicMatColours", 16);
			library.put("BlueMushroom", objectModel2);

		SFReferenceNodeData scene=new SFReferenceNodeData();
			scene.addNode("OrangeMushroom");
			scene.addNode("BlueMushroom");
			library.put("MushroomsScene01", scene);
			
		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0010.sf", library);
		
		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0010.sf"));

		SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomsScene01", new SFDataCenterListener<SFReferenceNodeData>() {
			@Override
			public void onDatasetAvailable(String name, SFReferenceNodeData dataset) {
				SFViewer.generateFrame(dataset.getResource());
			}
		});
		
	}
}
