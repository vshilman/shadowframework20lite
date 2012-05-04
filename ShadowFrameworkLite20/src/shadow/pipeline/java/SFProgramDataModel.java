package shadow.pipeline.java;

import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFStructureData;

public interface SFProgramDataModel {

	public void setData(SFPipelineStructure structure,
			SFStructureData data);

	public void setTransformData(float x, float y, float z, float rx, float ry,
			float rz);
	

	public void setTransformData(float[] transform);

	public void setIndexedData(SFPrimitiveIndices indices,
			SFGL20ValuenfArray[] datas,
			SFPrimitive primitive);

}