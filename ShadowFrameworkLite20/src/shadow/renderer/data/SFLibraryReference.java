package shadow.renderer.data;

import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.objects.SFPrimitiveType;

/**
 * A simple DataObject which can keep a Reference to a Dataset stored or
 * retrievable through the DataCenter
 * 
 * @author Alessandro Martinelli
 */
public class SFLibraryReference<T extends SFDataset> extends SFPrimitiveType {

	public static final String NULL_REFERENCE = "0";

	private String datasetName = NULL_REFERENCE;
	private T dataset = null;

	private class SFLibraryReferenceListener implements SFDataCenterListener<T> {
		private SFDataCenterListener<T> listener;

		public SFLibraryReferenceListener(SFDataCenterListener<T> listener) {
			super();
			this.listener = listener;
		}

		@Override
		public void onDatasetAvailable(String name, T dataset) {
			SFLibraryReference.this.dataset = dataset;
			listener.onDatasetAvailable(name, dataset);
		}
	}
	

	public SFLibraryReference() {
		super();
	}

	public SFLibraryReference(T dataset) {
		super();
		this.dataset=dataset;
	}
	

	@Override
	public SFLibraryReference<T> clone() {
		return new SFLibraryReference<T>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void readFromStream(SFInputStream stream) {
		this.datasetName = stream.readString();
		if (datasetName.equalsIgnoreCase(NULL_REFERENCE)) {
			this.dataset = (T) (SFDataCenter.getDataCenter().readDataset(stream));
		}
	}

	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeString(this.datasetName);
		if (datasetName.equalsIgnoreCase(NULL_REFERENCE)) {
			SFDataCenter.getDataCenter().writeDataset(stream, dataset);
		}
	}

	public void setReference(String name) {
		this.datasetName = name;
	}

	public String getReference() {
		return datasetName;
	}

	public void setDataset(T dataset) {
		this.dataset = dataset;
	}
	
	public T getDataset() {
		return dataset;
	}

	/**
	 * Ask for this referenced Dataset to be retrieved from Datacenter.
	 * 
	 * @param listener
	 */
	public void retrieveDataset(SFDataCenterListener<T> listener) {
		if (!datasetName.equalsIgnoreCase(NULL_REFERENCE)) {
			SFDataCenter.getDataCenter().makeDatasetAvailable(datasetName,
					new SFLibraryReferenceListener(listener));
			return;
		}
		listener.onDatasetAvailable(datasetName, dataset);

	}
}
