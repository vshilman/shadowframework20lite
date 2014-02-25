package test;

import interfaces.Observer;

import java.io.IOException;
import java.util.ArrayList;

import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
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


public class Application extends SFAbstractTestAO implements Observer{
	
	SFObjectModel model;
	SFCurvedTubeFunction function;
	private static String FILENAME="test0250b";
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	
	public static void main(String[] args) {
		execute(new Application());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {
		
		SFViewer.setMesh(triangleMesh);
		SFViewer.addObs(this);
		model = new SFObjectModel();
		model.getModel().setTransformComponent(new SFProgramModuleStructures("BasicPND1"));
		model.getModel().setMaterialComponent(new SFProgramModuleStructures("Data1OccMat"));
		model.init();
		SFViewer.generateFrame(model,SFViewer.getAlgController(),SFViewer.getLoadModelController(),SFViewer.getMaterialController(),SFViewer.getLightStepController(),SFViewer.getRotationController(),SFViewer.getZoomController());
		
	}
	


	public void buildTestData() {
		
	}

	@Override
	public void update(int index, int alg) throws NumberFormatException, IOException{
		
		if(alg == 0){
			
			SFPrimitive primitive = SFPipeline.getPrimitive("Triangle3PND1");
			triangleMesh.clear();
			triangleMesh = readData(index);
			SFMeshGeometry geometry = createNeWSFMeshRayTracer(triangleMesh, primitive);
			geometry.init();
			model.getModel().setRootGeometry(geometry);
			SFViewer.setZoom(index);
		
		}else if(alg == 1){
		
			String[] surfaces = {"MushroomSurface",  "LateralTubeSurface","StrangeGlassSurface"};
			String[] models   = {"PlainMushroomModel", "PlainLateralTubeModel", "PlainStrangeGlassModel"};
			String[] files = {"test0250", "test0252", "test0251"};
			FILENAME = files[index];
			loadLibraryAsDataCenter();
			
			SFDataCenter.getDataCenter().makeDatasetAvailable(surfaces[index], new SFDataCenterListener<SFCurvedTubeFunctionData>(){
	
				@Override
				public void onDatasetAvailable(String name, SFCurvedTubeFunctionData dataset){					
					function=(SFCurvedTubeFunction)dataset.getResource();
				}
					
			});

			SFDataCenter.getDataCenter().makeDatasetAvailable(models[index], new SFDataCenterListener<SFQuadsSurfaceGeometryData>(){
				
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

					triangleMesh = sample(function,step_u,step_v,1);
					
				}
					
			});
			
			SFPrimitive primitive=SFPipeline.getPrimitive("Triangle3PND1");
			SFMeshGeometry geometry = createNeWSFMeshD2(triangleMesh, primitive);
			geometry.init();
			model.getModel().setRootGeometry(geometry);	
			SFViewer.setZoom(index);
			
		}	
		
	}
	

}
