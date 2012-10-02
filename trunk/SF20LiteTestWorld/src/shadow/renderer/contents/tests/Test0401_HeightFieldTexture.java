package shadow.renderer.contents.tests;

import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.image.data.SFFilteredRenderedTextureData;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

/**
 * 
 * @author Alessandro Martinelli
 */
public class Test0401_HeightFieldTexture extends SFAbstractTest{

	private static final String FILENAME="test0401";
	
	/***/
	public static void main(String[] args) {
		execute(new Test0401_HeightFieldTexture());
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
				SFViewer.generateFrame(model,CommonMaterial.generateColoursController(model),SFViewer.getLightStepController(),
						SFViewer.getZoomController(),SFViewer.getRotationController(),SFViewer.getLightStepController());
			}
		});
	}
	
	@Override
	public void buildTestData() {
		
		String xmlFileName="Test0402InputSoMuchNoise";

		compileAndLoadXmlFile(xmlFileName);
		
		String textureName="PerlinTexture63";
			
		float[][] values={{0.005f},{50}};
		SFDataAsset<SFStructureArray> sizes = CommonMaterial.generateStructureDataData("NormalFromHeightField","TextureFilterData",values);
		library.put("BasicSizes", sizes);
		
		SFQuadsSurfaceGeometryData rectangle=new SFQuadsSurfaceGeometryData();
			rectangle.setup(new SFRectangle2DFunctionData(-1, -1, 2, 2), 2, 2, "Triangle2PN");
			library.put("FullScreenRectangle", rectangle);
			
		SFObjectModelData whiteTessel=new SFObjectModelData();
			whiteTessel.getRootGeometryReference().setReference("FullScreenRectangle");
			whiteTessel.getTransformComponent().setProgram("BasicPNTransform");
			whiteTessel.getMaterialComponent().setProgram("WhiteMat");
			library.put("WhiteTessel", whiteTessel);
			
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			SFRendererData renderer=new SFRendererData("BasicColor",new SF2DCameraData(0.5f, 0.5f));
			drawnTexture.setRenderer(renderer);
			drawnTexture.setNode("WhiteTessel");
			drawnTexture.addOutputTexture(2, 2, SFImageFormat.GRAY8, Filter.NEAREST, WrapMode.REPEAT, WrapMode.REPEAT);
			library.put("WhiteTexture", drawnTexture);
		
		SFFilteredRenderedTextureData renderedTextureData=new SFFilteredRenderedTextureData();//Asset
		renderedTextureData.getLightComponent().setProgram("DrawNormals");
		renderedTextureData.getMaterialComponent().setProgram("NormalFromHeightField");
		renderedTextureData.getMaterialComponent().addTexture( 0, textureName);
		renderedTextureData.addOutputTexture(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		renderedTextureData.getMaterialComponent().addStructure("BasicSizes", 0);
		//we insert the material in the library
		library.put("PerlinTextureBumpMap", renderedTextureData);
		
		SFCurvedTubeFunctionData retrievedFunction = loadDataset("test0001");
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8, new SFSimpleTexCoordGeometryuvData(4,4), "Triangle2BumpMap");
			library.put("BumpMappingsMushroom", geometry);
			
		float[][] colours = CommonMaterial.generateColours();
		SFDataAsset<SFStructureArray> material = CommonMaterial.generateMaterialData(colours);
			library.put("BasicMatColours", material);	
			
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.setGeometry("BumpMappingsMushroom");
			objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.6f, 0, 2.4f));
			objectModel.getTransformComponent().setProgram("BasicBumpMapTransform");
			objectModel.getMaterialComponent().setProgram("ImprovedBumpMappedMat");
			objectModel.getMaterialComponent().addTexture(0, "WhiteTexture");
			objectModel.getMaterialComponent().addTexture(0, "PerlinTextureBumpMap");
//			SFStructureReferenceData structureReference=new SFStructureReferenceData();
//				structureReference.setTableData("BasicMatColours");
//				structureReference.setRefIndex(0);
//			objectModel.addMaterialsStructures(structureReference);
			library.put("GraniteMushroom", objectModel);
		
		store(library);
	}
	
}
