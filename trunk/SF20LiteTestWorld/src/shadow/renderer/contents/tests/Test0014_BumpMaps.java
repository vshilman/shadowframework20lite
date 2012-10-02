package shadow.renderer.contents.tests;

import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.bitmaps.data.SFBitmapPerlinNoiseData;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.SFRendererData;

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
public class Test0014_BumpMaps extends SFAbstractTest{

	private static final String FILENAME = "test0014";

	public static void main(String[] args) {
		execute(new Test0014_BumpMaps());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();

		viewTextureSet("PebblesTextures", 1);
		
	}
	
	@Override
	public void buildTestData() {

		copyAssets("test0013", library, "PebblesModel");
		compileAndLoadXmlFile("Test0014");

		SFBitmapPerlinNoiseData perlinNoise=new SFBitmapPerlinNoiseData();
			perlinNoise.setHeight(100);
			perlinNoise.setWidth(100);
			perlinNoise.setWeights(0.3f,0.1f,0.05f,0.05f,0.1f,0.1f,0.1f);
	
		SFQuadsSurfaceGeometryData rectangle=new SFQuadsSurfaceGeometryData();
			rectangle.setup(new SFRectangle2DFunctionData(-1, -1, 2, 2), 2, 2, 
					new SFSimpleTexCoordGeometryuvData(), "Triangle2PNTxO");
			library.put("FullScreenRectangle", rectangle);
		
		SFObjectModelData pebblesGround=new SFObjectModelData();
			pebblesGround.setGeometry("FullScreenRectangle");
			pebblesGround.getMaterialComponent().addTexture("PerlinTexture2");
			pebblesGround.getTransformComponent().setProgram("BasicPNTx0Transform");
			pebblesGround.getMaterialComponent().setProgram("TexturedMat");
			library.put("PebblesGround", pebblesGround);
		
		SFReferenceNodeData referenceNode=new SFReferenceNodeData();
			referenceNode.addNode("PebblesGround");
			referenceNode.addNode("PebblesModel");
		library.put("PebblesTextureModel", referenceNode);
					
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
		drawnTexture.setRenderer(new SFRendererData("BumpMaps",new SF2DCameraData(0.5f, 0.5f)));
		drawnTexture.setNode("PebblesTextureModel");
		drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		drawnTexture.addOutputTexture(400, 400,SFImageFormat.GRAY8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		library.put("PebblesTextures", drawnTexture);
			
		store(library);
	}

}
