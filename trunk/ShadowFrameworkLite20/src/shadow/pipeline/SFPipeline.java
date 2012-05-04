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
		
		private SFProgramComponent[] components;
		private SFProgram program;
		
		private SFProgramTrace(SFProgramComponent[] components) {
			super();
			this.components = components;
		}

		@Override
		public int compareTo(SFProgramTrace o) {
			if(o.components.length!=components.length)
				return o.components.length-components.length;
			
			for (int i = 0; i < components.length; i++) {
				int compare=components[i].hashCode()-o.components[i].hashCode();
				if(compare!=0)
					return compare;
			}
			
			return 0;
		}
	}
	
	
	// Pipeline components.
	private HashMap<String, SFProgramComponent> components=new HashMap<String, SFProgramComponent>();
	private HashMap<String, SFPipelineGrid> grids=new HashMap<String, SFPipelineGrid>();
	private HashMap<String, SFPipelineStructure> structures=new HashMap<String, SFPipelineStructure>();
	
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

	public static SFPipelineElement getModule(String structureCode) {
		SFPipelineStructure structure=pipeline.structures.get(structureCode);
		if (structure != null) {
			return structure;
		}
		SFProgramComponent component=pipeline.components.get(structureCode);
		if (component != null) {
			return component;
		}
		return pipeline.grids.get(structureCode);
	}
	

	public static SFPipelineStructure getStructure(String structureCode) {
		return pipeline.structures.get(structureCode);
	}

	// Pipeline components.
	public static void loadStructure(String code, SFPipelineStructure component) {
		pipeline.structures.put(code,component);
	}

	// Pipeline components.
	public static void loadGrid(String code, SFPipelineGrid component) {
		pipeline.grids.put(code,component);
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
	public static SFProgram getStaticProgram(SFPrimitive primitive, String material[], String light) {

		SFProgramTrace trace=generateTrace(primitive, material, light);
		
		int index = Collections.binarySearch(pipeline.traces, trace); 
		
		if(index>=0){
		
			return pipeline.traces.get(index).program;
			
		}else{
			SFProgram program=pipeline.sfProgramBuilder.generateNewProgram();

			program.setPrimitive(primitive);
			
			for (int i=0; i < material.length; i++) {
				program.setMaterial(i,pipeline.components.get(material[i]));
			}
			program.setLightStep(pipeline.components.get(light));
			
			trace.program=program;
			
			pipeline.traces.add(trace);
			Collections.sort(pipeline.traces);
			
			// Now program is returned, and it is ready to be used
			return program;
		}
		
		
		

	}

	public static SFProgramTrace generateTrace(SFPrimitive primitive, String[] material, String light) {
		int primitiveComponents=0;
		if(primitive!=null)
			primitiveComponents=primitive.getComponents().length;
		int componentSize=primitiveComponents+material.length+1;
		SFProgramComponent[] components = new SFProgramComponent[componentSize];
		for (int i = 0; i < primitiveComponents; i++) {
			components[i]=primitive.getComponents()[i];
		}
		for (int i = 0; i < material.length; i++) {
			components[i+primitiveComponents]=pipeline.components.get(material[i]);
		}
		components[components.length-1]=pipeline.components.get(light);
		
		SFProgramTrace trace=new SFProgramTrace(components);
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
	public static SFProgram getStaticImageProgram(String material[], String light) {

		SFProgramTrace trace=generateTrace(null, material, light);
		
		int index = Collections.binarySearch(pipeline.traces, trace); 
		
		if(index>=0){
		
			return pipeline.traces.get(index).program;
			
		}else{
			SFProgram program=pipeline.sfProgramBuilder.generateImageProgram();
			
			for (int i=0; i < material.length; i++) {
				program.setMaterial(i,pipeline.components.get(material[i]));
			}
			program.setLightStep(pipeline.components.get(light));
			
			trace.program=program;
			
			pipeline.traces.add(trace);
			Collections.sort(pipeline.traces);
			
			// Now program is returned, and it is ready to be used
			return program;
		}
	}
	
	
}