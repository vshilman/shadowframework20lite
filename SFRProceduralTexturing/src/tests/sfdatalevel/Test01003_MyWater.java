package tests.sfdatalevel;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;

/*Disegna un quadrato in cui vengono modulati il perlin noise e un materiale blu*/
public class Test01003_MyWater extends MainPTTest {

	private static final String FILENAME="test0003_mywater";

	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test01002_MyTexturedRectangle());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		//SFRenderedTexturesSet texture=getAlreadyAvailableDatasetResource("BitmapTexture0001");
		//SFObjectModel model=(SFObjectModel)SFDataCenter.getDataCenter().makeDatasetAvailable("RectangleModel").getResource();
		//SFViewer.generateFrame(model,SFViewer.getLightStepController());
		
		SFDrawable drawable = getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");
		SFDrawableFrame frame = new SFDrawableFrame("Textured Rectangle", 800, 800, drawable);
		frame.setVisible(true);
	}
	
//	private final static String FILENAME = "MyWater";
//		
//		
//	public static void main(String[] args) {
//		execute(new Test01003_MyWater());
//	}
//		
//	@Override
//	public String getFilename() {		
//		return FILENAME;
//	}
//		
//	@Override
//	public void buildTestData() {
//		
//		copyAssets("test0003", library);	
//					
//		//Setup della geometria
//		SFQuadsSurfaceGeometryData geometryData=new SFQuadsSurfaceGeometryData();
//		SFDataAsset<SFSurfaceFunction> rectangle = generateRectangle();
//		geometryData.setup(rectangle, 8, 8,new SFSimpleTexCoordGeometryuvData(), "Triangle2PNTxO");
//		library.put("Rectangle", geometryData);
//			
//		//Definizione del modello e degli shader
//		
//		SFObjectModelData objectModel=new SFObjectModelData();
//		objectModel.setGeometry("Rectangle");
//		objectModel.getMaterialComponent().addStructure("BasicMatColours",5);
//		//objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.5f, 0, 2.5f));
//		objectModel.getMaterialComponent().addTexture("PerlinTexture");
//		objectModel.getTransformComponent().setProgram("BasicPNTx0Transform");
//		objectModel.getMaterialComponent().setProgram("BasicTexturedMat");
//		
//		library.put("PerlinRectangle", objectModel);
//		
//		
//		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
//		drawnTexture.setRenderer(new SFRendererData("BasicTest",new SF2DCameraData(0.5f, 0.5f)));
//		drawnTexture.setNode("PerlinRectangle");
//		drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
//		library.put("RectangleTexture", drawnTexture);
//								
//		SFDataUtility.saveDataset(root, "test0004.sf", library);
//		SFDataUtility.saveXMLFile(root, "test0004", library);
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
//		/*Sfrutta l'intero modello di SFViewer preso da Test_0005 (illuminazione, disegno delle normali, etc)*/
//		viewNode("PerlinRectangle");
//		
//		
//	}

}
