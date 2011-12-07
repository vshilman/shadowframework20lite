package shadow.pipeline.openGL20.tests.test06QuadSurfaceGeometry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.editing.SFConcreteTriangleExtractor;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL2;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tests.test02.SFRendering_Test002;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

import com.sun.opengl.util.FPSAnimator;


public class SFRendering_Test006v01 extends JFrame{

	private static final String RENDERNORMALS = "DrawNormals";
	private static SFMeshGeometry geometry;
	
	private static SFProgram program;
	
	public static void main(String[] args) {

		SFRendering_Test006v01 universe=new SFRendering_Test006v01();
		universe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		universe.setVisible(true);
		
		
	}
	
	public SFRendering_Test006v01(){

		try {
			SFGL20Pipeline.setup();
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
			
			SFPrimitive primitive=new SFPrimitive();
			
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("N"), (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("P"), (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			CylinderFunction function=new CylinderFunction();
			
			SFConcreteTriangleExtractor trianglesExtractor=new SFConcreteTriangleExtractor();
			SFQuadsSurfaceGeometry quadsSurfaceGeometry=new SFQuadsSurfaceGeometry(primitive,
					function, null, trianglesExtractor, 5, 2);
			
			long time1=System.nanoTime();
			quadsSurfaceGeometry.compile();
			long time2=System.nanoTime();
			System.out.println("Compile Time "+(time2-time1));
			geometry=quadsSurfaceGeometry;

			//primitives, transform
			String[] materials={};
			program=SFPipeline.getStaticProgram(primitive, materials, RENDERNORMALS);

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			ArrayList<String> errors=e.getList();
			for (int i = 0; i < errors.size(); i++) {
				System.err.println(errors.get(i));
			}
		} catch(SFException moduleException){
			moduleException.printStackTrace();
		}
		

		setSize(400, 400);
		
		setTitle("Frame");
	
		GLCanvas canvas=new GLCanvas(new GLCapabilities(GLProfile.get(GLProfile.GL2)));
		canvas.addGLEventListener(new EventListener001());
		FPSAnimator animator=new FPSAnimator(canvas,30);
		animator.start();
		
		getContentPane().add(canvas);
	}
	
	public static class CylinderFunction extends SFSurfaceFunction{
		
		@Override
		public float getX(float u, float v) {
			// TODO Auto-generated method stub
			//return u*0.6f;
			return 0.5f*(float)(Math.cos(2*Math.PI*u)*(0.5f+0.5f*(v)));
		}
		
		@Override
		public float getY(float u, float v) {
			// TODO Auto-generated method stub
			//return v*0.6f;
			return 0.5f*(float)(Math.sin(2*Math.PI*u)*(0.5f+0.5f*(v)));
		}
		
		@Override
		public float getZ(float u, float v) {
			// TODO Auto-generated method stub
			return 0.5f*(float)((v));
		}
		
		@Override
		public SFDataset generateNewDatasetInstance() {
			return null;
		}
		
		@Override
		public String getCode() {
			return null;
		}
		
		
		@Override
		public void readFromStream(SFInputStream stream) {
			
		}
		
		@Override
		public void writeOnStream(SFOutputStream stream) {
			
		}
	}

	public static void init(){
		//init program
		SFPipeline.getSfProgramBuilder().prepareProgram(program);
	}
	
	static float f=0;
	
	public static void render(){
		
		//load program
		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		f+=0.01f;
		SFPipeline.getSfPipelineGraphics().rotateModel(0.25f+f,0,0);
		//load primitives
		//SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);
		geometry.drawGeometry(1);
	}
	

	
	public static class EventListener001 implements GLEventListener{
		
		@Override
		public void display(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
		
			GL2 gl=(GL2)(arg0.getGL());
			gl.glClearColor(1,1,1,1);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			
			gl.glEnable(GL2.GL_DEPTH_TEST);
			
			//gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
			
			SFGL2.setGl((GL2)arg0.getGL());
			SFRendering_Test006v01.render();
		
		}
		
		@Override
		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void init(GLAutoDrawable arg0) {
			
			SFGL2.setGl((GL2)arg0.getGL());
			
			SFRendering_Test002.init();
		}
		
		@Override
		public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
				int arg4) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
