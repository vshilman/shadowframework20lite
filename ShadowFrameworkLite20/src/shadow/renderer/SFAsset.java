package shadow.renderer;

import shadow.system.SFInitiable;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;

public abstract class SFAsset  implements SFDataset, SFInitiable{

	private SFDataObject sfDataObject;

	public SFAsset() {
		super();
	}

	@Override
	public SFDataObject getSFDataObject() {
		return sfDataObject;
	}

	@Override
	public String getType() {
		return getClass().getSimpleName();
	}

	protected void setData(SFDataObject sfDataObject) {
		this.sfDataObject = sfDataObject;
	}

}