package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFSplineCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexListData16;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFTextureDataToken;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.data.transforms.SFTranslateFixed16Data;

/**
 * If you need to align this test Data, run the {@link SFGenerateAllTestData} utility once.
 * <br/>
 * Go to {@link SFAbstractTest} for general informations about this tests.
 * <br/>
 * Open the related FILENAME.xml file for a detailed view of this test contents. 
 * <br/>
 * Objective: TODO 
 * 
 * @author Alessandro Martinelli
 */
public class Test0009_TwoStepsRenderedTextures extends SFAbstractTest{

	private static final String FILENAME = "test0009";
	
	public static void main(String[] args) {
		execute(new Test0009_TwoStepsRenderedTextures());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		viewTextureSet("HooksDrawnTexture", 0);
	}
	
	@Override
	public void buildTestData() {

		SFSplineCurvedTubeFunctionData curvedTubefunction=new SFSplineCurvedTubeFunctionData();
			curvedTubefunction.addCurve(new SFBasisSplineData(new SFVertexListData16(),
					new SFVertex3f(0, 0, 0),
					new SFVertex3f(0, 0.2f, 0),
					new SFVertex3f(-0.4f, 0.3f, 0),
					new SFVertex3f(-0.4f, 0.6f, 0),
					new SFVertex3f(0.0f, 0.8f, 0),
					new SFVertex3f(0.1f, 0.5f, 0),
					new SFVertex3f(0.3f, 0.5f, 0)
					));
			curvedTubefunction.addCurve(new SFBasisSplineData(new SFVertexListData16(),
					new SFVertex3f(0, 0, 0),
					new SFVertex3f(0, 0.2f, 0),
					new SFVertex3f(-0.2f, 0.4f, 0),
					new SFVertex3f(0.0f, 0.6f, 0),
					new SFVertex3f(0.1f, 0.5f, 0),
					new SFVertex3f(0.3f, 0.5f, 0)
					));
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(curvedTubefunction, 17, 2, new SFSimpleTexCoordGeometryuvData(), "Triangle2PNTxO");
			library.put("HookGeometry", geometry);
		
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.getRootGeometryReference().setReference("HookGeometry");
			objectModel.getTransformComponent().setProgram("BasicPNTransform");
			objectModel.getMaterialComponent().setProgram("BlackMat");
			library.put("Hook", objectModel);
		
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			drawnTexture.setRenderer(new SFRendererData("NormalAndPosition",new SF2DCameraData(0.5f, 0.5f)));
			drawnTexture.setNode("Hook");//Different from the previous example... 
			drawnTexture.addOutputTexture(new SFTextureDataToken(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT));
			drawnTexture.addOutputTexture(new SFTextureDataToken(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT));
			library.put("HookTextures", drawnTexture);
		
		//Would like to have some kind of RECT geometry...
		SFQuadsSurfaceGeometryData geometryShow=new SFQuadsSurfaceGeometryData();
			geometryShow.setup(new SFRectangle2DFunctionData(0, 0, 1, 1), 2, 2, new SFSimpleTexCoordGeometryuvData(), "Triangle2PNTxO");
			library.put("QuarterScreenRectangle", geometryShow);
		
		SFObjectModelData visibleModel1=new SFObjectModelData();
			visibleModel1.setGeometry("QuarterScreenRectangle");
			visibleModel1.setTransform(new SFTranslateFixed16Data(-1, -1, 0));
			visibleModel1.getMaterialComponent().addTexture( 1, "HookTextures");
			visibleModel1.getTransformComponent().setProgram("BasicPNTx0Transform");
			visibleModel1.getMaterialComponent().setProgram("TexturedMat");
			library.put("HookNumber1", visibleModel1);
			
		SFObjectModelData visibleModel2=new SFObjectModelData();
			visibleModel2.setGeometry("QuarterScreenRectangle");
			visibleModel2.getMaterialComponent().addTexture( 0, "HookTextures");
			visibleModel2.getTransformComponent().setProgram("BasicPNTx0Transform");
			visibleModel2.getMaterialComponent().setProgram("TexturedMat");
			library.put("HookNumber2", visibleModel2);

		SFReferenceNodeData scene=new SFReferenceNodeData();
			scene.addNode("HookNumber1");
			scene.addNode("HookNumber2");
			library.put("HooksScene", scene);

		SFDrawnRenderedTextureData drawnTexture2=new SFDrawnRenderedTextureData();
			drawnTexture2.setRenderer(new SFRendererData("ReddishGrayAndBright",new SF2DCameraData(0.5f, 0.5f)));
			drawnTexture2.setNode("HooksScene");//Different from the previous example... 
			drawnTexture2.addOutputTexture(400, 400, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			drawnTexture2.addOutputTexture(400, 400, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		library.put("HooksDrawnTexture", drawnTexture2);
		
		store(library);
	}
	
}
