package shadow.renderer.contents.tests;

import shadow.math.SFMatrix3f;
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

public class Test0010b_References {

	private static final String root = "testsData";
	
	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		
		//Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline.prepare();
			
		SFViewerObjectsLibrary test0002Library=new SFViewerObjectsLibrary(root, "test0002.sf");
			library.put("Mushroom", test0002Library.getLibrary().retrieveDataset("Mushroom"));
			library.put("BasicMatColours", test0002Library.getLibrary().retrieveDataset("BasicMatColours"));
		
		SFReferenceNodeData scene=new SFReferenceNodeData();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int index=i*4+j;
				String name="ColouredMushroom"+index;
				float x=-0.7f+0.45f*j;
				float y=-0.85f+0.45f*i;
				
				SFObjectModelData objectModel1=new SFObjectModelData();
				objectModel1.placeGeometryAndScale("Mushroom", x, y, 0,0.6f);
				objectModel1.setOrientation(SFMatrix3f.getRotationY((float)(index*Math.PI/8)));
				objectModel1.addMaterial("BasicMat", "BasicMatColours", index);
				library.put(name, objectModel1);
				scene.addNode(name);	
			}
		}	
		library.put("MushroomsScene01b", scene);
			
		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0010b.sf", library);
		
		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0010b.sf"));

		SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomsScene01b", new SFDataCenterListener<SFReferenceNodeData>() {
			@Override
			public void onDatasetAvailable(String name, SFReferenceNodeData dataset) {
				SFViewer.generateFrame(dataset.getResource());
			}
		});
		
	}
}
