package shadow.pipeline.openGL20;

import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureData;
import shadow.system.SFInitiable;

public interface SFGL20GenericProgram extends SFProgram,SFInitiable{

	public abstract void setData(SFPipelineStructureInstance structure,
			SFStructureData data);

}