package shadow.renderer.contents.tests;

import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFStructureArrayDataUnit8;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
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
 * Objective: verify the capability to store an Object Model, load it and show
 * it on a Viewer. This Test is different from Test0001, because we are
 * storing a whole SFObjectModel and not simply its geometry.
 * 
 * TODO
 * 
 * @author Alessandro Martinelli
 */
public class Test0002_MushroomModel extends SFAbstractTest{

	private static final String FILENAME="test0002";
	
	public static void main(String[] args) {
		execute(new Test0002_MushroomModel());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("RedMushroom", new SFDataCenterListener<SFObjectModelData>() {
			@Override
			public void onDatasetAvailable(String name, SFObjectModelData dataset) {
				SFObjectModel model=(SFObjectModel)dataset.getResource();
				SFViewer.generateFrame(model,CommonMaterial.generateColoursController(model),SFViewer.getLightStepController());
			}
		});
	}

	public void buildTestData() {
		// 1) Create a Surface Function (Mushroom) and edit its Data.
		// At Editing time we need to work directly on Data version of each
		// Asset
		//Geometry Function for this Object Model is retrieved from Test0001
		SFCurvedTubeFunctionData retrievedFunction = loadDataset("test0001");
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8,"Triangle2PN");
			library.put("Mushroom", geometry);
			
		float[][] colours = CommonMaterial.generateColours();
		SFStructureArrayDataUnit8 material = CommonMaterial.generateMaterialData(colours);
			library.put("BasicMatColours", material);
		
		String geometryName="Mushroom";	
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.setGeometry(geometryName);
			objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.5f, 0, 2.5f));
				//Note: material Data and material program are different, because the same material data can be used with different programs
			int colorIndex=2;
			objectModel.getTransformComponent().setProgram("BasicPNTransform");
			objectModel.getMaterialComponent().setProgram("BasicMat");
			objectModel.getMaterialComponent().addStructure("BasicMatColours", colorIndex);
			//we insert the material in the library
			library.put("RedMushroom", objectModel);
			

		store(library);
	}

}
