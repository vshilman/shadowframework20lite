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


public class Test0252_D2_LateralTubeAO extends SFAbstractTestAO{
	
	private static final String FILENAME="test0252";
	SFCurvedTubeFunction function;
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	
	public static void main(String[] args) {
		execute(new Test0252_D2_LateralTubeAO());
	}
	
	@Override
	public String getFilename(){
		return FILENAME;
	}

	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("LateralTubeSurface", new SFDataCenterListener<SFCurvedTubeFunctionData>(){
			
			@Override
			public void onDatasetAvailable(String name, SFCurvedTubeFunctionData dataset){
				
				function=(SFCurvedTubeFunction)dataset.getResource();
				
			}
				
		});

		SFDataCenter.getDataCenter().makeDatasetAvailable("PlainLateralTubeModel", new SFDataCenterListener<SFQuadsSurfaceGeometryData>(){
			
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
				
				step_u = 0.05f;
				step_v = 0.05f;
				
				for(float v=0; v<1.0f; v += step_v){
					
					for(float u=0; u<1.0f; u += step_u){
						
						SFVertex3f p1 = function.getPosition(u, v);  
						SFVertex3f p2 = function.getPosition(u, v+step_v); 
						SFVertex3f p3 = function.getPosition(u+step_u, v ); 
						
						SFVertex3f n1 = function.getNormal(u, v);  
						SFVertex3f n2 = function.getNormal(u, v+step_v); 
						SFVertex3f n3 = function.getNormal(u+step_u, v); 
						
						Triangle t = new Triangle (p1,p2,p3,n1,n2,n3);
						t.setAO1(getKao(u,v,n1));
						t.setAO2(getKao(u,v+step_v,n2));
						t.setAO3(getKao(u+step_u,v,n3));
						triangleMesh.add(t);
						
						p1 = function.getPosition(u, v+step_v);  
						p2 = function.getPosition(u+step_u, v); 
						p3 = function.getPosition(u+step_u, v+step_v); 
						
				     	n1 = function.getNormal(u, v+step_v);  
						n2 = function.getNormal(u+step_u, v); 
						n3 = function.getNormal(u+step_u, v+step_v);
					 
						t = new Triangle (p1,p2,p3,n1,n2,n3);
						t.setAO1(getKao(u,v+step_v,n1));
						t.setAO2(getKao(u+step_u,v,n2));
						t.setAO3(getKao(u+step_u,v+step_v,n3));
						triangleMesh.add(t);
						
					}
					
				} 
				
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
	
	
	
	private static final float eps=0.01f;
	private static final double g = 0.05f;
	
	public SFVertex3f getDu2(float u,float v){
		
		SFVertex3f p1 = function.getPosition(u+eps,v);
		SFVertex3f p2 = function.getPosition(u,v);
		SFVertex3f p3 = function.getPosition(u-eps,v);
		p2.mult(2.0f);
		p1.subtract3f(p2);
		p1.add3f(p3);
		p1.mult(1.0f/(eps*eps));
		return p1;
	}
	
	public SFVertex3f getDv2(float u,float v){
		
		SFVertex3f p1 = function.getPosition(u,v+eps);
		SFVertex3f p2 = function.getPosition(u,v);
		SFVertex3f p3 = function.getPosition(u,v-eps);
		p2.mult(2.0f);
		p1.subtract3f(p2);
		p1.add3f(p3);
		p1.mult(1.0f/(eps*eps));
		return p1;
	}
	
	public SFVertex3f getDuv(float u,float v){
		
		SFVertex3f p1 = function.getPosition(u+eps,v+eps);
		SFVertex3f p2 = function.getPosition(u+eps,v-eps);
		SFVertex3f p3 = function.getPosition(u-eps,v+eps);
		SFVertex3f p4 = function.getPosition(u-eps,v-eps);
		p1.subtract3f(p2);
		p1.subtract3f(p3);
		p1.add3f(p4);
		p1.mult(1.0f/(4*(eps*eps)));
		return p1;
	}
	
	public float getKao(float u, float v, SFVertex3f normal){
		
		SFVertex3f Du2 = getDu2(u,v);
		SFVertex3f Dv2 = getDv2(u,v);
		SFVertex3f Duv = getDuv(u,v);
		
		float ku2 = normal.dot3f(Du2); 
		float kv2 = normal.dot3f(Dv2);
		float kuv = normal.dot3f(Duv);
		
		float k = ku2+kv2+2*kuv;
		
		if (k < 0.001f){
			
			k=1;
			
		}else{
			
			k = (float)Math.exp(-g*(double)k);
			
		}
		
		return k;

	}
	
	

	public void buildTestData(){
		
	}

}