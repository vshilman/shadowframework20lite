package shadow.pipeline.openGL20;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.parameters.SFPipelineRegister;

public interface SFProgramDataModel {

	public void setData(SFPipelineStructureInstance structure,
			SFStructureData data);

	public void setTransformData(float x, float y, float z);

	public void setIndexedData(SFPrimitiveIndices indices,
			SFGL20ListData<SFValuenf>[] datas,Integer[][] uniforms,SFPipelineRegister[] registers);

}