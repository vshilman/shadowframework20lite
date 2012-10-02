package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.curves.data.SFLineData;
import shadow.geometry.functions.data.SFSplineCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexListData16;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFDataAsset;
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
public class Test0018_RedTile extends SFAbstractTest{

	private static final String FILENAME = "test0018";

	public static void main(String[] args) {
		execute(new Test0018_RedTile());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();

		viewTextureSet("TileTexture", 0);
	}
	
	@Override
	public void buildTestData() {
		
		copyAssets("test0002", library, "BasicMatColours");

		copyAssets("test0014", library,  "OriginalNoise", "PerlinTexture2",
				"FullScreenRectangle","PebblesGround");
		
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			SFDataAsset<SFSurfaceFunction> curvedTubefunction = generateTileSurface();
			geometry.setup(curvedTubefunction, 8, 8, "Triangle2PN");
			library.put("Tile", geometry);
	
		SFObjectModelData tileModel=new SFObjectModelData();
			tileModel.setGeometry("Tile");
			tileModel.getMaterialComponent().addStructure("BasicMatColours", 1);
			tileModel.getTransformComponent().setProgram("BasicPNTransform");
			tileModel.getMaterialComponent().setProgram("BasicMat");
			library.put("TileModel", tileModel);
			
		SFReferenceNodeData referenceNode=new SFReferenceNodeData();
			referenceNode.addNode("PebblesGround");
			referenceNode.addNode("TileModel");
		library.put("TileTextureModel", referenceNode);
		
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			drawnTexture.setRenderer(new SFRendererData("BumpMaps",new SF2DCameraData(0.5f, 0.5f)));
			drawnTexture.setNode("TileTextureModel");
			drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("TileTexture", drawnTexture);
			
			
			
		store(library);
	}

	/* SurfaceFunction used for the Tile Model*/
	public SFDataAsset<SFSurfaceFunction> generateTileSurface() {
		SFSplineCurvedTubeFunctionData curvedTubefunction=new SFSplineCurvedTubeFunctionData();

		curvedTubefunction.addCurve(new SFLineData(new SFVertexListData16(),
				new SFVertex3f(-0.9f, -0.9f, -0.00f),new SFVertex3f(-0.9f, 0.9f, -0.00f)));
		curvedTubefunction.addCurve(new SFBasisSplineData(new SFVertexListData16(),
				new SFVertex3f(-0.8f, -0.9f, -0.00f),new SFVertex3f(-0.8f, -0.8f, -0.1f),
				new SFVertex3f(-0.8f, 0.8f, -0.1f),new SFVertex3f(-0.8f, 0.9f, -0.00f)));
		curvedTubefunction.addCurve(new SFBasisSplineData(new SFVertexListData16(),
				new SFVertex3f(0.8f, -0.9f, -0.00f),new SFVertex3f(0.8f, -0.8f, -0.1f),
				new SFVertex3f(0.8f, 0.8f, -0.1f),new SFVertex3f(0.8f, 0.9f, -0.00f)));
		curvedTubefunction.addCurve(new SFLineData(new SFVertexListData16(),
				new SFVertex3f(0.9f, -0.9f, -0.00f),new SFVertex3f(0.9f, 0.9f, -0.00f)));
		return curvedTubefunction;
	}
}
