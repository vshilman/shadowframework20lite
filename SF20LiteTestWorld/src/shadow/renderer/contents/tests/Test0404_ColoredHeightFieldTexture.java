package shadow.renderer.contents.tests;

import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.renderer.SFObjectModel;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

/**
 * 
 * @author Alessandro Martinelli
 */
public class Test0404_ColoredHeightFieldTexture extends SFAbstractTest{

	private static final String FILENAME="test0404";
	
	/***/
	public static void main(String[] args) {
		execute(new Test0404_ColoredHeightFieldTexture());
	}
	

	@Override
	public String getFilename() {
		return FILENAME;
	}
	

	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("GraniteMushroom", new SFDataCenterListener<SFObjectModelData>() {
			@Override
			public void onDatasetAvailable(String name, SFObjectModelData dataset) {
				SFObjectModel model=(SFObjectModel)dataset.getResource();
				SFViewer.generateFrame(model,SFViewer.getLightStepController(),
						SFViewer.getZoomController(),SFViewer.getRotationController(),SFViewer.getLightStepController());
			}
		});
	}
	
	@Override
	public void buildTestData() {

		String xmlFileName="Test0403InputFilteredTexturesExamples";

		compileAndLoadXmlFile(xmlFileName);
		
		SFCurvedTubeFunctionData retrievedFunction = loadDataset("test0001");
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8, new SFSimpleTexCoordGeometryuvData(4,4), "Triangle2BumpMap");
			library.put("BumpMappingsMushroom", geometry);
			
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.setGeometry("BumpMappingsMushroom");
			objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.6f, 0, 2.4f));
			objectModel.getTransformComponent().setProgram("BasicBumpMapTransform");
			objectModel.getMaterialComponent().setProgram("ImprovedBumpMappedMat");
			
			objectModel.getMaterialComponent().addTexture( 0, "ReddishGrayAndBrightPerlinNoise");
			//objectModel.addTexture(0, 0, "PerlinTexture03+04Colors");
			objectModel.getMaterialComponent().addTexture( 0, "PerlinTextureBumpMap");
			//objectModel.addTexture(1, 0, "PerlinTexture03x04BumpMap");
			
			library.put("GraniteMushroom", objectModel);
		
		store(library);
	}
	
}
