package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFRadialSurfaceFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexFixedListData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFTextureReference;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFOneStepAlgorithmData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFFrameController;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFObjectsLibrary;

public class Test0019_SpottedMushRoom {

	private static final String root = "testsData";

	public static void main(String[] args) {
		(new Test0019_SpottedMushRoom()).test();
	}

	private SFObjectModel objectModel;
	private String[] menuName={"Tiled Mushroom","Spotted Mushroom","Stoned Mushroom","Lot of Fishes","Lot of Fishes (Transparency)"};
	private SFRenderedTexturesSet[] textureSet={null,null,null,null,null};
	
	
	public void test() {
		// Preparation
		CommonPipeline pipeline=new CommonPipeline();
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		generateData(pipeline);
		viewData();
	}


	public void viewData() {
		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				root, "test0019.sf"));

		SFDataCenter.getDataCenter().makeDatasetAvailable("TileMushroom",
				new SFDataCenterListener<SFObjectModelData>() {
					@Override
					public void onDatasetAvailable(String name,SFObjectModelData dataset) {
						objectModel=(SFObjectModel)(dataset.getResource());
						SFViewer viewer=SFViewer.generateFrame(dataset.getResource(),spottedMushroomFrameController,
								SFViewer.getLightStepController(),SFViewer.getRotationController(),SFViewer.getWireframeController(),SFViewer.getZoomController());
						viewer.setRotateModel(true, 0.01f);
					}
				});
		
		textureSet[0]=(SFRenderedTexturesSet)((SFDataAsset<?>)SFDataCenter.getDataCenter().getAlreadyAvailableDataset("TileTexture")).getResource();
		textureSet[1]=(SFRenderedTexturesSet)((SFDataAsset<?>)SFDataCenter.getDataCenter().getAlreadyAvailableDataset("SpotTexture")).getResource();
		textureSet[2]=(SFRenderedTexturesSet)((SFDataAsset<?>)SFDataCenter.getDataCenter().getAlreadyAvailableDataset("PebblesTextures")).getResource();
		textureSet[3]=(SFRenderedTexturesSet)((SFDataAsset<?>)SFDataCenter.getDataCenter().getAlreadyAvailableDataset("FishTextures")).getResource();
		textureSet[4]=(SFRenderedTexturesSet)((SFDataAsset<?>)SFDataCenter.getDataCenter().getAlreadyAvailableDataset("TransparentFishTextures")).getResource();
	}


	public void generateData(CommonPipeline pipeline) {
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		
		SFViewerObjectsLibrary test0014Library=new SFViewerObjectsLibrary(root, "test0014.sf");
			library.put("PerlinTexture", test0014Library.getLibrary().retrieveDataset("PerlinTexture"));
			library.put("PebblesModel", test0014Library.getLibrary().retrieveDataset("PebblesModel"));
			library.put("PebblesTextureModel", test0014Library.getLibrary().retrieveDataset("PebblesTextureModel"));
			library.put("PebblesGround", test0014Library.getLibrary().retrieveDataset("PebblesGround"));
			library.put("PebblesTextures", test0014Library.getLibrary().retrieveDataset("PebblesTextures"));
			
		SFViewerObjectsLibrary test0018Library=new SFViewerObjectsLibrary(root, "test0018.sf");
			library.put("PerlinTexture2", test0018Library.getLibrary().retrieveDataset("PerlinTexture2"));
			library.put("FullScreenRectangle", test0018Library.getLibrary().retrieveDataset("FullScreenRectangle"));
			library.put("PebblesGround", test0018Library.getLibrary().retrieveDataset("PebblesGround"));
			library.put("Tile", test0018Library.getLibrary().retrieveDataset("Tile"));
			library.put("BasicMatColours", test0018Library.getLibrary().retrieveDataset("BasicMatColours"));
			library.put("TileModel", test0018Library.getLibrary().retrieveDataset("TileModel"));
			library.put("TileTextureModel", test0018Library.getLibrary().retrieveDataset("TileTextureModel"));
			library.put("TileTexture", test0018Library.getLibrary().retrieveDataset("TileTexture"));

		SFViewerObjectsLibrary test0008Library=new SFViewerObjectsLibrary(root, "test0008.sf");
			library.put("FishGeometry", test0008Library.getLibrary().retrieveDataset("FishGeometry"));
		
		SFRadialSurfaceFunctionData spotFunction = new SFRadialSurfaceFunctionData();
			spotFunction.setFirstCurve(new SFBasisSplineData(new SFVertexFixedListData(),true,
					new SFVertex3f(0.8f, 0.8f, 0), new SFVertex3f(-0.8f, 0.8f, 0),
					new SFVertex3f(-0.7f, -0.8f, 0), new SFVertex3f(0.8f, -0.7f, 0)));
			spotFunction.setSecondCurve(new SFBasisSplineData(new SFVertexFixedListData(),
					new SFVertex3f(0.0f, 0, -0.3f), new SFVertex3f(0.7f, 0.0f, -0.3f),
					new SFVertex3f(0.7f, 0.0f, 0)));

		SFQuadsSurfaceGeometryData spotGeometry=new SFQuadsSurfaceGeometryData();
			spotGeometry.setup(spotFunction, 8, 8, new SFSimpleTexCoordGeometryuvData(), pipeline.getPrimitive());
			library.put("Spot", spotGeometry);

		SFObjectModelData spotModel=new SFObjectModelData();
			spotModel.setGeometry("Spot");
			spotModel.addMaterial("BasicMat", "BasicMatColours", 10);
			library.put("SpotModel", spotModel);
		
		SFReferenceNodeData referenceNode=new SFReferenceNodeData();
			referenceNode.addNode("PebblesGround");
			referenceNode.addNode("SpotModel");
		library.put("SpotTextureModel", referenceNode);
		
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			drawnTexture.setRenderer(new SFRendererData(new SFOneStepAlgorithmData("BumpMaps"),new SF2DCameraData(0.5f, 0.5f)));
			drawnTexture.setNode("SpotTextureModel");
			drawnTexture.addOutputTexture(100, 100, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			drawnTexture.addOutputTexture(100, 100, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("SpotTexture", drawnTexture);	
			
		SFObjectModelData fishModel=new SFObjectModelData();
			fishModel.setOrientation(SFMatrix3f.getRotationZ(0.25f*(float)(Math.PI)));
			fishModel.setScale(1.5f);
			fishModel.addMaterial("BasicMat","BasicMatColours",4);
			fishModel.getRootGeometryReference().setReference("FishGeometry");
			library.put("Fish", fishModel);//not the same as Test0008
			
		SFDrawnRenderedTextureData fishTexture=new SFDrawnRenderedTextureData();
			fishTexture.setRenderer(new SFRendererData(new SFOneStepAlgorithmData("BumpMaps"),new SF2DCameraData(0.5f, 0.5f)));
			fishTexture.setNode("Fish");
			fishTexture.addOutputTexture(100, 100, SFImageFormat.ARGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			fishTexture.addOutputTexture(100, 100, SFImageFormat.ARGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("TransparentFishTextures", fishTexture);//not the same as Test0008
		
		SFDrawnRenderedTextureData fishTexture2=new SFDrawnRenderedTextureData();
			fishTexture2.setRenderer(new SFRendererData(new SFOneStepAlgorithmData("BumpMaps"),new SF2DCameraData(0.5f, 0.5f)));
			fishTexture2.setNode("Fish");
			fishTexture2.addOutputTexture(100, 100, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			fishTexture2.addOutputTexture(100, 100, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("FishTextures", fishTexture2);//not the same as Test0008	
			
		SFCurvedTubeFunctionData retrievedFunction = (SFCurvedTubeFunctionData) SFDataUtility
				.loadDataset(root, "test0001.sf");
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8, new SFSimpleTexCoordGeometryuvData(20,20), pipeline.getBumpMapPrimitive());
			library.put("BumpMappingsMushroom", geometry);
			
		SFObjectModelData tiledModel=new SFObjectModelData();
			tiledModel.placeGeometryAndScale("BumpMappingsMushroom", 0, -0.6f, 0, 2.4f);
			tiledModel.addMaterialProgram("BumpMappedMat");
			tiledModel.addTexture(0, 1, "TileTexture");
			tiledModel.addTexture(1, 0, "TileTexture");
			library.put("TileMushroom", tiledModel);

		SFDataUtility.saveDataset(root, "test0019.sf", library);
	}

	
	private SFFrameController spottedMushroomFrameController=new SFFrameController() {
		
		@Override
		public void select(int index) {
			if(textureSet[index]!=null){
				objectModel.getModel().getTextures().clear();
				objectModel.getModel().getTextures().add(new SFTextureReference(0, new SFTexture(textureSet[index], 1)));	
				objectModel.getModel().getTextures().add(new SFTextureReference(1, new SFTexture(textureSet[index], 0)));	
			}
		}
		
		@Override
		public String getName() {
			return "Material";
		}
		
		@Override
		public String[] getAlternatives() {
			return menuName;
		}
	};
}
