package shadow.system.data.objects;

import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFDatasetObject extends SFPrimitiveType {

	private SFDataset dataset;
	
	public SFDatasetObject(SFDataset dataset) {
		super();
		this.dataset = dataset;
	}

	public SFDataset getDataset() {
		return dataset;
	}

	public void setDataset(SFDataset dataset) {
		this.dataset = dataset;
	}

	@Override
	public SFPrimitiveType clone() {
		return new SFDatasetObject(null);
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		String code=stream.readString();
		SFDataset dataset=SFDataCenter.getDataCenter().createDataset(code);
		dataset.getSFDataObject().readFromStream(stream);
		this.dataset=dataset;
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeString(dataset.getType());
		dataset.getSFDataObject().writeOnStream(stream);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof SFDatasetObject))
			return false;
		return ((SFDatasetObject)obj).dataset==dataset;
	}
}
