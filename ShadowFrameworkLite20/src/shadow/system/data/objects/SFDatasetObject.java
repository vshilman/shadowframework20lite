package shadow.system.data.objects;

import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFDatasetObject<T extends SFDataset> extends SFPrimitiveType {

	private T dataset;
	
	public SFDatasetObject(T dataset) {
		super();
		this.dataset = dataset;
	}

	public T getDataset() {
		return dataset;
	}

	public void setDataset(T dataset) {
		this.dataset = dataset;
	}

	@Override
	public SFDatasetObject<T> clone() {
		return new SFDatasetObject<T>(null);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void readFromStream(SFInputStream stream) {
		SFDataset dataset=SFDataCenter.getDataCenter().readDataset(stream);
		this.dataset=(T)dataset;
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		SFDataCenter.getDataCenter().writeDataset(stream, dataset);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof SFDatasetObject<?>))
			return false;
		return ((SFDatasetObject<?>)obj).dataset==dataset;
	}
}
