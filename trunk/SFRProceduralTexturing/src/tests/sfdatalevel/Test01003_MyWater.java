package tests.sfdatalevel;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.SFSimpleTexCoordGeometryuv;
import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.image.SFImageFormat;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.renderer.SFObjectModel;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
import shadow.renderer.data.utils.SFDataUtility;

/*Disegna un quadrato in cui vengono modulati il perlin noise e un materiale blu*/
public class Test01003_MyWater extends SFGenericTest {

	private final static String FILENAME = "MyWater";
		
		
	public static void main(String[] args) {
		execute(new Test01003_MyWater());
	}
		
	@Override
	public String getFilename() {		
		return FILENAME;
	}
		
	@Override
	public void buildTestData() {
		
		copyAssets("test0003", library);	
					
		//Setup della geometria
		SFQuadsSurfaceGeometryData geometryData=new SFQuadsSurfaceGeometryData();
		SFDataAsset<SFSurfaceFunction> rectangle = generateRectangle();
		geometryData.setup(rectangle, 8, 8,new SFSimpleTexCoordGeometryuvData(), "Triangle2PNTxO");
		library.put("Rectangle", geometryData);
			
		//Definizione del modello e degli shader
		
		SFObjectModelData objectModel=new SFObjectModelData();
		objectModel.setGeometry("Rectangle");
		objectModel.getMaterialComponent().addStructure("BasicMatColours",5);
		//objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.5f, 0, 2.5f));
		objectModel.getMaterialComponent().addTexture("PerlinTexture");
		objectModel.getTransformComponent().setProgram("BasicPNTx0Transform");
		objectModel.getMaterialComponent().setProgram("BasicTexturedMat");
		
		library.put("PerlinRectangle", objectModel);
		
		
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
		drawnTexture.setRenderer(new SFRendererData("BasicTest",new SF2DCameraData(0.5f, 0.5f)));
		drawnTexture.setNode("PerlinRectangle");
		drawnTexture.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		library.put("RectangleTexture", drawnTexture);
								
		SFDataUtility.saveDataset(root, "test0004.sf", library);
		SFDataUtility.saveXMLFile(root, "test0004", library);
		
		store(library);
	
	}
	
	
	public SFDataAsset<SFSurfaceFunction> generateRectangle() {
		
		SFRectangle2DFunctionData rectangle = new SFRectangle2DFunctionData(-0.5f, -0.5f, 1f, 1f);
		
		return rectangle;
	}
	
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();

		/*Sfrutta l'intero modello di SFViewer preso da Test_0005 (illuminazione, disegno delle normali, etc)*/
		viewNode("PerlinRectangle");
		
		
	}

}
