package shadow.pipeline;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;


/**
 * The Description of All the Indices Being part of a Primitive
 * 
 * @author Alessandro Martinelli
 */
public class SFPrimitiveIndices {

	int primitiveIndices[][];
	
	public SFPrimitiveIndices(SFPrimitive primitive){
		
		Map<SFPipelineRegister, SFProgramComponent> map=primitive.getPrimitiveMap();
		
		primitiveIndices=new int[map.size()][];
		
		Collection<SFProgramComponent> collection=map.values();
		int index=0;
		
		for (Iterator<SFProgramComponent> iterator = collection.iterator(); 
				iterator.hasNext();index++) {
			SFProgramComponent component=iterator.next();
			
			System.out.println("component.getName() "+component.getName());
			//NOTE:this is a bit long.Its based on the idea that primitive ProgramComponent are 
			//using always only 1 grid; that's the reason of getGrids().iterator().next().
			LinkedList<SFParameteri> parameters=component.getGrids().iterator().next().getParameters();
			
			primitiveIndices[index]=new int[parameters.size()];
		}
		
	}

	public void set(SFPrimitiveIndices indices){
		for (int i = 0; i < primitiveIndices.length; i++) {
			for (int j = 0; j < primitiveIndices[i].length; j++) {
				primitiveIndices[i][j]=indices.primitiveIndices[i][j];
			}
		}
	}

	public int[][] getPrimitiveIndices() {
		return primitiveIndices;
	}

	public void setPrimitiveIndices(int[][] primitiveIndices) {
		this.primitiveIndices = primitiveIndices;
	}
	
	
}
