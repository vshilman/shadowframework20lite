package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.curves.data.SFLineData;
import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFSplineCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexFixedListData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
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

public class Test0018_RedTile {

	private static final String root = "testsData";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		// Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline pipeline=new CommonPipeline();

		SFSplineCurvedTubeFunctionData curvedTubefunction=new SFSplineCurvedTubeFunctionData();

			curvedTubefunction.addCurve(new SFLineData(new SFVertexFixedListData(),
					new SFVertex3f(-0.9f, -0.9f, -0.00f),new SFVertex3f(-0.9f, 0.9f, -0.00f)));
			curvedTubefunction.addCurve(new SFBasisSplineData(new SFVertexFixedListData(),
					new SFVertex3f(-0.8f, -0.9f, -0.00f),new SFVertex3f(-0.8f, -0.8f, -0.1f),
					new SFVertex3f(-0.8f, 0.8f, -0.1f),new SFVertex3f(-0.8f, 0.9f, -0.00f)));
			curvedTubefunction.addCurve(new SFBasisSplineData(new SFVertexFixedListData(),
					new SFVertex3f(0.8f, -0.9f, -0.00f),new SFVertex3f(0.8f, -0.8f, -0.1f),
					new SFVertex3f(0.8f, 0.8f, -0.1f),new SFVertex3f(0.8f, 0.9f, -0.00f)));
			curvedTubefunction.addCurve(new SFLineData(new SFVertexFixedListData(),
					new SFVertex3f(0.9f, -0.9f, -0.00f),new SFVertex3f(0.9f, 0.9f, -0.00f)));
			
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(curvedTubefunction, 8, 8, new SFSimpleTexCoordGeometryuvData(), pipeline.getPrimitive());
			library.put("Tile", geometry);
			
		SFViewerObjectsLibrary test0002Library=new SFViewerObjectsLibrary(root, "test0002.sf");
			library.put("BasicMatColours", test0002Library.getLibrary().retrieveDataset("BasicMatColours"));

		SFObjectModelData tileModel=new SFObjectModelData();
			tileModel.setGeometry("Tile");
			tileModel.addMaterial("BasicMat", "BasicMatColours", 1);
			library.put("TileModel", tileModel);
		
		SFViewerObjectsLibrary test0014Library=new SFViewerObjectsLibrary(root, "test0014.sf");
			library.put("PerlinTexture2", test0014Library.getLibrary().retrieveDataset("PerlinTexture2"));
			library.put("FullScreenRectangle", test0014Library.getLibrary().retrieveDataset("FullScreenRectangle"));
			library.put("PebblesGround", test0014Library.getLibrary().retrieveDataset("PebblesGround"));
			
		SFReferenceNodeData referenceNode=new SFReferenceNodeData();
			referenceNode.addNode("PebblesGround");
			referenceNode.addNode("TileModel");
		library.put("TileTextureModel", referenceNode);
		
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			drawnTexture.setRenderer(new SFRendererData(new SFOneStepAlgorithmData("BumpMaps"),new SF2DCameraData(0.5f, 0.5f)));
			drawnTexture.setNode("TileTextureModel");
			drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("TileTexture", drawnTexture);

		SFDataUtility.saveDataset(root, "test0018.sf", library);
		
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				root, "test0018.sf"));

		SFDataCenter.getDataCenter().makeDatasetAvailable("TileTexture", new SFDataCenterListener<SFDrawnRenderedTextureData>() {
			@Override
			public void onDatasetAvailable(String name, SFDrawnRenderedTextureData dataset) {
				SFRenderedTexturesSet set=dataset.getResource();
				SFTexture texture=new SFTexture(set, 0);
				SFTextureViewer.generateFrame(texture,CommonTextures.generateTextureSelectionController(texture, set.getTextureSize()));
			}
		});		
		
	}

	
}
