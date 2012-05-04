package shadow.renderer.contents.tests;

import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.image.bitmaps.data.SFSimplePerlinNoiseData;
import shadow.image.data.SFBitmapTextureData;
import shadow.image.data.SFDrawnRenderedTextureData;
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

public class Test0014_BumpMaps {

	private static final String root = "testsData";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		// Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline pipeline=new CommonPipeline();

		SFViewerObjectsLibrary test0013Library=new SFViewerObjectsLibrary(root, "test0013.sf");
			library.put("PebblesModel", test0013Library.getLibrary().retrieveDataset("PebblesModel"));

		SFSimplePerlinNoiseData perlinNoise=new SFSimplePerlinNoiseData();
			perlinNoise.setHeight(100);
			perlinNoise.setWidth(100);
			perlinNoise.setWeights(0.3f,0.1f,0.05f,0.05f,0.1f,0.1f,0.1f);
			perlinNoise.setRGB(false);
		
		SFBitmapTextureData textureData=new SFBitmapTextureData();
			textureData.setBitmap(perlinNoise);
			library.put("PerlinTexture", textureData);	
			
		SFQuadsSurfaceGeometryData rectangle=new SFQuadsSurfaceGeometryData();
			rectangle.setup(new SFRectangle2DFunctionData(-1, -1, 2, 2), 2, 2, new SFSimpleTexCoordGeometryuvData(), pipeline.getTexturePrimitive());
			library.put("FullScreenRectangle", rectangle);
			
		SFSimplePerlinNoiseData perlinNoise2=new SFSimplePerlinNoiseData();
			perlinNoise2.setHeight(100);
			perlinNoise2.setWidth(100);
			perlinNoise2.setWeights(0.5f,0.2f,0.15f,0.05f,0.1f,0.1f,0.1f);
			perlinNoise2.setRGB(false);
		
		SFBitmapTextureData textureData2=new SFBitmapTextureData();
			textureData2.setBitmap(perlinNoise2);
			library.put("PerlinTexture2", textureData2);

		SFObjectModelData pebblesGround=new SFObjectModelData();
			pebblesGround.setGeometry("FullScreenRectangle");
			pebblesGround.addTexture("TexturedMat", "PerlinTexture2");
			library.put("PebblesGround", pebblesGround);
		
		SFReferenceNodeData referenceNode=new SFReferenceNodeData();
			referenceNode.addNode("PebblesGround");
			referenceNode.addNode("PebblesModel");
		library.put("PebblesTextureModel", referenceNode);
		
			
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
		drawnTexture.setRenderer(new SFRendererData(new SFOneStepAlgorithmData("BumpMaps"),new SF2DCameraData(0.5f, 0.5f)));
		drawnTexture.setNode("PebblesTextureModel");
		drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		drawnTexture.addOutputTexture(400, 400,SFImageFormat.GRAY8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		library.put("PebblesTextures", drawnTexture);

		// 2) Store the library containing all elements into
		// 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0014.sf", library);
		
		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				root, "test0014.sf"));

		SFDataCenter.getDataCenter().makeDatasetAvailable("PebblesTextures", new SFDataCenterListener<SFDrawnRenderedTextureData>() {
			@Override
			public void onDatasetAvailable(String name, SFDrawnRenderedTextureData dataset) {
				SFRenderedTexturesSet set=dataset.getResource();
				SFTexture texture=new SFTexture(set, 0);
				SFTextureViewer.generateFrame(texture,CommonTextures.generateTextureSelectionController(texture, set.getTextureSize()));
			}
		});		
			
	}


}
