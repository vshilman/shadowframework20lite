package tests.sfdatalevel;

import shadow.nodes.SFObjectModel;
import shadow.pipeline.tools.SFCommonPipeline;
import shadow.system.data.SFDataCenter;
import shadow.tests.tools.SFViewer;

/* -disegna un quadrato */
public class Test01001_MyRectangle extends MainPTTest {

	private static final String FILENAME="test0001_myrectangle";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test01001_MyRectangle());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFObjectModel model=(SFObjectModel)SFDataCenter.getDataCenter().makeDatasetAvailable("RectangleModel").getResource();
		SFViewer.generateFrame(model,generateColoursController(model),SFViewer.getLightStepController());
	}
	
//	private final static String FILENAME = "MyRectangle";
//	
//	public static void main(String[] args) {
//		execute(new Test0001_MyRectangle());
//	}
//	
//	@Override
//	public String getFilename() {
//		
//		return FILENAME;
//	}
//	
//	@Override
//	public void buildTestData() {
//		copyAssets("test0002", library, "BasicMatColours");
//		
//		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
//		SFDataAsset<SFSurfaceFunction> rectangle = generateRectangle();
//		
//		geometry.setup(rectangle, 8, 8, "Triangle2PN");
//		library.put("Rectangle", geometry);
//		
//		SFObjectModelData rectangleModel=new SFObjectModelData();
//		rectangleModel.setGeometry("Rectangle");
//		rectangleModel.getMaterialComponent().addStructure("BasicMatColours",1);
//		rectangleModel.getTransformComponent().setProgram("BasicPNTransform");
//		rectangleModel.getMaterialComponent().setProgram("BasicMat");
//		library.put("RectangleModel", rectangleModel);
//		
//		SFReferenceNodeData referenceNode=new SFReferenceNodeData();
//		referenceNode.addNode("RectangleModel");
//		library.put("RectangleTextureModel", referenceNode);
//		
//		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
//		drawnTexture.setRenderer(new SFRendererData("BasicTest",new SF2DCameraData(0.5f, 0.5f)));
//		drawnTexture.setNode("RectangleModel");
//		drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
//
//		library.put("RectangleTexture", drawnTexture);
//
//		store(library);
//		
//	}
//	
//	
//	public SFDataAsset<SFSurfaceFunction> generateRectangle() {
//		
//		SFRectangle2DFunctionData rectangle = new SFRectangle2DFunctionData(-0.5f, -0.5f, 1f, 1f);
//		
//		return rectangle;
//	}
//	
//	
//	@Override
//	public void viewTestData() {
//		
//		loadLibraryAsDataCenter();
//
//		viewTextureSet("RectangleTexture", 0);
//		
//		
//	}
	
}
