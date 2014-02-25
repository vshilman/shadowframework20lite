package test;

import java.util.ArrayList;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexListDataUnit8;
import shadow.math.SFVertex2f;
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

public class Test0002_D2_MushroomAO extends SFAbstractTestAO{

	private static final String FILENAME="test0002";
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	SFCurvedTubeFunction function;
	
	public static void main(String[] args) {
		execute(new Test0002_D2_MushroomAO());
	}
	
	@Override
	public String getFilename(){
		return FILENAME;
	}

	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		SFCurvedTubeFunctionData func = new SFCurvedTubeFunctionData();	
		
		func.setFirstCurve(new SFBasisSplineData(new SFVertexListDataUnit8(),
				new SFVertex2f(0, 0),
				new SFVertex2f(0.0f, 0.1f),
				new SFVertex2f(0.025f, 0.2f),
				new SFVertex2f(0.0f, 0.3f),
				new SFVertex2f(0.0f, 0.4f),
				new SFVertex2f(0.0f, 0.5f)));
		
		func.setSecondCurve(new SFBasisSplineData(new SFVertexListDataUnit8(),
				new SFVertex2f(0.1f, 0),
				new SFVertex2f(0.2f, 0.1f),
				new SFVertex2f(0.2f, 0.2f),
				new SFVertex2f(0.05f, 0.3f),
				new SFVertex2f(0.05f, 0.4f),
				new SFVertex2f(0.45f, 0.5f),
				new SFVertex2f(0.05f, 0.5f)));
		
		function = (SFCurvedTubeFunction)func.getResource();
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("Mushroom", new SFDataCenterListener<SFQuadsSurfaceGeometryData>(){
 			
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
				triangleMesh = sample(function,step_u,step_v,0);
		
			}
		});
		
		
		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PND1");
		SFMeshGeometry geometry = createNeWSFMeshD2(triangleMesh, primitive);
		geometry.init();
		SFObjectModel model=new SFObjectModel();
		model.getModel().setRootGeometry(geometry);
		model.getModel().setTransformComponent(new SFProgramModuleStructures("BasicPND1"));
		model.getModel().setMaterialComponent(new SFProgramModuleStructures("Data1OccMat"));
		model.init();			
		SFViewer.generateFrame(model,SFViewer.getMaterialController(),SFViewer.getLightStepController(),SFViewer.getRotationController(),SFViewer.getZoomController());
		
	}
	
	

	public void buildTestData(){
		
	}

}
