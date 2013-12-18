package test;

import java.util.ArrayList;

import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramModuleStructures;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.objects.SFShortByteField;
import util.Triangle;
import viewer.SFViewer;

public class Test0250_D2_MushroomAO extends SFAbstractTestAO{
	
	private static final String FILENAME="test0250";
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	SFCurvedTubeFunction function;
	
	public static void main(String[] args) {
		execute(new Test0250_D2_MushroomAO());
	}
	
	@Override
	public String getFilename(){
		return FILENAME;
	}

	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomSurface", new SFDataCenterListener<SFCurvedTubeFunctionData>(){
			
			@Override
			public void onDatasetAvailable(String name, SFCurvedTubeFunctionData dataset){
				
				function=(SFCurvedTubeFunction)dataset.getResource();
				
			}
				
		});

		SFDataCenter.getDataCenter().makeDatasetAvailable("PlainMushroomModel", new SFDataCenterListener<SFQuadsSurfaceGeometryData>(){
			
			@Override
			public void onDatasetAvailable(String name, SFQuadsSurfaceGeometryData dataset){
				
				SFShortByteField NuNv = (SFShortByteField) dataset.getSFDataObject().getObject("NuNv");
				int Nu=NuNv.getByte(0);
				int Nv=NuNv.getByte(1);
				SFPrimitive primitive = dataset.getResource().getPrimitive();
				SFPipelineGrid grids[] = primitive.getGrids();
				int n_1 = grids[0].getN();
				int n_2 = grids[1].getN();
				float step_u = 1.0f/((Nu-1.0f)*(n_1-1.0f));
				float step_v = 1.0f/((Nv-1.0f)*(n_2-1.0f));
				
				triangleMesh = sample(function,step_u,step_v);
				
			}
				
		});
		
		
		SFPrimitive primitive=SFPipeline.getPrimitive("TrianglePND1");
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
	
	
	
	
	


	public void buildTestData(){
		
	}

}
