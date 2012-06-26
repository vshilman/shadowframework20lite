package shadow.renderer.contents.tests;

import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFSplineCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.image.SFDrawnRenderedTexture;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFTexture;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.contents.tests.common.CommonTextures;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFOneStepAlgorithmData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFTextureViewer;
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
public class Test0008_RenderedTextures {

	private static final String root = "testsData";
	
	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		
		//Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline pipeline=new CommonPipeline();
		
		SFSplineCurvedTubeFunctionData curvedTubefunction = (SFSplineCurvedTubeFunctionData) SFDataUtility
				.loadDataset(root, "test0007.sf");

		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(curvedTubefunction, 8, 4, new SFSimpleTexCoordGeometryuvData(), pipeline.getTexturePrimitive());
			library.put("FishGeometry", geometry);
		
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.getRootGeometryReference().setReference("FishGeometry");
			library.put("Fish", objectModel);
			
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			SFRendererData renderer=new SFRendererData(new SFOneStepAlgorithmData("NormalAndPosition"),new SF2DCameraData(0.5f, 0.5f));
			drawnTexture.setRenderer(renderer);
			drawnTexture.setNode("Fish");
			drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("FishTextures", drawnTexture);

		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0008.sf", library);

		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0008.sf"));

		SFDataCenter.getDataCenter().makeDatasetAvailable("FishTextures", new SFDataCenterListener<SFDrawnRenderedTextureData>() {
			@Override
			public void onDatasetAvailable(String name, SFDrawnRenderedTextureData dataset) {
				SFDrawnRenderedTexture drawnTexture=(SFDrawnRenderedTexture)dataset.getResource();
				SFTexture texture=new SFTexture(drawnTexture, 0);
				SFTextureViewer.generateFrame(texture,CommonTextures.generateTextureSelectionController(texture, 2));
			}
		});		
		
	}
}
