package shadow.system.data.objects;

import shadow.system.data.SFDataAsset;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFDataAssetObject<T> implements SFDataObject {

	private SFDataAsset<T> dataset;
	
	public SFDataAssetObject(SFDataAsset<T> dataset) {
		super();
		this.dataset = dataset;
	}

	public SFDataAsset<T> getDataAsset() {
		return dataset;
	}

	public void setDataAsset(SFDataAsset<T> dataset) {
		this.dataset = dataset;
	}

	@Override
	public SFDataAssetObject<T> copyDataObject() {
		return new SFDataAssetObject<T>(null);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void readFromStream(SFInputStream stream) {
		SFDataAsset<T> dataset=(SFDataAsset<T>)SFDataCenter.getDataCenter().readDataset(stream,null);
		this.dataset=dataset;
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		SFDataCenter.getDataCenter().writeDataset(stream, dataset);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof SFDataAssetObject<?>))
			return false;
		return ((SFDataAssetObject<?>)obj).dataset==dataset;
	}

}
