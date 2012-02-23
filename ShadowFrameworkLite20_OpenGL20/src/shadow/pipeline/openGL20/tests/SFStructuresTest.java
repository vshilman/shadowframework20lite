package shadow.pipeline.openGL20.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;
import shadow.renderer.data.SFStructureReference;
import shadow.system.SFArrayElementException;

public class SFStructuresTest {


	private static SFStructureArray materialData;
	private static SFStructureReference materialReference;
	
	public static void main(String[] args) {

		try {
			SFGL20Pipeline.setup();
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
			
			//Material
			SFPipelineStructure materialStructure=SFPipeline.getStructure("Mat01");
			List<SFParameteri> parameters=new ArrayList<SFParameteri>();
			parameters.add(new SFParameter("mat01",SFParameteri.GLOBAL_FLOAT3));
			parameters.add(new SFParameter("mat02",SFParameteri.GLOBAL_FLOAT3));
			SFPipelineStructureInstance materialStructureInstance=new SFPipelineStructureInstance(materialStructure,parameters);
			materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance.getStructure()); 
			materialReference=new SFStructureReference(materialData,materialData.generateElement()); 
			SFStructureData mat=new SFStructureData(materialStructureInstance.getStructure());
			((SFVertex3f)mat.getValue(0)).set3f(1, 0, 0);
			((SFVertex3f)mat.getValue(1)).set3f(0.1f, 0.3f, 0.1f);
			try {
				materialReference.setStructureData(mat);
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
			
			//Testing...
			SFStructureData matTest=new SFStructureData(materialStructureInstance.getStructure());
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
