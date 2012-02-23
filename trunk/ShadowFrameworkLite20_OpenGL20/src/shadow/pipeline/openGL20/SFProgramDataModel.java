package shadow.pipeline.openGL20;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.parameters.SFPipelineRegister;

public interface SFProgramDataModel {

	public void setData(SFPipelineStructure structure,
			SFStructureData data);

	public void setTransformData(float x, float y, float z, float rx, float ry,
			float rz);

	public void setIndexedData(SFPrimitiveIndices indices,
			SFGL20ListData<SFValuenf>[] datas, Integer[][] uniforms,
			SFPipelineRegister[] registers);

}