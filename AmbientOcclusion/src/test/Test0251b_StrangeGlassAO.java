package test;

import java.util.ArrayList;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.data.SFMeshGeometryData;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramModuleStructures;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import util.Triangle;
import viewer.SFViewer;

public class Test0251b_StrangeGlassAO extends SFAbstractTestAO{

	private static final String FILENAME="test0251b";
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	
	public static void main(String[] args) {
		execute(new Test0251b_StrangeGlassAO());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("StrangeGlassMesh", new SFDataCenterListener<SFMeshGeometryData>() {
			
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
		
		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle3PND1");
				
		SFMeshGeometry geometry = createNeWSFMeshGeometryAO(triangleMesh, primitive);
		geometry.init();
			
		// select Cd,Cs,Ca
		float[][] diffColor={{0f,0,0.9f}};;
		float[][] specColor={{0.3f,0.9f,0.99f}};;
		float[][] ambColor={{0.3f,0.2f,0.5f}};;
				
		// select light: ILD, ILS, ILA, LIGHT_POSITION
		SFVertex3f[] lightData={new SFVertex3f(0.5,0.5,0.2), new SFVertex3f(1,1,1), new SFVertex3f(1,1,1),  new SFVertex3f(1,1,-1)};
						
		SFObjectModel model=new SFObjectModel();
		model.getModel().setRootGeometry(geometry);
		model.getModel().setTransformComponent(new SFProgramModuleStructures("BasicPND1"));
		model.getModel().setMaterialComponent(new SFProgramModuleStructures("Data1OccMat"));
				
		SFViewer.setColor(diffColor, specColor, ambColor);
		SFViewer.setLight(lightData);
			
		model.init();
						
		SFViewer.generateFrame(model,SFViewer.getLightStepController(),SFViewer.getRotationController(),SFViewer.getZoomController());

	}
	

	public void buildTestData() {
		
	}

}
