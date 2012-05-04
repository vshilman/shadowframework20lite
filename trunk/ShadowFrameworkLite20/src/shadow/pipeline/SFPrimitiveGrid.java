package shadow.pipeline;

import java.util.LinkedList;

import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.pipeline.parameters.SFParameteri;

public class SFPrimitiveGrid {

	private PrimitiveBlock block;
	private SFPipelineGrid grid = new SFPipelineGrid();
	private short type;
	
	public SFPrimitiveGrid(PrimitiveBlock block,SFPipelineGridInstance gridInstance){
		this.block=block;
		this.grid=gridInstance.getGrid();
		type=gridInstance.getParameters().get(0).getType();
		if(type==SFParameteri.GLOBAL_GENERIC)
			type=block.getType();
	}

	public PrimitiveBlock getBlock() {
		return block;
	}

	public SFPipelineGrid getGrid() {
		return grid;
	}

	public short getType() {
		return type;
	}

	
}
