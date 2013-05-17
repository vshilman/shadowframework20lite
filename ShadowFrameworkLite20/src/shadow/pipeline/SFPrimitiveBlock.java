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
	TXO2(SFPipelineRegister.getFromName("Tx1")),
	TXO3(SFPipelineRegister.getFromName("Tx2")),
	TXO4(SFPipelineRegister.getFromName("Tx3")),
	DATA1(SFPipelineRegister.getFromName("prData01")),
	DATA2(SFPipelineRegister.getFromName("prData02")),
	DATA3(SFPipelineRegister.getFromName("prData03")),
	DATA4(SFPipelineRegister.getFromName("prData04")),
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
