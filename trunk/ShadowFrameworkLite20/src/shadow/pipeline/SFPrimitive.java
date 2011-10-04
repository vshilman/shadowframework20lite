package shadow.pipeline;

import java.util.HashMap;
import java.util.Map;

import shadow.pipeline.parameters.SFPipelineRegister;

/**
 * A complete description of a Geometric Primitive.
 * 
 * 		- It's tessellator.
 * 		- The Map of Primitive and Slots. 
 * 		- The List of Transforms.
 * 
 * @author Alessandro Martinelli
 */
public class SFPrimitive {

	private SFProgramComponent tessellator; 
	
	private HashMap<SFPipelineRegister, SFProgramComponent> primitiveMap=new 
		HashMap<SFPipelineRegister, SFProgramComponent>();

	public SFPrimitive() {
		super();
	}
	
	public void addPrimitiveElement(SFPipelineRegister register,SFProgramComponent component){
		primitiveMap.put(register,component);
	}

	public Map<SFPipelineRegister, SFProgramComponent> getPrimitiveMap(){
		return primitiveMap;
	}

	public SFProgramComponent getTessellator() {
		return tessellator;
	}

	public void setAdaptingTessellator(SFProgramComponent adaptingTessellator) {
		this.tessellator=adaptingTessellator;
	}
}
