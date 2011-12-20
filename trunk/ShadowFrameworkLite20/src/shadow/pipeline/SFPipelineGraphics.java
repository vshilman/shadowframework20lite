package shadow.pipeline;


public interface SFPipelineGraphics {
	
	public void translateModel(float modelX,float modelY,float modelZ);
	
	public void rotateModel(float rotX,float rotY,float rotZ);
	
	public void drawPrimitives(SFPrimitiveArray primitives,int first,int count);
	
	public void loadStructureData(SFStructureArray array,int indexOfData);
	
	public void drawBaseQuad();
	
}
