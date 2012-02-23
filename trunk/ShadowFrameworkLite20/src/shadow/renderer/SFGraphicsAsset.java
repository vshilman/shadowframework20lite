package shadow.renderer;

import shadow.system.data.SFDataset;

public abstract class SFGraphicsAsset extends SFAsset implements SFDataset{

	public abstract void allocateGraphicsMemory();
	
	public abstract void freeGraphicsMemory();
	
	
}