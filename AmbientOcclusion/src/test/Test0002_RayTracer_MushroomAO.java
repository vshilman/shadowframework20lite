package test;

import java.util.ArrayList;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.data.SFMeshGeometryData;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramModuleStructures;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import util.Triangle;
import viewer.SFViewer;


public class Test0002_RayTracer_MushroomAO extends SFAbstractTestAO{

	private static final String FILENAME="test0002b";
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	
	public static void main(String[] args) {
		execute(new Test0002_RayTracer_MushroomAO());
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
	
				calculateAOValues(triangleMesh, 1000);
				
			}
			
		});
		
		SFPrimitive primitive = SFPipeline.getPrimitive("Triangle2PND1");
		SFMeshGeometry geometry = createNeWSFMeshRayTracer(triangleMesh, primitive);
		geometry.init();
		SFObjectModel model=new SFObjectModel();
		model.getModel().setRootGeometry(geometry);
		
		model.getModel().setTransformComponent(new SFProgramModuleStructures("BasicPND1"));
		model.getModel().setMaterialComponent(new SFProgramModuleStructures("Data1OccMat"));
		model.init();
		
		SFViewer.generateFrame(model,SFViewer.getMaterialController(),SFViewer.getLightStepController(),SFViewer.getRotationController(),SFViewer.getZoomController());
		
	}
	


	public void buildTestData() {
		
	}

}
