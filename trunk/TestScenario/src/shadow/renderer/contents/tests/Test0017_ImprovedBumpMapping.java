package shadow.renderer.contents.tests;

import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFObjectsLibrary;

public class Test0017_ImprovedBumpMapping {

	private static final String root = "testsData";

	public static void main(String[] args) {

		// Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline pipeline=new CommonPipeline();

		SFViewerObjectsLibrary test0014Library=new SFViewerObjectsLibrary(root, "test0014.sf");
			library.put("PerlinTexture", test0014Library.getLibrary().retrieveDataset("PerlinTexture"));
			library.put("PerlinTexture2", test0014Library.getLibrary().retrieveDataset("PerlinTexture2"));
			library.put("PebblesModel", test0014Library.getLibrary().retrieveDataset("PebblesModel"));
			library.put("FullScreenRectangle", test0014Library.getLibrary().retrieveDataset("FullScreenRectangle"));
			library.put("PebblesTextureModel", test0014Library.getLibrary().retrieveDataset("PebblesTextureModel"));
			library.put("PebblesGround", test0014Library.getLibrary().retrieveDataset("PebblesGround"));
			library.put("PebblesTextures", test0014Library.getLibrary().retrieveDataset("PebblesTextures"));
		
		SFCurvedTubeFunctionData retrievedFunction = (SFCurvedTubeFunctionData) SFDataUtility
				.loadDataset(root, "test0001.sf");
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8, new SFSimpleTexCoordGeometryuvData(4,4), pipeline.getBumpMapPrimitive());
			library.put("BumpMappingsMushroom", geometry);
			
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.placeGeometryAndScale("BumpMappingsMushroom", 0, -0.6f, 0, 2.4f);
			objectModel.addMaterialProgram("ImprovedBumpMappedMat");
			objectModel.addTexture(0, 1, "PebblesTextures");
			objectModel.addTexture(1, 0, "PebblesTextures");
			library.put("StoneMushroom", objectModel);
		
		SFDataUtility.saveDataset(root, "test0017.sf", library);
		
		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				root, "test0017.sf"));


		SFDataCenter.getDataCenter().makeDatasetAvailable("StoneMushroom",
				new SFDataCenterListener<SFObjectModelData>() {
					@Override
					public void onDatasetAvailable(String name,SFObjectModelData dataset) {
						SFViewer viewer=SFViewer.generateFrame(dataset.getResource());
						viewer.setRotateModel(true, 0.03f);
					}
				});	
	}
}
