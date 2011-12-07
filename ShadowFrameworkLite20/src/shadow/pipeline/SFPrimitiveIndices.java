package shadow.pipeline;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;


/**
 * The Description of All the Indices Being part of a Primitive
 * 
 * @author Alessandro Martinelli
 */
public class SFPrimitiveIndices {

	int primitiveIndices[][];
	
	private SFPrimitiveIndices(){
		
	}
	
	public SFPrimitiveIndices(SFPrimitive primitive){
		
		List<Entry<SFPipelineRegister, SFProgramComponent>> map=primitive.getPrimitiveMap();
		
		primitiveIndices=new int[map.size()][];
		
		int index=0;
		
		for (Iterator<Entry<SFPipelineRegister, SFProgramComponent>> iterator = map.iterator(); 
				iterator.hasNext();index++) {
			Entry<SFPipelineRegister, SFProgramComponent> entry=iterator.next();
			
			SFProgramComponent component=entry.getValue();
			
			//System.out.println("component.getName() "+component.getName());
			//NOTE:this is a bit long.Its based on the idea that primitive ProgramComponent are 
			//using always only 1 grid; that's the reason of getGrids().iterator().next().
			 
			LinkedList<SFParameteri> parameters=component.getGrid().getParameters();
			
			primitiveIndices[index]=new int[parameters.size()];
			
		}	
	
	}
	
	public void setData(SFPrimitiveIndices indices,int registerIndex){
		for (int j = 0; j < primitiveIndices[registerIndex].length; j++) {
			primitiveIndices[registerIndex][j]=indices.primitiveIndices[registerIndex][j];
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
	
	public SFPrimitiveIndices clone(){
		SFPrimitiveIndices indices=new SFPrimitiveIndices();
		indices.primitiveIndices=new int[this.primitiveIndices.length][];
		for (int i = 0; i < indices.primitiveIndices.length; i++) {
			indices.primitiveIndices[i]=new int[this.primitiveIndices[i].length];
		}
		return indices;
	}
}
