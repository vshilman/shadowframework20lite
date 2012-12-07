package shadow.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineElement;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20AbstractProgram;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.renderer.SFStructureReference;
import shadow.system.SFArrayElementException;
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
	//public static SFProgram generateProgram(String filename,String[] materials,String lightStep,
			//SFPrimitive primitive,String positionProgram,String normalProgram,String tessellationProgram){
public static SFProgram generateProgram(String filename,String transform, String materials,String lightStep,
		SFPrimitive primitive,String positionProgram,String normalProgram,String tessellationProgram){
		
	SFProgram program=null;
		try {
			SFProgramComponentLoader.loadComponents(new File(filename), new SFPipelineBuilder());

			//primitive.addPrimitiveElement(SFPipelineRegister.getFromName("N"), (SFProgramComponent)(SFPipeline.getModule(positionProgram)));
			//primitive.addPrimitiveElement(SFPipelineRegister.getFromName("P"), (SFProgramComponent)(SFPipeline.getModule(normalProgram)));
			//primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule(tessellationProgram)));
			
			SFPrimitiveBlock[] blocks={SFPrimitiveBlock.NORMAL,SFPrimitiveBlock.POSITION}; //crea
			SFProgramComponent[] components={(SFProgramComponent)(SFPipeline.getModule(normalProgram)),
					 (SFProgramComponent)(SFPipeline.getModule(positionProgram))}; //riempie
			primitive.setPrimitiveElements(blocks,components); //utilizza
			
			//primitives, transform
			program=SFPipeline.getStaticProgram(primitive, transform, materials, lightStep); //da compilare
			//inp ratica qui non ho più un array, ma un solo elemento, un solo programma che è la somma di più materiali
			//se ho un solo componente carico quello e lui creo programma in autmatico
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
	public static SFProgram generateProgram(String filename,String transform, String materials,String lightStep,
			SFPrimitive primitive,String positionProgram,String duProgram,String dvProgram,String tessellationProgram){

		SFProgram program=null;
		try {
			SFProgramComponentLoader.loadComponents(new File(filename), new SFPipelineBuilder() );
//vedi sopra
		//	primitive.addPrimitiveElement(SFPipelineRegister.getFromName("N"), (SFProgramComponent)(SFPipeline.getModule(positionProgram)));
		//	primitive.addPrimitiveElement(SFPipelineRegister.getFromName("dU"), (SFProgramComponent)(SFPipeline.getModule(duProgram)));
		//	primitive.addPrimitiveElement(SFPipelineRegister.getFromName("dV"), (SFProgramComponent)(SFPipeline.getModule(dvProgram)));
		//	primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule(tessellationProgram)));
			SFPrimitiveBlock[] blocks={SFPrimitiveBlock.POSITION, SFPrimitiveBlock.DU,SFPrimitiveBlock.DV}; //crea
			SFProgramComponent[] components={(SFProgramComponent)(SFPipeline.getModule(positionProgram)),
					 (SFProgramComponent)(SFPipeline.getModule(duProgram)),(SFProgramComponent)(SFPipeline.getModule(dvProgram))}; //riempie
			primitive.setPrimitiveElements(blocks,components); //utilizza
			
			//primitives, transform
			program=SFPipeline.getStaticProgram(primitive, transform, materials, lightStep);
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
	public static SFProgram generateImageProgram(String filename,String materials,String lightStep){

		SFProgram program=null;
		try {
			SFProgramComponentLoader.loadComponents(new File(filename),  new SFPipelineBuilder());

			program=SFPipeline.getStaticImageProgram(materials, lightStep); //ora sono singole stringehe
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		return program;
	}
	
	public static SFStructureArray generateMaterialData(String programComponet,int materialIndex){
		//note it supposo to have only one structure..
		SFPipelineStructureInstance materialStructureInstance=((SFProgramComponent)(SFPipeline.getModule(programComponet))).getStructures().get(materialIndex);
		SFStructureArray materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance.getStructure()); 
		return materialData;
	}
	
	public static SFStructureArray generateMaterialData(SFProgram program,int materialIndex,int structureIndex){
		//note it supposo to have only one structure..
		
		//SFPipelineStructureInstance materialStructureInstance=((List<SFPipelineStructureInstance>)(program.getMaterial(materialIndex).getStructures())).get(structureIndex);
		 SFPipelineStructureInstance materialStructureInstance=((SFGL20AbstractProgram)program).getMaterials().getComponents()[materialIndex].getStructures().get(structureIndex);
		 //prima vettore di materiali, ora material program che contiene vettore di comoponenti, resto uguale
		SFStructureArray materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance.getStructure()); 
		return materialData;
	}
	
	public static SFStructureArray generateLightData(SFProgram program,int structureIndex){
		//note it supposo to have only one structure..
		
		//SFPipelineStructureInstance materialStructureInstance=((List<SFPipelineStructureInstance>)(program.getLightStep().getStructures())).get(structureIndex);
		SFPipelineStructureInstance materialStructureInstance=((SFGL20AbstractProgram)program).getLight().getComponents()[0].getStructures().get(structureIndex);
		
		SFStructureArray materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance.getStructure()); 
		return materialData;
	}
	
	public static SFStructureReference generateStructureDataReference(SFProgram program,SFStructureArray materialData,SFVertex3f[] data){
		SFPipelineStructure materialStructure=materialData.getPipelineStructure();
		int elementIndex=materialData.generateElement();
		SFStructureReference materialReference=new SFStructureReference(materialData,elementIndex); 
		SFStructureData mat=new SFStructureData(materialStructure);
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
