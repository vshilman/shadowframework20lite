package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFRadialSurfaceFunctionData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexListData16;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFObjectModel;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.data.transforms.SFRigidTransformData;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
import shadow.renderer.viewer.SFFrameController;
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
 * Objective: TODO 
 * 
 * @author Alessandro Martinelli
 */
public class Test0019_SpottedMushRoom extends SFAbstractTest{

	private static final String FILENAME = "test0019";

	public static void main(String[] args) {
		execute(new Test0019_SpottedMushRoom());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	private SFObjectModel objectModel;
	private String[] menuName={"Tiled Mushroom","Spotted Mushroom","Stoned Mushroom","Lot of Fishes","Lot of Fishes (Transparency)"};
	private SFRenderedTexturesSet[] textureSet={null,null,null,null,null};
	
	@Override
	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("TileMushroom", new SFDataCenterListener<SFDataAsset<SFObjectModel>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFObjectModel> dataset) {
				objectModel=dataset.getResource();
				SFViewer.generateFrame(objectModel,spottedMushroomFrameController,
						SFViewer.getLightStepController(),SFViewer.getRotationController(),
						SFViewer.getWireframeController(),SFViewer.getZoomController());
			}
		});
		
		textureSet[0]=getAlreadyAvailableDatasetResource("TileTexture");
		textureSet[1]=getAlreadyAvailableDatasetResource("SpotTexture");
		textureSet[2]=getAlreadyAvailableDatasetResource("PebblesTextures");
		textureSet[3]=getAlreadyAvailableDatasetResource("FishTextures");
		textureSet[4]=getAlreadyAvailableDatasetResource("TransparentFishTextures");
		
	}

	@Override
	public void buildTestData() {
	
		copyAssets("test0014", library,  "OriginalNoise", "PerlinTexture",
				"PebblesModel","PebblesTextureModel",
				"PebblesTextures");

		copyAssets("test0018", library, "PerlinTexture2",
				"FullScreenRectangle","PebblesGround",
				"Tile","BasicMatColours",
				"TileModel","TileTextureModel",
				"TileTexture");
		
		copyAssets("test0008", library, "FishGeometry");
		
		SFRadialSurfaceFunctionData spotFunction = new SFRadialSurfaceFunctionData();
			spotFunction.setFirstCurve(new SFBasisSplineData(new SFVertexListData16(),true,
					new SFVertex3f(0.8f, 0.8f, 0), new SFVertex3f(-0.8f, 0.8f, 0),
					new SFVertex3f(-0.7f, -0.8f, 0), new SFVertex3f(0.8f, -0.7f, 0)));
			spotFunction.setSecondCurve(new SFBasisSplineData(new SFVertexListData16(),
					new SFVertex3f(0.0f, 0, -0.3f), new SFVertex3f(0.7f, 0.0f, -0.3f),
					new SFVertex3f(0.7f, 0.0f, 0)));

		SFQuadsSurfaceGeometryData spotGeometry=new SFQuadsSurfaceGeometryData();
			spotGeometry.setup(spotFunction, 8, 8, "Triangle2PN");
			library.put("Spot", spotGeometry);

		SFObjectModelData spotModel=new SFObjectModelData();
			spotModel.setGeometry("Spot");
			spotModel.getMaterialComponent().addStructure("BasicMatColours", 10);
			spotModel.getTransformComponent().setProgram("BasicPNTransform");
			spotModel.getMaterialComponent().setProgram("BasicMat");
			library.put("SpotModel", spotModel);
		
		SFReferenceNodeData referenceNode=new SFReferenceNodeData();
			referenceNode.addNode("PebblesGround");
			referenceNode.addNode("SpotModel");
			library.put("SpotTextureModel", referenceNode);
		
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			drawnTexture.setRenderer(new SFRendererData("BumpMaps",new SF2DCameraData(0.5f, 0.5f)));
			drawnTexture.setNode("SpotTextureModel");
			drawnTexture.addOutputTexture(100, 100, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			drawnTexture.addOutputTexture(100, 100, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("SpotTexture", drawnTexture);	
			
		SFObjectModelData fishModel=new SFObjectModelData();
			fishModel.setTransform(new SFRigidTransformData(0,0,0,1.5f,SFMatrix3f.getRotationZ(0.25f*(float)(Math.PI))));
			fishModel.getMaterialComponent().addStructure("BasicMatColours",4);
			fishModel.getTransformComponent().setProgram("BasicPNTransform");
			fishModel.getMaterialComponent().setProgram("BasicMat");
			fishModel.setGeometry("FishGeometry");
			library.put("Fish", fishModel);//not the same as Test0008
			
		SFDrawnRenderedTextureData fishTexture=new SFDrawnRenderedTextureData();
			fishTexture.setRenderer(new SFRendererData("BumpMaps",new SF2DCameraData(0.5f, 0.5f)));
			fishTexture.setNode("Fish");
			fishTexture.addOutputTexture(100, 100, SFImageFormat.ARGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			fishTexture.addOutputTexture(100, 100, SFImageFormat.ARGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("TransparentFishTextures", fishTexture);//not the same as Test0008
		
		SFDrawnRenderedTextureData fishTexture2=new SFDrawnRenderedTextureData();
			fishTexture2.setRenderer(new SFRendererData("BumpMaps",new SF2DCameraData(0.5f, 0.5f)));
			fishTexture2.setNode("Fish");
			fishTexture2.addOutputTexture(100, 100, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			fishTexture2.addOutputTexture(100, 100, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("FishTextures", fishTexture2);//not the same as Test0008	
			
		SFCurvedTubeFunctionData retrievedFunction = loadDataset("test0001");
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8, new SFSimpleTexCoordGeometryuvData(20,20), "Triangle2BumpMap");
			library.put("BumpMappingsMushroom", geometry);
			
		SFObjectModelData tiledModel=new SFObjectModelData();
			tiledModel.setGeometry("BumpMappingsMushroom");
			tiledModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.6f, 0, 2.4f));
			tiledModel.getTransformComponent().setProgram("BasicBumpMapTransform");
			tiledModel.getMaterialComponent().setProgram("BumpMappedMat");
			tiledModel.getMaterialComponent().addTexture( 1, "TileTexture");
			tiledModel.getMaterialComponent().addTexture( 0, "TileTexture");
			library.put("TileMushroom", tiledModel);

		store(library);
	}

	
	private SFFrameController spottedMushroomFrameController=new SFFrameController() {
		
		@Override
		public void select(int index) {
			if(textureSet[index]!=null){
				objectModel.getModel().getMaterialComponent().getTextures().clear();
				objectModel.getModel().getMaterialComponent().getTextures().add( new SFTexture(textureSet[index], 1));	
				objectModel.getModel().getMaterialComponent().getTextures().add( new SFTexture(textureSet[index], 0));	
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
