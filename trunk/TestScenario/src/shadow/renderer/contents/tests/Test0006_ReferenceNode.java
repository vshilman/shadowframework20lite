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

/**
 * <br/>
 * <h1>SF Contents Test/Tutorial Series</h1>
 * This Test/Tutorial is a part of a series whose aim is showing SF contents.
 * Each test is performed following the basic steps of any SF content lifetime.
 * <ol>
 * 		<li>Editing</li>
 * 		<li>Storing</li>
 * 		<li>Viewing</li>		
 * </ol>
 * Each SF content Module has a '*Data' counterpart which is involved in data processing.
 * The '*.Data' module will generate a proper SF module, and will be discarded when
 * unnecessary. 
 * 
 * @author Alessandro Martinelli
 */
public class Test0006_ReferenceNode {

	private static final String root = "testsData";
	
	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		
		//Preparation

		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline.prepare();

		//copying "PerlinTexture" and "TextureMushroom" from Test0004 to library
		SFViewerObjectsLibrary test0004Library=new SFViewerObjectsLibrary(root, "test0004.sf");
			library.put("PerlinTexture", test0004Library.getLibrary().retrieveDataset("PerlinTexture"));
			library.put("TexturedMushroom", test0004Library.getLibrary().retrieveDataset("TexturedMushroom"));
		
		//copying "ReddishGrayAndBrightPerlinNoise" from Test0005 to library
		SFViewerObjectsLibrary test0005Library=new SFViewerObjectsLibrary(root, "test0005.sf");
			library.put("ReddishGrayAndBrightPerlinNoise", test0005Library.getLibrary().retrieveDataset("ReddishGrayAndBrightPerlinNoise"));

		//First Object 
		SFObjectModelData objectModel1=new SFObjectModelData();//Asset
			objectModel1.placeGeometry("TexturedMushroom", -0.5f, -0.2f, 0);
			objectModel1.addTexture("TexturedMat", "ReddishGrayAndBrightPerlinNoise", 0, 0);
			library.put("MushroomObject01", objectModel1);
		
		//Second Object 
		SFObjectModelData objectModel2=new SFObjectModelData();//Asset
			objectModel2.placeGeometry("TexturedMushroom", 0.5f, -0.7f, 0);
			objectModel2.addTexture("TexturedMat", "ReddishGrayAndBrightPerlinNoise", 0, 1);
			library.put("MushroomObject02", objectModel2);

		//Third Object 	
		SFObjectModelData objectModel3=new SFObjectModelData();//Asset
			objectModel3.placeGeometry("TexturedMushroom", 0.5f, +0.3f, 0);
			objectModel3.addTexture("TexturedMat", "PerlinTexture");
			library.put("MushroomObject03", objectModel3);

		//Second Object 
		SFReferenceNodeData scene=new SFReferenceNodeData();
			scene.addNode("MushroomObject01");
			scene.addNode("MushroomObject02");
			scene.addNode("MushroomObject03");
			library.put("Scene01", scene);
			
		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0006.sf", library);
			
		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0006.sf"));
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("Scene01", new SFDataCenterListener<SFReferenceNodeData>() {
			@Override
			public void onDatasetAvailable(String name, SFReferenceNodeData dataset) {
				SFViewer.generateFrame(dataset.getResource(),SFViewer.getLightStepController());
			}
		});
	}

}
