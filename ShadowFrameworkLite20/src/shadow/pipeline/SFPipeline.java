package shadow.pipeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 * The Shadow Framework Pipeline is a Virtual Rendering Pipeline which the
 * ShadowFramework will implement through some OpenGL version.
 * 
 * The ShadowFramework Pipeline allows a combination of five ShaderComponents,
 * one for each programmable Virtual Shadow Framework Rendering steps:
 * Tessellation, Primitive, Transform, Material and Lighting.
 * 
 * @author Alessandro Martinelli
 */
public class SFPipeline {
	
	private static class SFProgramTrace implements Comparable<SFProgramTrace>{
		
		private String[] modulesNames;
		private SFProgram program;
		
		private SFProgramTrace(String[] modulesNames) {
			super();
			this.modulesNames = modulesNames;
		}

		@Override
		public int compareTo(SFProgramTrace o) {
			if(modulesNames.length!=o.modulesNames.length)
				return (modulesNames.length-o.modulesNames.length);
			for (int i = 0; i < modulesNames.length; i++) {
				int compare=modulesNames[i].compareTo(o.modulesNames[i]);
				if(compare!=0)
					return compare;
			}
			
			return 0;
		}
	}
	
	
	// Pipeline components.
	private HashMap<String, SFProgramComponent> components=new HashMap<String, SFProgramComponent>();
	private HashMap<String, SFProgramModule> modules=new HashMap<String, SFProgramModule>();
	private HashMap<String, SFPipelineStructure> structures=new HashMap<String, SFPipelineStructure>();
	private HashMap<String, SFPrimitive> primitives=new HashMap<String, SFPrimitive>();
	
	private List<SFProgramTrace> traces=new ArrayList<SFProgramTrace>();

	private SFProgramBuilder sfProgramBuilder;
	private SFPipelineGraphics sfPipelineGraphics;
	private SFPipelineMemory sfPipelineMemory;
	private SFTexturePipeline sfTexturePipeline;

	private static SFPipeline pipeline=new SFPipeline();
	
	private SFPipeline() {
		super();
	}

	public static SFPipeline getPipeline() {
		return pipeline;
	}

	public static void setSfTexturePipeline(SFTexturePipeline sfTexturePipeline) {
		pipeline.sfTexturePipeline=sfTexturePipeline;
	}

	public static SFProgramBuilder getSfProgramBuilder() {
		return pipeline.sfProgramBuilder;
	}

	public static void setSfProgramBuilder(SFProgramBuilder sfProgramBuilder) {
		pipeline.sfProgramBuilder=sfProgramBuilder;
	}
	
	public static SFTexturePipeline getSfTexturePipeline() {
		return pipeline.sfTexturePipeline;
	}
	
	public static SFPipelineMemory getSfPipelineMemory() {
		return pipeline.sfPipelineMemory;
	}

	public static void setSfPipelineMemory(SFPipelineMemory sfPipelineMemory) {
		pipeline.sfPipelineMemory = sfPipelineMemory;
	}

	public static SFPipelineGraphics getSfPipelineGraphics() {
		return pipeline.sfPipelineGraphics;
	}

	public static void setSfPipelineGraphics(
			SFPipelineGraphics sfPipelineGraphics) {
		pipeline.sfPipelineGraphics=sfPipelineGraphics;
	}

	// Pipeline components.
	public static void loadShaderComponent(String code,
			SFProgramComponent component) {
		pipeline.components.put(code,component);
	}
	
	// Pipeline components.
	public static void loadPrimitive(String code,
			SFPrimitive component) {
		pipeline.primitives.put(code,component);
	}

	// Pipeline components.
	public static void loadShaderModule(String code,
			SFProgramModule component) {
		pipeline.modules.put(code,component);
	}


	public static SFPipelineElement getModule(String structureCode) {
		SFPipelineStructure structure=pipeline.structures.get(structureCode);
		if (structure != null) {
			return structure;
		}
		return pipeline.components.get(structureCode);
	}
	
	public static SFProgramModule getProgramModule(String name){
		SFProgramModule module=pipeline.modules.get(name);
		if(module==null){
			SFProgramComponent component=(SFProgramComponent)(getModule(name));
			if(component!=null){
				module=new SFProgramModule();
				SFProgramComponent[] components={component};
				module.setComponents(components);
			}
		}
		return module;
	}

	public static SFPrimitive getPrimitive(String name){
		SFPrimitive module=pipeline.primitives.get(name);
		return module;
	}

	public static SFPipelineStructure getStructure(String structureCode) {
		return pipeline.structures.get(structureCode);
	}

	// Pipeline components.
	public static void loadStructure(String code, SFPipelineStructure component) {
//		if(pipeline.structures.containsKey(code))
//			throw new RuntimeException("Structure "+code+" has already been loaded");
		pipeline.structures.put(code,component);
	}

	/**
	 * Generate a unique Static SFShader given its parameters, or create it if
	 * it does not exists.
	 * 
	 * @param tessellator
	 * @param primitive
	 * @param transform
	 * @param material
	 * @param light
	 * @return
	 */
	public static SFProgram getStaticProgram(SFPrimitive primitive, String transform, String material, String light) {

		SFProgramTrace trace=generateTrace(primitive.getName(), transform, material, light);
		
		int index = Collections.binarySearch(pipeline.traces, trace); 
		
		if(index>=0){
		
			return pipeline.traces.get(index).program;
			
		}else{
			SFProgram program=pipeline.sfProgramBuilder.generateNewProgram();

			program.setPrimitive(primitive);
			SFProgramModule transformP=getProgramModule(transform);
			SFProgramModule materialP=getProgramModule(material);
			SFProgramModule lightP=getProgramModule(light);
			program.setTransform(transformP);
			program.setMaterial(materialP);
			program.setLightStep(lightP);
			trace.program=program;
			
			pipeline.traces.add(trace);
			Collections.sort(pipeline.traces);
			
			// Now program is returned, and it is ready to be used
			return program;
		}
		
		
		

	}

	public static SFProgramTrace generateTrace(String primitive, String transform, String material, 
			String light) {
		
		String[] modules = {primitive,transform,material,light};
	
		SFProgramTrace trace=new SFProgramTrace(modules);
		return trace;
	}
	
	public static SFProgramTrace generateTrace(String material, 
			String light) {
		
		String[] modules = {material,light};
	
		SFProgramTrace trace=new SFProgramTrace(modules);
		return trace;
	}

	/**
	 * Generate a unique Static SFShader given its parameters, or create it if
	 * it does not exists.
	 * 
	 * @param tessellator
	 * @param primitive
	 * @param transform
	 * @param material
	 * @param light
	 * @return
	 */
	public static SFProgram getStaticImageProgram(String material, String light) {

		SFProgramTrace trace=generateTrace(material, light);
		
		int index = Collections.binarySearch(pipeline.traces, trace); 
		
		if(index>=0){
		
			return pipeline.traces.get(index).program;
			
		}else{
			SFProgram program=pipeline.sfProgramBuilder.generateImageProgram();

			program.setMaterial(getProgramModule(material));
			program.setLightStep(getProgramModule(light));
			
			trace.program=program;
			
			pipeline.traces.add(trace);
			Collections.sort(pipeline.traces);
			
			// Now program is returned, and it is ready to be used
			return program;
		}
	}
	
}