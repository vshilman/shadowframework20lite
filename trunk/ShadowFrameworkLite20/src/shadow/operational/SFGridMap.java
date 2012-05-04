package shadow.operational;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.expression.SFValuesMap;

public class SFGridMap implements SFValuesMap {

	private SFPipelineGrid grid;
	private SFValuenf[] values;
	private int[] indices;
	private int indicesOffset;
	
	public SFGridMap(SFPipelineGrid grid,SFValuenf[] values,int[] indices,int indicesOffset) {
		super();
		this.grid = grid;
		this.values = values;
		this.indices= indices;
		this.indicesOffset=indicesOffset;
	}

	public SFValuenf getValue(String name){
		int index=grid.getNameIndex(name);
		if(index==-1)
			throw new ArrayIndexOutOfBoundsException();
		int id=indices[index+indicesOffset];
		//System.err.println("id "+id);
		SFValuenf value=values[id]; 
		return value; 
	}
	
	@Override
	public SFValuenf generateValue() {
		return new SFValuenf(values[0].get().length);
	}
}
