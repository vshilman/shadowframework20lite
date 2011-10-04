package shadow.pipeline.openGL20.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import shadow.material.SFStructureReference;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFArrayElementException;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public class SFStructuresTest {


	private static SFStructureArray materialData;
	private static SFStructureReference materialReference;
	
	public static void main(String[] args) {

		try {
			SFGL20Pipeline.setup();
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
			
			//Material
			SFPipelineStructure materialStructure=SFPipeline.getStructure("Mat01");
			materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructure); 
			materialReference=new SFStructureReference(materialData); 
			SFStructureData mat=new SFStructureData(materialStructure);
			((SFVertex3f)mat.getValue(0)).set3f(1, 0, 0);
			((SFVertex3f)mat.getValue(1)).set3f(0.1f, 0.1f, 0.1f);
			try {
				materialReference.setStructureData(mat);
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
			
			//Testing...
			SFStructureData matTest=new SFStructureData(materialStructure);
			try {
				materialReference.getStructureData(matTest);
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
			System.out.println(((SFVertex3f)matTest.getValue(0)).toString());
			System.out.println(((SFVertex3f)matTest.getValue(1)).toString());
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			ArrayList<String> errors=e.getList();
			for (int i = 0; i < errors.size(); i++) {
				System.err.println(errors.get(i));
			}
		} 
	}
}
