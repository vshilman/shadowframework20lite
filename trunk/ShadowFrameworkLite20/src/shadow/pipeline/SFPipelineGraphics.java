package shadow.pipeline;

import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipelineRenderingState.AccumulatorOperation;


public interface SFPipelineGraphics {
	
	public void setupProjection(float projection[]);
	
	public void translateModel(SFVertex3f modelPosition);
	
	public void rotateModel(float rotX,float rotY,float rotZ);
	
	public void setupTransform(float[] transform);
	
	public void drawPrimitives(SFPrimitiveArray primitives,int first,int count);
	
	public void loadStructureData(SFStructureArray array,int indexOfData);
	
	public void drawBaseQuad();
	
	public void setPipelineState(SFPipelineRenderingState state);
	
	public void executeAccumulationOperation(AccumulatorOperation operation,float value);
	
}
