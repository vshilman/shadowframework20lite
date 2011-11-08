package shadow.pipeline;

import shadow.pipeline.parameters.SFPipelineRegister;

public class SFPrimitiveIndicesAssociation {
	
	private SFPipelineRegister register;
	private int[] indices;
	
	public SFPrimitiveIndicesAssociation(SFPipelineRegister register, int[] indices) {
		super();
		this.register = register;
		this.indices = indices;
	}
	
	public SFPipelineRegister getRegister() {
		return register;
	}
	
	public int[] getIndices() {
		return indices;
	}

}
