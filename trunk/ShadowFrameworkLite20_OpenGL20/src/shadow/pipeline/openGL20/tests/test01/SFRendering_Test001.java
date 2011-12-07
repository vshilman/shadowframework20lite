package shadow.pipeline.openGL20.tests.test01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.sun.opengl.util.FPSAnimator;

import shadow.material.SFStructureReference;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFArrayElementException;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL2;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFArray;
import shadow.system.SFException;

public class SFRendering_Test001 extends JFrame{

	private static final String BASIC_LSPN = "BasicLSPN";
	private static final String BASIC_MAT = "BasicMat";
	
	private static SFProgram program;

	private static SFStructureArray materialData;
	private static SFStructureReference materialReference;
	private static SFStructureArray lightData;
	private static SFStructureReference lightReference;
	
	private static SFPrimitiveArray primitiveData;
	
	public static void main(String[] args) {
		
		SFRendering_Test001 universe=new SFRendering_Test001();
		universe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		universe.setVisible(true);
	}
	
	public SFRendering_Test001(){
		
		try {
			SFGL20Pipeline.setup();
			
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
			
			String[] materials={BASIC_MAT};
			SFPrimitive primitive=new SFPrimitive();
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("N"), (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("P"), (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			//registers,
			
			//primitives, transform
			program=SFPipeline.getStaticProgram(primitive, materials, BASIC_LSPN);

			//Material
			//SFPipelineStructure materialStructure=SFPipeline.getStructure("Mat01");
			SFPipelineStructureInstance materialStructureInstance=((List<SFPipelineStructureInstance>)(program.getMaterial(0).getStructures())).get(0);
			materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance); 
			materialReference=new SFStructureReference(materialData); 
			SFStructureData mat=new SFStructureData(materialStructureInstance);
			((SFVertex3f)mat.getValue(0)).set3f(1, 0, 0);
			((SFVertex3f)mat.getValue(1)).set3f(0.1f, 0.1f, 0.1f);
			try {
				materialReference.setStructureData(mat);
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
			
			//Light
			SFPipelineStructure lighStructure=SFPipeline.getStructure("PLight01");
			SFPipelineStructureInstance lightStructureInstance=((List<SFPipelineStructureInstance>)(program.getLightStep().getStructures())).get(0);
			lightData=SFPipeline.getSfPipelineMemory().generateStructureData(lightStructureInstance); 
			lightReference=new SFStructureReference(lightData); 
			SFStructureData lit=new SFStructureData(lightStructureInstance);
			((SFVertex3f)lit.getValue(0)).set3f(1, 1, 1);
			((SFVertex3f)lit.getValue(1)).set3f(1, 1, 1);
			try {
				lightReference.setStructureData(lit);
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
			
			
			//Primitive
			primitiveData=SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive);
			int elementIndex=primitiveData.generateElements(1);
			
			SFArray<SFValuenf> verticesArray=primitiveData.getPrimitiveData(1);
			SFArray<SFValuenf> normalsArray=primitiveData.getPrimitiveData(0);
			int verticesIndex=verticesArray.generateElements(6);
			int normalsIndex=normalsArray.generateElements(1);
			
			try {
				verticesArray.setElement(verticesIndex+0,new SFVertex3f(0, 0, 1));
				verticesArray.setElement(verticesIndex+1,new SFVertex3f(1, 0, 0));
				verticesArray.setElement(verticesIndex+2,new SFVertex3f(0, 1, 0));
				verticesArray.setElement(verticesIndex+3,new SFVertex3f(0.5f, 0, 0));
				verticesArray.setElement(verticesIndex+4,new SFVertex3f(0.5f, 0.5f, 0));
				verticesArray.setElement(verticesIndex+5,new SFVertex3f(0, 0.5f, 0));
				
				normalsArray.setElement(normalsIndex+0,new SFVertex3f(0,0,1));
				
				SFPrimitiveIndices indices=new SFPrimitiveIndices(primitive);
				
				int prIndices[][]={{normalsIndex,normalsIndex,normalsIndex},
						{verticesIndex+0,verticesIndex+1,verticesIndex+2,
					verticesIndex+3,verticesIndex+4,verticesIndex+5}};
				indices.setPrimitiveIndices(prIndices);
				primitiveData.setElement(elementIndex, indices);
				
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
			
			
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
	
	public static void init(){
		//init program
		SFPipeline.getSfProgramBuilder().prepareProgram(program);
	}
	
	public static void render(){
		
		//load program
		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		//load material data
		SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());
		
		//load light data
		SFPipeline.getSfPipelineGraphics().loadStructureData(lightData, lightReference.getMaterialIndex());
		
		//load primitives
		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);
	}
	
	
	
	public static class EventListener001 implements GLEventListener{
		
		@Override
		public void display(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
			GL2 gl=(GL2)(arg0.getGL());
			gl.glClearColor(1,1,1,1);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);
			
			SFGL2.setGl((GL2)arg0.getGL());
			SFRendering_Test001.render();
		
		}
		
		@Override
		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void init(GLAutoDrawable arg0) {
			
			SFGL2.setGl((GL2)arg0.getGL());
			
			SFRendering_Test001.init();
		}
		
		@Override
		public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
				int arg4) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
