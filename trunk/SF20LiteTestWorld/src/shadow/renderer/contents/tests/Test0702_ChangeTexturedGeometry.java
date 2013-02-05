package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.geometries.data.SFMeshGeometryData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.math.SFValuenf;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.renderer.SFModel;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFPrimitiveArrayData;
import shadow.renderer.data.SFStructureArrayDataUnit8;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.viewer.SFFrameController;
import shadow.renderer.viewer.SFViewer;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;

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
 * Objective: verify the capability to store an Object Model, load it and show
 * it on a Viewer. This Test is different from Test0001, because we are
 * storing a whole SFObjectModel and not simply its geometry.
 * 
 * TODO
 * 
 * @author Alessandro Martinelli
 */
public class Test0702_ChangeTexturedGeometry extends SFAbstractTest{

	private static final String FILENAME="test0702";
	
	
	private static class GeometryController implements SFFrameController{

		private SFGeometry[] geometries=new SFGeometry[2];
		private SFObjectModel model;
		private String[] names;
		private String name="Geometry";
		
		public GeometryController(SFGeometry[] geometries, SFObjectModel model, String... names) {
			super();
			this.geometries = geometries;
			this.model = model;
			this.names=names;
		}

		@Override
		public String[] getAlternatives() {
			return names;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		@Override
		public void select(int index) throws ArrayIndexOutOfBoundsException{
			model.getModel().setRootGeometry(geometries[index]);
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public static void main(String[] args) {
		execute(new Test0702_ChangeTexturedGeometry());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		SFGeometry[] geometries=new SFGeometry[2];
		geometries[0]=getAlreadyAvailableDatasetResource("CubeTx");
		geometries[1]=getAlreadyAvailableDatasetResource("TexturedMushroom");
		
		SFObjectModel model=getAlreadyAvailableDatasetResource("TexturedObject");
		
		GeometryController controller=new GeometryController(geometries, model,"Quads Mushroom","Triangle 2 Mushroom");
		
		SFViewer.generateFrame(model,CommonMaterial.generateColoursController(model),SFViewer.getLightStepController(), controller);
		
		
	}

	public void buildTestData() {
		
//		copyAssets("test0003", library);
//		
//		SFCurvedTubeFunctionData retrievedFunction = loadDataset("test0001");
//			
//		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
//			geometry.setup(retrievedFunction, 8, 8, new SFSimpleTexCoordGeometryuvData(), "Triangle2PNTxO");
//			library.put("TexturedMushroom", geometry);
//		
//		SFObjectModelData objectModel=new SFObjectModelData();
//			objectModel.setGeometry("TexturedMushroom");
//			objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.5f, 0, 2.5f));
//			objectModel.getMaterialComponent().addTexture("PerlinTexture");
//			objectModel.getTransformComponent().setProgram("BasicPNTx0Transform");
//			objectModel.getMaterialComponent().setProgram("TexturedMat");
//		library.put("PerlinMushroom", objectModel);

		//copyAssets("test0702", library);
		compileAndLoadXmlFile("test0702");
		
		SFDataUtility.saveDataset(root, "test0702.sf", library);
		
		SFDataUtility.saveXMLFile(root, "test0702", library);
		
		
	}

}
