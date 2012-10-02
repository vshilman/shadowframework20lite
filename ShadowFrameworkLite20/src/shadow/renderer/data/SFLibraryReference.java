package shadow.renderer.data;

import shadow.system.SFInitiable;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.objects.SFPrimitiveType;

/**
 * A simple DataObject which can keep a Reference to a Dataset stored or
 * retrievable through the DataCenter
 * 
 * @author Alessandro Martinelli
 */
public class SFLibraryReference<T extends SFInitiable> extends SFPrimitiveType{

	public static final String NULL_REFERENCE = "0";
	public static final String MISSING_REFERENCE = "0=0";

	private String datasetName = NULL_REFERENCE;
	private SFDataAsset<T> dataset = null;

	private class SFLibraryReferenceListener implements SFDataCenterListener<SFDataAsset<T>> {
		private SFDataCenterListener<SFDataAsset<T>> listener;

		public SFLibraryReferenceListener(SFDataCenterListener<SFDataAsset<T>> listener) {
			super();
			this.listener = listener;
		}

		@Override
		public void onDatasetAvailable(String name, SFDataAsset<T> dataset) {
			SFLibraryReference.this.dataset = dataset;
			listener.onDatasetAvailable(name, dataset);
		}
	}
	

	public SFLibraryReference() {
		super();
	}

	public SFLibraryReference(SFDataAsset<T> dataset) {
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
		this.datasetName = stream.readName();
		if (datasetName.equalsIgnoreCase(MISSING_REFERENCE)) {
			this.datasetName=NULL_REFERENCE;
			this.dataset=null;
		}else if (datasetName.equalsIgnoreCase(NULL_REFERENCE)) {
			this.dataset = (SFDataAsset<T>) (SFDataCenter.getDataCenter().readDataset(stream));
		}
	}

	@Override
	public void writeOnStream(SFOutputStream stream) {
		if (datasetName.equalsIgnoreCase(NULL_REFERENCE) && dataset==null) {
			stream.writeName(MISSING_REFERENCE);
			return;
		}
		stream.writeName(this.datasetName);
		
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

	//TODO: rename as setDataAsset
	public void setDataset(SFDataAsset<T> dataset) {
		this.dataset = dataset;
	}
	
	//TODO: rename as getDataAsset
	public SFDataAsset<T> getDataset() {
		return dataset;
	}

	/**
	 * Ask for this referenced Dataset to be retrieved from Datacenter.
	 * 
	 * @param listener
	 */
	public void retrieveDataset(SFDataCenterListener<SFDataAsset<T>> listener) {
		if (!datasetName.equalsIgnoreCase(NULL_REFERENCE)) {
			SFDataCenter.getDataCenter().makeDatasetAvailable(datasetName,
					new SFLibraryReferenceListener(listener));
			return;
		}
		listener.onDatasetAvailable(datasetName, dataset);

	}
}
