package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFStructureArrayDataUnit8;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
import shadow.renderer.viewer.SFFrameController;
import shadow.renderer.viewer.SFViewer;

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
public class Test0701_ChangeGeometry extends SFAbstractTest{

	private static final String FILENAME="test0701";
	
	
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
		execute(new Test0701_ChangeGeometry());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		SFGeometry[] geometries=new SFGeometry[2];
		geometries[0]=getAlreadyAvailableDatasetResource("Mushroom");
		geometries[1]=getAlreadyAvailableDatasetResource("Mushroom2");
		
		SFObjectModel model=getAlreadyAvailableDatasetResource("RedMushroom");
		
		GeometryController controller=new GeometryController(geometries, model,"Quads Mushroom","Triangle 2 Mushroom");
		
		SFViewer.generateFrame(model,CommonMaterial.generateColoursController(model),SFViewer.getLightStepController(),controller);
	}

	public void buildTestData() {
		
		SFCurvedTubeFunctionData retrievedFunction = loadDataset("test0001");
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8,"QuadPN");
			library.put("Mushroom", geometry);

		geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8,"Triangle2PN");
			library.put("Mushroom2", geometry);
			
		float[][] colours = CommonMaterial.generateColours();
		SFStructureArrayDataUnit8 material = CommonMaterial.generateMaterialData(colours);
			library.put("BasicMatColours", material);
		
		String geometryName="Mushroom";	
		
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.setGeometry(geometryName);
			objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.5f, 0, 2.5f));
			
			objectModel.getTransformComponent().setProgram("BasicPNTransform");
			objectModel.getMaterialComponent().setProgram("RedMat");
			
			library.put("RedMushroom", objectModel);
			

		store(library);
	}

}
