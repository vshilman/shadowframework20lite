package shadow.pipeline;

import shadow.image.SFPipelineTexture;
import shadow.pipeline.SFPipelineRenderingState.AccumulatorOperation;


public interface SFPipelineGraphics {
	
	public enum Module{
		TRANSFORM,
		MATERIAL,
		LIGHT
	}
	
	public void setupProjection(float projection[]);
	
	public void setupTransform(float[] transform);
	
	public float[] getProjection();
	
	public float[] getTransform();

	public void drawCompiledPrimitives(SFPrimitiveArray primitives,int compiledGeometry);
	
	public void drawCompiledPointsCloud(SFPrimitiveArray primitives,int compiledGeometry);
	
	public void drawPrimitives(SFPrimitiveArray primitives,int first,int count);
	
	public void drawPointsCloud(SFPrimitiveArray primitives,int first,int count);

	public int compilePrimitiveArray(SFPrimitiveArray array,int firstElement,int lastElement);

	public int compilePointsCloud(SFPrimitiveArray array,int firstElement,int lastElement);
	
	public void updateCompiledPrimitive(SFPrimitiveArray array,int compiled);
	
	public void updateCompiledPointsCloud(SFPrimitiveArray array,int compiled);
	
	public void loadStructureData(Module module,SFStructureArray array, int inProgramIndex ,int indexOfData);
	
	public void loadTexture(Module module,SFPipelineTexture texture ,int indexOfTexture);
	
	public void drawBaseQuad();
	
	public void setPipelineState(SFPipelineRenderingState state);
	
	public void executeAccumulationOperation(AccumulatorOperation operation,float value);
	
}
