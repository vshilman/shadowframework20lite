package shadow.pipeline;

import shadow.pipeline.parameters.SFPipelineRegister;

/**
 * A Primitive Block
 * @author Alessandro Martinelli
 */
public enum SFPrimitiveBlock {
	TESSELLATOR(null),
	POSITION(SFPipelineRegister.getFromName("P")),
	NORMAL(SFPipelineRegister.getFromName("N")),
	DU(SFPipelineRegister.getFromName("du")),
	DV(SFPipelineRegister.getFromName("dv")),
	TXO(SFPipelineRegister.getFromName("Tx0")),
	UV(SFPipelineRegister.getFromName("uv")),
	UVP(SFPipelineRegister.getFromName("uvP"));
	private SFPipelineRegister register;

	private SFPrimitiveBlock(SFPipelineRegister register) {
		this.register = register;
	}
	
	public SFPipelineRegister getRegister(){
		return register;
	}
	
	public short getType(){
		return register.getType();
	}
	
	public static SFPrimitiveBlock getBlock(SFPipelineRegister register){
		SFPrimitiveBlock[] blocks=SFPrimitiveBlock.values();
		for (int i = 0; i < blocks.length; i++) {
			if(blocks[i].getRegister()==register){
				return blocks[i];
			}
		}
		return POSITION;
	}

	public int getIndex() {
		return ordinal();
	}

	public static SFPrimitiveBlock getBlock(int index){
		return values()[index];
	}
}
