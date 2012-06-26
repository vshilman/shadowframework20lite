package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.functions.data.SFSplineCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexFixedListData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFTexture;
import shadow.image.SFTextureDataToken;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.math.SFVertex3f;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.contents.tests.common.CommonTextures;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFOneStepAlgorithmData;
import shadow.renderer.data.SFReferenceNodeData;
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
 * Each SF content Module has a '*Data' counterpart which is invol
 * ved in data processing.
 * The '*.Data' module will generate a proper SF module, and will be discarded when
 * unnecessary. 
 * 
 * @author Alessandro Martinelli
 */
public class Test0009_TwoStepsRenderedTextures {

	private static final String root = "testsData";
	
	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		
		//Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline pipeline=new CommonPipeline();

		SFSplineCurvedTubeFunctionData curvedTubefunction=new SFSplineCurvedTubeFunctionData();
			curvedTubefunction.addCurve(new SFBasisSplineData(new SFVertexFixedListData(),
					new SFVertex3f(0, 0, 0),
					new SFVertex3f(0, 0.2f, 0),
					new SFVertex3f(-0.4f, 0.3f, 0),
					new SFVertex3f(-0.4f, 0.6f, 0),
					new SFVertex3f(0.0f, 0.8f, 0),
					new SFVertex3f(0.1f, 0.5f, 0),
					new SFVertex3f(0.3f, 0.5f, 0)
					));
			curvedTubefunction.addCurve(new SFBasisSplineData(new SFVertexFixedListData(),
					new SFVertex3f(0, 0, 0),
					new SFVertex3f(0, 0.2f, 0),
					new SFVertex3f(-0.2f, 0.4f, 0),
					new SFVertex3f(0.0f, 0.6f, 0),
					new SFVertex3f(0.1f, 0.5f, 0),
					new SFVertex3f(0.3f, 0.5f, 0)
					));
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(curvedTubefunction, 17, 2, new SFSimpleTexCoordGeometryuvData(), pipeline.getTexturePrimitive());
			library.put("HookGeometry", geometry);
		
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.getRootGeometryReference().setReference("HookGeometry");
			library.put("Hook", objectModel);
		
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			drawnTexture.setRenderer(new SFRendererData(new SFOneStepAlgorithmData("NormalAndPosition"),new SF2DCameraData(0.5f, 0.5f)));
			drawnTexture.setNode("Hook");//Different from the previous example... 
			drawnTexture.addOutputTexture(new SFTextureDataToken(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT));
			drawnTexture.addOutputTexture(new SFTextureDataToken(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT));
			library.put("HookTextures", drawnTexture);
		
		//Would like to have some kind of RECT geometry...
		SFQuadsSurfaceGeometryData geometryShow=new SFQuadsSurfaceGeometryData();
			geometryShow.setup(new SFRectangle2DFunctionData(0, 0, 1, 1), 2, 2, new SFSimpleTexCoordGeometryuvData(), pipeline.getTexturePrimitive());
			library.put("QuarterScreenRectangle", geometryShow);
		
		SFObjectModelData visibleModel1=new SFObjectModelData();
			visibleModel1.placeGeometry("QuarterScreenRectangle", -1, -1, 0);
			visibleModel1.addTexture("TexturedMat", "HookTextures", 0, 1);
			library.put("HookNumber1", visibleModel1);
			
		SFObjectModelData visibleModel2=new SFObjectModelData();
			visibleModel2.setGeometry("QuarterScreenRectangle");
			visibleModel2.addTexture("TexturedMat", "HookTextures", 0, 0);
			library.put("HookNumber2", visibleModel2);

		SFReferenceNodeData scene=new SFReferenceNodeData();
			scene.addNode("HookNumber1");
			scene.addNode("HookNumber2");
			library.put("HooksScene", scene);

		SFDrawnRenderedTextureData drawnTexture2=new SFDrawnRenderedTextureData();
			drawnTexture2.setRenderer(new SFRendererData(new SFOneStepAlgorithmData("ReddishGrayAndBright"),new SF2DCameraData(0.5f, 0.5f)));
			drawnTexture2.setNode("HooksScene");//Different from the previous example... 
			drawnTexture2.addOutputTexture(400, 400, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			drawnTexture2.addOutputTexture(400, 400, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		library.put("HooksDrawnTexture", drawnTexture2);

		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0009.sf", library);

		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0009.sf"));

		SFDataCenter.getDataCenter().makeDatasetAvailable("HooksDrawnTexture", new SFDataCenterListener<SFDrawnRenderedTextureData>() {
			@Override
			public void onDatasetAvailable(String name, SFDrawnRenderedTextureData dataset) {
				SFTexture texture=new SFTexture(dataset.getResource(), 0);
				SFTextureViewer.generateFrame(texture,CommonTextures.generateTextureSelectionController(texture, 2));
			}
		});		
		
	}
}
