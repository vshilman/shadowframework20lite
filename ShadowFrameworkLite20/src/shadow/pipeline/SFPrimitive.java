package shadow.pipeline;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import shadow.pipeline.parameters.SFPipelineRegister;

/**
 * A complete description of a Geometric Primitive.
 * 
 * 		- It's tessellator.
 * 		- The Map of Primitive and Slots. 
 * 
 * @author Alessandro Martinelli
 */
public class SFPrimitive {

	private SFProgramComponent tessellator; 
	
	private List<Entry<SFPipelineRegister, SFProgramComponent>> primitiveMap=new 
		LinkedList<Entry<SFPipelineRegister, SFProgramComponent>>();
	
	public SFPrimitive() {
		super();
	}
	
	public boolean containRegister(SFPipelineRegister register){
		for (Entry<SFPipelineRegister, SFProgramComponent> entry : primitiveMap) {
			if(register==entry.getKey())
				return true;
		}
		return false;
	}
	
	public void addPrimitiveElement(SFPipelineRegister register,SFProgramComponent component){
		primitiveMap.add(new AbstractMap.SimpleEntry<SFPipelineRegister,SFProgramComponent>(register,component));
	}

	public List<Entry<SFPipelineRegister, SFProgramComponent>> getPrimitiveMap(){
		return primitiveMap;
	}

	public SFProgramComponent getTessellator() {
		return tessellator;
	}

	public void setAdaptingTessellator(SFProgramComponent adaptingTessellator) {
		this.tessellator=adaptingTessellator;
	}
}
