package shadow.renderer.contents.tests;

import shadow.geometry.functions.data.SFSplineCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFObjectModelData;
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
public class Test0008_RenderedTextures extends SFAbstractTest{

	private static final String FILENAME = "test0008";
	
	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		execute(new Test0008_RenderedTextures());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		viewTextureSet("FishTextures", 0);	
	}
	
	@Override
	public void buildTestData() {

		SFSplineCurvedTubeFunctionData curvedTubefunction = loadDataset("test0007");

		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(curvedTubefunction, 8, 4, "Triangle2PN");
			library.put("FishGeometry", geometry);
		
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.setGeometry("FishGeometry");
			objectModel.getTransformComponent().setProgram("BasicPNTransform");
			objectModel.getMaterialComponent().setProgram("BlackMat");
			library.put("Fish", objectModel);
			
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			SFRendererData renderer=new SFRendererData("NormalAndPosition",new SF2DCameraData(0.5f, 0.5f));
			drawnTexture.setRenderer(renderer);
			drawnTexture.setNode("Fish");
			drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("FishTextures", drawnTexture);

		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		store(library);
	}

}
