package test;

import java.util.ArrayList;
import java.util.List;

import shadow.animation.SFAnimation;
import shadow.animation.SFAnimator;
import shadow.animation.SFPeriodicAnimation;
import shadow.geometry.curves.SFBasisSpline2;
import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.math.SFValuenf;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramModuleStructures;
import viewer.SFAnimationTimer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.objects.SFShortByteField;
import util.Triangle;
import viewer.SFViewer;

public class Test0250_D2_MushroomAO_Animation extends SFAbstractTestAO{
	
	private static final String FILENAME="test0250";
	SFCurvedTubeFunction function;
	SFObjectModel model;
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	float step_u;
	float step_v;
	
	public static void main(String[] args) {
		execute(new Test0250_D2_MushroomAO_Animation());
	}
	
	@Override
	public String getFilename(){
		return FILENAME;
	}

	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		SFAnimationTimer.startTimer();

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
				step_u = 1.0f/((Nu-1.0f)*(n_1-1.0f));
				step_v = 1.0f/((Nv-1.0f)*(n_2-1.0f));
				
			}
				
		});
		triangleMesh = sample(function, step_u, step_v,1);
		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle3PND1");		
		final SFMeshGeometry geometry = createNeWSFMeshD2(triangleMesh, primitive);
		SFBasisSpline2<SFValuenf> curve=(SFBasisSpline2<SFValuenf>)function.getRayCurve();
		final SFValuenf value=curve.getVertices().get(5);
		final SFValuenf value2=curve.getVertices().get(1);
		final SFValuenf value3=curve.getVertices().get(2);
	
		SFAnimation morphAnimation=new SFAnimation(){
			
			public void init(){
			}
			
			public SFAnimation clone(){
				return null;
			}
			
			public void destroy(){
			}
			
			@Override
			public void animate(long time){
				List<Long> times = new ArrayList<Long>();
				times.add(System.nanoTime());
				float t = (time/(1.0f*4000));
				t = 4*t*(1-t)*0.3f+0.5f;
				value.set(0.45f*t,0.5f*t,t*0.25f);
				value2.set(0.3f*t,0.1f,-t*0.25f);
				value3.set(0.3f*t,0.2f,-t*0.25f);
				triangleMesh = sample(function, step_u, step_v,1);
				SFPrimitive primitive = SFPipeline.getPrimitive("Triangle3PND1");
				SFMeshGeometry geometry = createNeWSFMeshD2(triangleMesh, primitive);
				geometry.init();
				model.getModel().setRootGeometry(geometry);
			}
		};
		
		SFPeriodicAnimation morphAnimationPeriod=new SFPeriodicAnimation(morphAnimation,4000, 0);
		SFAnimator.addAnimation(morphAnimationPeriod);
		model=new SFObjectModel();
		model.getModel().setRootGeometry(geometry);
		model.getModel().setTransformComponent(new SFProgramModuleStructures("BasicPND1"));
		model.getModel().setMaterialComponent(new SFProgramModuleStructures("Data1OccMat"));
		model.init();
		SFViewer.generateFrame(model,SFViewer.getMaterialController(),SFViewer.getLightStepController(),SFViewer.getRotationController(),SFViewer.getZoomController());	
	}
	
	
	


	public void buildTestData(){
		
	}

	
}
