package shadow.pipeline.openGL20.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFArrayElementException;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFArray;
import shadow.system.SFException;

public class SFPrimitiveDataTest {

	
	private static SFPrimitiveArray primitiveData;
	
	public static void main(String[] args) {

		try {
			SFGL20Pipeline.setup();
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
			
			SFPrimitive primitive=new SFPrimitive();
			
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("N"), (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("P"), (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			//registers,
			
			
			//Primitive
			primitiveData=SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive);
			int elementIndex=primitiveData.generateElements(1);
			SFArray<SFValuenf> verticesArray=primitiveData.getPrimitiveData(0);
			SFArray<SFValuenf> normalsArray=primitiveData.getPrimitiveData(1);
			int verticesIndex=verticesArray.generateElements(6);
			int normalsIndex=normalsArray.generateElements(1);
			
			try {
				verticesArray.setElement(verticesIndex+0,new SFVertex3f(0, 0, 0));
				verticesArray.setElement(verticesIndex+1,new SFVertex3f(1, 0, 0));
				verticesArray.setElement(verticesIndex+2,new SFVertex3f(0, 1, 0));
				verticesArray.setElement(verticesIndex+3,new SFVertex3f(0.5f, 0, 0));
				verticesArray.setElement(verticesIndex+4,new SFVertex3f(0, 0.5f, 0));
				verticesArray.setElement(verticesIndex+5,new SFVertex3f(0.5f, 0.5f, 0));
				
				
				normalsArray.setElement(normalsIndex+0,new SFVertex3f(0,0,1));
				
				SFPrimitiveIndices indices=new SFPrimitiveIndices(primitive);
				int prIndices[][]={{normalsIndex,normalsIndex,normalsIndex},
						{verticesIndex+0,verticesIndex+1,verticesIndex+2,
					verticesIndex+3,verticesIndex+4,verticesIndex+5},};
				indices.setPrimitiveIndices(prIndices);
				primitiveData.setElement(elementIndex, indices);
				
				SFPrimitiveIndices indicesBack=new SFPrimitiveIndices(primitive);
				primitiveData.getElement(elementIndex, indicesBack);
				
				int idsBack[][]=indicesBack.getPrimitiveIndices();
				System.out.println("idsBack.length="+idsBack.length);
				for (int i = 0; i < idsBack.length; i++) {
					System.out.println("idsBack[i].length="+idsBack[i].length);
					for (int j = 0; j < idsBack[i].length; j++) {
						System.out.println("idsBack[i][j]="+idsBack[i][j]);
						int index=idsBack[i][j];
						SFVertex3f v=new SFVertex3f(0,0,0);
						if(i==1)
							verticesArray.getElement(verticesIndex+index, v);
						if(i==0)
							normalsArray.getElement(normalsIndex+index, v);
						System.out.println(v);
					}
				}
				
				
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
		
	}

}
