package shadow.renderer.contents.tests;

import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.data.SFBitmapTextureData;
import shadow.image.data.SFFilteredRenderedTextureData;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.contents.tests.common.CommonTextures;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFObjectsLibrary;

/**
 * 
 * TODO missing test description
 * 
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
public class Test0005_FilteredTexture {

	private static final String root = "testsData";
	
	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		
		//Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline.prepare();
		
		SFBitmapTextureData textureData=(SFBitmapTextureData)SFDataUtility.loadDataset(root, "test0003.sf");	
		library.put("PerlinTexture", textureData);
			
		SFFilteredRenderedTextureData renderedTextureData=new SFFilteredRenderedTextureData();//Asset
			renderedTextureData.setLightStep("ReddishGrayAndBright");
			renderedTextureData.addTexture("TexturedMat", "PerlinTexture");
			renderedTextureData.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			renderedTextureData.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			//we insert the material in the library
			library.put("ReddishGrayAndBrightPerlinNoise", renderedTextureData);
		
		SFViewerObjectsLibrary mushroomLibrary=new SFViewerObjectsLibrary(root, "test0004.sf");
			library.put("TexturedMushroom", mushroomLibrary.getLibrary().retrieveDataset("TexturedMushroom"));
		
		SFObjectModelData objectModel=new SFObjectModelData();//Asset
			objectModel.placeGeometryAndScale("TexturedMushroom", 0, -0.5f, 0, 2);
			objectModel.addTexture("TexturedMat", "ReddishGrayAndBrightPerlinNoise");
			//we insert the material in the library
			library.put("ReddishGrayAndBrightMushroom", objectModel);
			
		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0005.sf", library);
			
		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0005.sf"));
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("ReddishGrayAndBrightMushroom", new SFDataCenterListener<SFObjectModelData>() {
			@Override
			public void onDatasetAvailable(String name, SFObjectModelData dataset) {
				SFObjectModel model=(SFObjectModel)dataset.getResource();
				SFViewer.generateFrame(model,SFViewer.getLightStepController(),
						CommonTextures.generateTextureSelectionController(model.getModel().getTextures().get(0).getTexture(), 2));
			}
		});
	}

}
