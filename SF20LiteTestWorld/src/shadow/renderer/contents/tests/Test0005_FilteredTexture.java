package shadow.renderer.contents.tests;

import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.data.SFFilteredRenderedTextureData;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonTextures;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

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
 * 
 * @author Alessandro Martinelli
 */
public class Test0005_FilteredTexture extends SFAbstractTest{

	private static final String FILENAME="test0005";
	
	public static void main(String[] args) {
		execute(new Test0005_FilteredTexture());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();
	
		SFDataCenter.getDataCenter().makeDatasetAvailable("ReddishGrayAndBrightMushroom", new SFDataCenterListener<SFObjectModelData>() {
			@Override
			public void onDatasetAvailable(String name, SFObjectModelData dataset) {
				SFObjectModel model=(SFObjectModel)dataset.getResource();
				SFViewer.generateFrame(model,SFViewer.getLightStepController(),
						SFViewer.getRotationController(),SFViewer.getZoomController(),
						CommonTextures.generateTextureSelectionController(model.getModel().getMaterialComponent().getTextures().get(0), 2));
			}
		});
	}

	@Override
	public void buildTestData() {

		copyAssets("test0003", library);
		
		SFFilteredRenderedTextureData renderedTextureData=new SFFilteredRenderedTextureData();//Asset
			renderedTextureData.getLightComponent().setProgram("ReddishGrayAndBright");
			renderedTextureData.getMaterialComponent().setProgram("TexturedMat");
			renderedTextureData.getMaterialComponent().addTexture(0, "PerlinTexture");
			renderedTextureData.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			renderedTextureData.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			//we insert the material in the library
			library.put("ReddishGrayAndBrightPerlinNoise", renderedTextureData);
		
		SFViewerObjectsLibrary mushroomLibrary=new SFViewerObjectsLibrary(root, "test0004.sf");
			library.put("TexturedMushroom", mushroomLibrary.getLibrary().retrieveDataset("TexturedMushroom"));
		
		SFObjectModelData objectModel=new SFObjectModelData();//Asset
			objectModel.setGeometry("TexturedMushroom");
			objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.5f, 0, 2.5f));
			objectModel.getTransformComponent().setProgram("BasicPNTx0Transform");
			objectModel.getMaterialComponent().setProgram("TexturedMat");
			objectModel.getMaterialComponent().addTexture( "ReddishGrayAndBrightPerlinNoise");
			//we insert the material in the library
			library.put("ReddishGrayAndBrightMushroom", objectModel);
			
		store(library);
	}
}
