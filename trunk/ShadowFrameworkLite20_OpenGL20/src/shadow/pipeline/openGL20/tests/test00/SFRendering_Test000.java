package shadow.pipeline.openGL20.tests.test00;

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

import shadow.material.SFStructureReference;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFArrayElementException;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
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

import com.sun.opengl.util.FPSAnimator;

public class SFRendering_Test000 extends JFrame{

	private static final String BASIC_LSPN = "BasicColor";
	private static final String BASIC_MAT = "BasicMat";
	
	private static SFProgram program;

	private static SFStructureArray materialData;
	private static SFStructureReference materialReference;
	
	private static SFPrimitiveArray primitiveData;
	
	private static int elementIndex;
	
	public static void main(String[] args) {
		
		SFRendering_Test000 universe=new SFRendering_Test000();
		universe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		universe.setVisible(true);
	}
	
	public SFRendering_Test000(){
		
		try {
			SFGL20Pipeline.setup();
			
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
			
			String[] materials={BASIC_MAT};
			SFPrimitive primitive=new SFPrimitive();
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("P"), (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			//registers,
			
			
			//primitives, transform
			program=SFPipeline.getStaticProgram(primitive, materials, BASIC_LSPN);

			//Material
			SFPipelineStructureInstance materialStructureInstance=((List<SFPipelineStructureInstance>)(program.getMaterial(0).getStructures())).get(0);
			materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance); 
			materialReference=new SFStructureReference(materialData); 
			SFStructureData mat=new SFStructureData(materialStructureInstance);
			((SFVertex3f)mat.getValue(0)).set3f(1, 0, 0);
			((SFVertex3f)mat.getValue(1)).set3f(1.0f, 1.0f, 0.0f);
			try {
				materialReference.setStructureData(mat);
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
			
			System.err.println("Material Parameter 0 "+materialStructureInstance.getParameters().get(0).getName());
			System.err.println("Material Parameter 1 "+materialStructureInstance.getParameters().get(1).getName());
			System.err.println("Mat Structure Parameter 0 "+materialStructureInstance.getStructure().getAllParameters().get(0).getName());
			System.err.println("Mat Structure Parameter 1 "+materialStructureInstance.getStructure().getAllParameters().get(1).getName());
			System.err.println("((SFVertex3f)mat.getValue(0)) "+((SFVertex3f)mat.getValue(0)));
			System.err.println("((SFVertex3f)mat.getValue(1)) "+((SFVertex3f)mat.getValue(1)));
			
//			//Light
//			SFPipelineStructure lighStructure=SFPipeline.getStructure("PLight01");
//			lightData=SFPipeline.getSfPipelineMemory().generateStructureData(lighStructure); 
//			lightReference=new SFStructureReference(lightData); 
//			SFStructureData lit=new SFStructureData(lighStructure);
//			((SFVertex3f)lit.getValue(0)).set3f(1, 1, 1);
//			((SFVertex3f)lit.getValue(1)).set3f(1, 1, 1);
//			try {
//				lightReference.setStructureData(lit);
//			} catch (SFArrayElementException e) {
//				e.printStackTrace();
//			}		
			
			
			//Primitive
			primitiveData=SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive);
			elementIndex=primitiveData.generateElements(4);
			SFArray<SFValuenf> verticesArray=primitiveData.getPrimitiveData(0);
			int verticesIndex=verticesArray.generateElements(5);
			
			try {
				verticesArray.setElement(verticesIndex+0,new SFVertex3f(0, 0, 1));
				verticesArray.setElement(verticesIndex+1,new SFVertex3f(1, 0, 0));
				verticesArray.setElement(verticesIndex+2,new SFVertex3f(0, 1, 0));
				verticesArray.setElement(verticesIndex+3,new SFVertex3f(-1, 0, 0));
				verticesArray.setElement(verticesIndex+4,new SFVertex3f(0, -1, 0));
				
				SFPrimitiveIndices indices=new SFPrimitiveIndices(primitive);
				
				int prIndices[][]={{verticesIndex+0,verticesIndex+1,verticesIndex+2}};
				indices.setPrimitiveIndices(prIndices);
				primitiveData.setElement(elementIndex, indices);
				prIndices[0][1]=verticesIndex+0;
				prIndices[0][1]=verticesIndex+2;
				prIndices[0][2]=verticesIndex+3;
				indices.setPrimitiveIndices(prIndices);
				primitiveData.setElement(elementIndex+1, indices);
				prIndices[0][1]=verticesIndex+0;
				prIndices[0][1]=verticesIndex+3;
				prIndices[0][2]=verticesIndex+4;
				indices.setPrimitiveIndices(prIndices);
				primitiveData.setElement(elementIndex+2, indices);
				prIndices[0][1]=verticesIndex+0;
				prIndices[0][1]=verticesIndex+4;
				prIndices[0][2]=verticesIndex+1;
				indices.setPrimitiveIndices(prIndices);
				primitiveData.setElement(elementIndex+3, indices);
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
		
		long t1=System.nanoTime();
		
		//load program
		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		//load material data
		SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());
		
		//load primitives
		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, elementIndex, 4);
		long t2=System.nanoTime();
		
		System.out.println("t2-t2 "+(t2-t1));
	}
	
	
	
	public static class EventListener001 implements GLEventListener{
		
		@Override
		public void display(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
			GL2 gl=(GL2)(arg0.getGL());
			gl.glClearColor(1,1,1,1);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);
			
			SFGL2.setGl((GL2)arg0.getGL());
			SFRendering_Test000.render();
		
		}
		
		@Override
		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void init(GLAutoDrawable arg0) {
			
			SFGL2.setGl((GL2)arg0.getGL());
			
			SFRendering_Test000.init();
		}
		
		@Override
		public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
				int arg4) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
