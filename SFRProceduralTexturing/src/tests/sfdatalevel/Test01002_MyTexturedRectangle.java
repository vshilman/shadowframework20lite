package tests.sfdatalevel;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.SFSimpleTexCoordGeometryuv;
import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.nodes.SFObjectModel;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataAsset;
import shadow.tests.tools.SFGenericTest;


/* -disegna un quadrato con la texture Perlin */

public class Test01002_MyTexturedRectangle extends SFGenericTest {

	private final static String FILENAME = "MyTexturedRectangle";
	
	
	public static void main(String[] args) {
		execute(new Test01002_MyTexturedRectangle());
	}
	
	@Override
	public String getFilename() {
		
		return FILENAME;
	}
	
	@Override
	public void buildTestData() {
		copyAssets("test0002", library, "BasicMatColours");
		copyAssets("test0005", library);
		

	store(library);
		
		
	}
	
	
	public SFDataAsset<SFSurfaceFunction> generateRectangle() {
		
		SFRectangle2DFunctionData rectangle = new SFRectangle2DFunctionData(-0.5f, -0.5f, 1f, 1f);
		
		return rectangle;
	}
	
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		
		SFRenderedTexturesSet resource = getAlreadyAvailableDatasetResource("PerlinTexture");
		SFDataAsset<SFSurfaceFunction> retrievedGeometryFunction = generateRectangle();
		SFSurfaceFunction geometryResource=retrievedGeometryFunction.getResource();
		
	
		
		
	
		
		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PNTxO");
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry(primitive.getConstructionPrimitive(), 8, 8,false,false);
		SFParametrizedGeometry geometry=new SFParametrizedGeometry(primitive,gridGeometry);
		geometry.setFunction(SFPrimitiveBlock.NORMAL, geometryResource);
		geometry.setFunction(SFPrimitiveBlock.POSITION, geometryResource);
		geometry.setFunction(SFPrimitiveBlock.TXO, new SFSimpleTexCoordGeometryuv(1,1));
		geometry.init();
	
		
		
		
		
		SFObjectModel objmodel=new SFObjectModel();
		
		
			objmodel.getModel().setRootGeometry(geometry);
			objmodel.getModel().getMaterialComponent().getTextures().add( new SFTexture(resource,0));
			
			objmodel.getModel().getTransformComponent().setProgram("BasicPNTx0Transform");
			objmodel.getModel().getMaterialComponent().setProgram("BasicTexturedMat");

		
		
		SFViewer.generateFrame(objmodel, new SFProgramModuleStructures("DrawTexture"), CommonTextures.generateTextureSelectionController(objmodel.getModel().getMaterialComponent().getTextures().get(0), 1));

		
		
	}
	
}
