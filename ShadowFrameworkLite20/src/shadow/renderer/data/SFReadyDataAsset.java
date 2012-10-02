package shadow.renderer.data;

import shadow.system.SFInitiable;

public class SFReadyDataAsset<T extends SFInitiable> extends SFDataAsset<T>{

	private T resource;
	
	public SFReadyDataAsset(T resource) {
		super();
		this.resource = resource;
	}

	@Override
	protected T buildResource() {
		return resource;
	}
}
