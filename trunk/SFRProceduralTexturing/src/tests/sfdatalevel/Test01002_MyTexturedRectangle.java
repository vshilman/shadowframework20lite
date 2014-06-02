package tests.sfdatalevel;

import shadow.image.SFRenderedTexturesSet;
import shadow.nodes.SFObjectModel;
import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;
import shadow.system.data.SFDataCenter;
import shadow.tests.tools.SFViewer;


/* -disegna un quadrato con la texture Perlin */

public class Test01002_MyTexturedRectangle extends MainPTTest {

	private static final String FILENAME="test0002_mytexturedrectangle";

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
	
//	private final static String FILENAME = "MyTexturedRectangle";
//	
//	public static void main(String[] args) {
//		execute(new Test01002_MyTexturedRectangle());
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
//		copyAssets("test0005", library);
//		
//
//	store(library);
//		
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
//		
//		SFRenderedTexturesSet resource = getAlreadyAvailableDatasetResource("PerlinTexture");
//		SFDataAsset<SFSurfaceFunction> retrievedGeometryFunction = generateRectangle();
//		SFSurfaceFunction geometryResource=retrievedGeometryFunction.getResource();
//		
//		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PNTxO");
//		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry(primitive.getConstructionPrimitive(), 8, 8,false,false);
//		SFParametrizedGeometry geometry=new SFParametrizedGeometry(primitive,gridGeometry);
//		geometry.setFunction(SFPrimitiveBlock.NORMAL, geometryResource);
//		geometry.setFunction(SFPrimitiveBlock.POSITION, geometryResource);
//		geometry.setFunction(SFPrimitiveBlock.TXO, new SFSimpleTexCoordGeometryuv(1,1));
//		geometry.init();
//	
//		
//		SFObjectModel objmodel=new SFObjectModel();
//		
//		
//			objmodel.getModel().setRootGeometry(geometry);
//			objmodel.getModel().getMaterialComponent().getTextures().add( new SFTexture(resource,0));
//			
//			objmodel.getModel().getTransformComponent().setProgram("BasicPNTx0Transform");
//			objmodel.getModel().getMaterialComponent().setProgram("BasicTexturedMat");
//
//		
//		
//		SFViewer.generateFrame(objmodel, new SFProgramModuleStructures("DrawTexture"), CommonTextures.generateTextureSelectionController(objmodel.getModel().getMaterialComponent().getTextures().get(0), 1));
//
//		
//		
//	}
	
}
