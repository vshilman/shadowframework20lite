package shadow.pipeline.java;

import shadow.image.SFPipelineTexture;
import shadow.math.SFValuenf;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.SFPipelineGraphics.Module;

public interface SFProgramDataModel {

	public void setTransformData(float[] transform);

	public void setIndexedData(SFPrimitiveIndices indices,
			SFGL20ValuenfArray[] datas,
			SFPrimitive primitive);

	public void sendVertex(SFValuenf valu
);

	public void setData(Module module,int index, SFStructureData data);

	public void setData(Module module,int index, SFPipelineTexture texture);

}