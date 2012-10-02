package shadow.renderer.contents.tests;

import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.viewer.SFFrameController;
import shadow.renderer.viewer.SFViewer;

public class Test0250_0230Commons {

	private SFAbstractTest test;

	private SFReferenceNode node;

	private SFObjectModel objectModel;
	private String[] menuName={"OBTilesTexture","Tiles","Spots","Stones"};
	private SFRenderedTexturesSet[] textureSet={null,null,null,null};
	private int selectedTextureIndex=0;

	private String[] modelsNames={"Plain","Textured","BumpMapped"};
	private SFObjectModel[] objectModels={null,null,null};
	private int selectedObjectIndex=0;
	
	public Test0250_0230Commons(SFAbstractTest test) {
		super();
		this.test = test;
	}
	
	
	public void loadViewer(String plainModelName,String textureModelName,String bumpMappedModelName){
		objectModel = test.getAlreadyAvailableDatasetResource(plainModelName);
		
		node=new SFReferenceNode();
		node.addNode(objectModel);

		objectModels[0]=test.getAlreadyAvailableDatasetResource(plainModelName);
		objectModels[1]=test.getAlreadyAvailableDatasetResource(textureModelName);
		objectModels[2]=test.getAlreadyAvailableDatasetResource(bumpMappedModelName);

		textureSet[0]=test.getAlreadyAvailableDatasetResource("OBTilesTexture");
		textureSet[1]=test.getAlreadyAvailableDatasetResource("TileTexture");
		textureSet[2]=test.getAlreadyAvailableDatasetResource("SpotTexture");
		textureSet[3]=test.getAlreadyAvailableDatasetResource("PebblesTextures");
		
		SFViewer.generateFrame(node,modelController,modelTextureController,
				CommonMaterial.generateColoursController(objectModels[0]),
				SFViewer.getWireframeController(),SFViewer.getLightStepController(),SFViewer.getZoomController(),
				SFViewer.getRotationController());
	}

	public void loadTextures(){

		test.copyAssets("test0002", test.library,
				"BasicMatColours");
		
		test.copyAssets("test0019", test.library,
				"FullScreenRectangle",
				"PebblesGround",
				"PebblesModel",
				"PebblesTextureModel",
				"OriginalNoise",
				"PebblesTextures",
				"PerlinTexture",
				"PerlinTexture2",
				"Spot",
				"SpotModel",
				"SpotTexture",
				"SpotTextureModel",
				"Tile",
				"TileModel",
				"TileTexture",
				"TileTextureModel");

		test.copyAssets("test0027", test.library,
				"OBTilesDrawnTextureNode",
				"OBTilesModel0",
				"OBTilesModel1",
				"OBTilesModel2",
				"OBTilesModel3",
				"OBTilesRectangle0",
				"OBTilesRectangle1",
				"OBTilesRectangle2",
				"OBTilesRectangle3",
				"OBTilesTexture");
	}
	

	private SFFrameController modelController=new SFFrameController() {
		
		@Override
		public void select(int index) {
			if(index!=0)
				modelTextureController.select(selectedTextureIndex);
			node.removeNode(objectModel);
			objectModel=objectModels[index];
			node.addNode(objectModel);
			selectedObjectIndex=index;
		}
		
		@Override
		public String getName() {
			return "Model Version";
		}
		
		@Override
		public String[] getAlternatives() {
			return modelsNames;
		}
	};
	
	private SFFrameController modelTextureController=new SFFrameController() {
		
		@Override
		public void select(int index) {
			if(selectedObjectIndex==0)
				return;
			if(selectedObjectIndex==2 && index==0)
				return; //ObTilesTexture is not compatible with BumpMapped Models
			selectedTextureIndex=index;
			if(textureSet[index]!=null){
				objectModel.getModel().getMaterialComponent().getTextures().clear();
				if(selectedObjectIndex==2){
					objectModel.getModel().getMaterialComponent().getTextures().add(new SFTexture(textureSet[index], 1));
					objectModel.getModel().getMaterialComponent().getTextures().add(new SFTexture(textureSet[index], 0));	
				}else{
					if(index==0)
						objectModel.getModel().getMaterialComponent().getTextures().add(0, new SFTexture(textureSet[index], 0));
					else
						objectModel.getModel().getMaterialComponent().getTextures().add(0, new SFTexture(textureSet[index], 1));
				}
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
