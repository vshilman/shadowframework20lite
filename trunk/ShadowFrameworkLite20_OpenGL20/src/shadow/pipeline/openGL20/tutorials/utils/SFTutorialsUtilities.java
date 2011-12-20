package shadow.pipeline.openGL20.tutorials.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import shadow.material.SFStructureReference;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFArrayElementException;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public class SFTutorialsUtilities {

	/**
	 * 
	 * @param filename
	 * @param materials
	 * @param lightStep
	 * @param primitive an empty primitive which will be assigned a Positions function, a Normal function and a Tessellator
	 * @return
	 */
	public static SFProgram generateProgram(String filename,String[] materials,String lightStep,
			SFPrimitive primitive,String positionProgram,String normalProgram,String tessellationProgram){

		SFProgram program=null;
		try {
			SFProgramComponentLoader.loadComponents(new File(filename));

			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("N"), (SFProgramComponent)(SFPipeline.getModule(positionProgram)));
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("P"), (SFProgramComponent)(SFPipeline.getModule(normalProgram)));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule(tessellationProgram)));

			//primitives, transform
			program=SFPipeline.getStaticProgram(primitive, materials, lightStep);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		} catch (SFException e) {
			e.printStackTrace();
		}
		return program;
	}
	
	/**
	 * 
	 * @param filename
	 * @param materials
	 * @param lightStep
	 * @param primitive an empty primitive which will be assigned a Positions function, a Normal function and a Tessellator
	 * @return
	 */
	public static SFProgram generateProgram(String filename,String[] materials,String lightStep,
			SFPrimitive primitive,String positionProgram,String duProgram,String dvProgram,String tessellationProgram){

		SFProgram program=null;
		try {
			SFProgramComponentLoader.loadComponents(new File(filename));

			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("N"), (SFProgramComponent)(SFPipeline.getModule(positionProgram)));
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("dU"), (SFProgramComponent)(SFPipeline.getModule(duProgram)));
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("dV"), (SFProgramComponent)(SFPipeline.getModule(dvProgram)));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule(tessellationProgram)));

			//primitives, transform
			program=SFPipeline.getStaticProgram(primitive, materials, lightStep);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		} catch (SFException e) {
			e.printStackTrace();
		}
		return program;
	}
	
	/**
	 * 
	 * @param filename
	 * @param materials
	 * @param lightStep
	 * @param primitive an empty primitive which will be assigned a Positions function, a Normal function and a Tessellator
	 * @return
	 */
	public static SFProgram generateImageProgram(String filename,String[] materials,String lightStep){

		SFProgram program=null;
		try {
			SFProgramComponentLoader.loadComponents(new File(filename));

			program=SFPipeline.getStaticImageProgram(materials, lightStep);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		return program;
	}
	
	public static SFStructureArray generateMaterialData(SFProgram program,int materialIndex,int structureIndex){
		//note it supposo to have only one structure..
		SFPipelineStructureInstance materialStructureInstance=((List<SFPipelineStructureInstance>)(program.getMaterial(materialIndex).getStructures())).get(structureIndex);
		SFStructureArray materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance); 
		return materialData;
	}
	
	public static SFStructureArray generateLightData(SFProgram program,int structureIndex){
		//note it supposo to have only one structure..
		SFPipelineStructureInstance materialStructureInstance=((List<SFPipelineStructureInstance>)(program.getLightStep().getStructures())).get(structureIndex);
		SFStructureArray materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance); 
		return materialData;
	}
	
	public static SFStructureReference generateStructureDataReference(SFProgram program,SFStructureArray materialData,SFVertex3f[] data){
		SFPipelineStructureInstance materialStructureInstance=materialData.getPipelineStructure();
		SFStructureReference materialReference=new SFStructureReference(materialData); 
		SFStructureData mat=new SFStructureData(materialStructureInstance);
		for (int i = 0; i < data.length; i++) {
			((SFVertex3f)mat.getValue(i)).set(data[i]);
		}
		try {
			materialReference.setStructureData(mat);
		} catch (SFArrayElementException e) {
			e.printStackTrace();
		}
		return materialReference;
	}

}
