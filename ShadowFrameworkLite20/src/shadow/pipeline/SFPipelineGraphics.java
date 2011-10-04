package shadow.pipeline;


public interface SFPipelineGraphics {
	
	public void setModelPosition(float modelX,float modelY,float modelZ);
	
	public void drawPrimitives(SFPrimitiveArray primitives,int first,int count);
	
	public void loadStructureData(SFStructureArray array,int indexOfData);
	
}
