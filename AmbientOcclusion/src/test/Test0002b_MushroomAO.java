package test;

import java.util.ArrayList;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.data.SFMeshGeometryData;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.SFStructureReference;


import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import util.Triangle;
import viewer.SFViewer;
import common.CommonMaterial;

public class Test0002b_MushroomAO extends SFAbstractTestAO{

	private static final String FILENAME="test0002b";
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	
	public static void main(String[] args) {
		execute(new Test0002b_MushroomAO());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomMesh", new SFDataCenterListener<SFMeshGeometryData>() {
			
			@Override
			public void onDatasetAvailable(String name, SFMeshGeometryData dataset) {
				
				SFMeshGeometry meshGeometry=(SFMeshGeometry)dataset.getResource();
				
				//storeXML(dataset);
				
				triangleMesh = convertSFMeshGeometryInTriangles(meshGeometry);
				
				//triangleMesh = tessellation(triangleMesh, 2);
				
				calculateAOValues(triangleMesh, 1000);
				
				//printAOValues(triangleMesh);
				
				//printTriangleInfo(triangleMesh);
				
			}
			
		});
		
		SFPrimitive primitive = SFPipeline.getPrimitive("Triangle2PND1");
		
		SFMeshGeometry geometry = createNeWSFMeshGeometryAO(triangleMesh, primitive);
		geometry.init();
	
		float[][] colours = CommonMaterial.generateColours();
		SFStructureArray array = CommonMaterial.generateMaterial(colours);
		
		SFObjectModel model = new SFObjectModel();
		model.getModel().setRootGeometry(geometry);
		model.getModel().setTransformComponent(new SFProgramModuleStructures("BasicPND1"));
		model.getModel().setMaterialComponent(new SFProgramModuleStructures("Data1OccMat"));
		model.getModel().getMaterialComponent().addData(new SFStructureReference(array, 0));
		model.init();
		
		SFViewer.generateFrame(model,SFViewer.getLightStepController(),CommonMaterial.generateColoursController(model),SFViewer.getRotationController(),SFViewer.getZoomController());
		
}
	


	public void buildTestData() {
		
	}

}
