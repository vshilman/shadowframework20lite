package shadow.pipeline.java;

import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureData;
import shadow.system.SFInitiable;

public interface SFGL20GenericProgram  extends SFProgram, SFInitiable{

	public abstract void setData(SFPipelineStructure structure,
			SFStructureData data);
	
}