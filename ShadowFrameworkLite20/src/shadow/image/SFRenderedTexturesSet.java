package shadow.image;

import shadow.system.SFInitiable;
import shadow.system.SFUpdatable;

public interface SFRenderedTexturesSet extends SFInitiable,SFUpdatable{

	public SFPipelineTexture getTexture(int index);
	
	public int getTextureSize();
	
}
