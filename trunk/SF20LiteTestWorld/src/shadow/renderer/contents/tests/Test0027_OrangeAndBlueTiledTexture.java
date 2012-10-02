package shadow.renderer.contents.tests;

import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFTextureDataToken;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.system.data.SFObjectsLibrary;

public class Test0027_OrangeAndBlueTiledTexture extends SFAbstractTest{

	private static final String FILENAME = "test0027";

	public static void main(String[] args) {
		execute(new Test0027_OrangeAndBlueTiledTexture());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		viewTextureSet("OBTilesTexture", 0);
	}
	
	@Override
	public void buildTestData() {
		
		SFViewerObjectsLibrary test0002Library=new SFViewerObjectsLibrary(root, "test0002.sf");
			library.put("BasicMatColours", test0002Library.getLibrary().retrieveDataset("BasicMatColours"));

		generateRectangle(library,0,0,1,1, 16 ,0);
		generateRectangle(library,-1,-1,1,1, 16  ,1);
		generateRectangle(library,-1,0,1,1, 10 ,2);
		generateRectangle(library,0,-1,1,1, 10 ,3);
		
		SFReferenceNodeData referenceNode=new SFReferenceNodeData();
			referenceNode.addNode("OBTilesModel0");
			referenceNode.addNode("OBTilesModel1");
			referenceNode.addNode("OBTilesModel2");
			referenceNode.addNode("OBTilesModel3");
		library.put("OBTilesDrawnTextureNode", referenceNode);
		
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			SF2DCameraData cameraData=new SF2DCameraData();
			cameraData.setDimensions(0.5f, 0.5f);
		SFRendererData renderer=new SFRendererData("BasicColor",cameraData);
		drawnTexture.setRenderer(renderer);
		drawnTexture.setNode("OBTilesDrawnTextureNode");
		drawnTexture.addOutputTexture(new SFTextureDataToken(20, 20, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT));
		library.put("OBTilesTexture", drawnTexture);
			
		store(library);
	}


	private static void generateRectangle(SFObjectsLibrary library,float x,float y,float w,float h,int materialIndex,int index) {

		SFQuadsSurfaceGeometryData rectangle1=new SFQuadsSurfaceGeometryData();
		SFRectangle2DFunctionData rectangle2d=new SFRectangle2DFunctionData(x, y, w, h);
		rectangle1.setNuNv(2, 2);
		rectangle1.getSurfaceFunction().setDataset(rectangle2d);
		rectangle1.getPrimitive().setString("Triangle2PN");
			library.put("OBTilesRectangle"+index, rectangle1);
		
		SFObjectModelData model1=new SFObjectModelData();
				//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
				model1.getMaterialComponent().addStructure("BasicMatColours", materialIndex);
				model1.getMaterialComponent().setProgram("BasicMat");
				model1.getTransformComponent().setProgram("BasicPNTransform");
				model1.getRootGeometryReference().setReference("OBTilesRectangle"+index);
			//we insert the material in the library
			library.put("OBTilesModel"+index, model1);
	}

}
