package shadow.pipeline.java;

import shadow.image.SFPipelineTexture;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.system.SFInitiable;

public interface SFGL20GenericProgram  extends SFProgram, SFInitiable{

	public abstract void setData(Module module,int index, SFStructureData data);

	public abstract void setData(Module module,int index, SFPipelineTexture texture);
}