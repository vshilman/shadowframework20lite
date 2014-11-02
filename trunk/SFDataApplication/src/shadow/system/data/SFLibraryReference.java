package shadow.system.data;

import shadow.system.SFAsset;
import shadow.system.SFAssetsLibrary;
import shadow.system.SFSimpleAsset;

/**
 * A simple DataObject which can keep a Reference to a Dataset stored or
 * retrievable through the DataCenter
 * 
 * @author Alessandro Martinelli
 */
public class SFLibraryReference<T> implements SFDataObject {

	public static final String NULL_REFERENCE = "0";
	public static final String MISSING_REFERENCE = "0=0";

	private String datasetName = NULL_REFERENCE;
	private SFDataAsset<T> dataset = null;
	private SFAsset<T> asset;
	private int index = -1;
	
	public SFLibraryReference() {
		super();
	}

	public SFLibraryReference(SFDataAsset<T> dataset) {
		super();
		this.dataset=dataset;
	}

	@Override
	public SFLibraryReference<T> copyDataObject() {
		SFLibraryReference<T> ref=new SFLibraryReference<T>();
		ref.datasetName=datasetName;
		if(dataset!=null)
			ref.dataset=(SFDataAsset<T>)(dataset.copyDataset());
		return ref;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void readFromStream(SFInputStream stream) {
		this.datasetName = stream.readName();
		if (datasetName.equalsIgnoreCase(MISSING_REFERENCE)) {
			this.datasetName=NULL_REFERENCE;
			this.dataset=null;
		}else if (isNullReference()) {
			this.dataset = (SFDataAsset<T>) (SFDataCenter.getDataCenter().readDataset(stream,null));
		}
	}

	public boolean isNullReference() {
		return datasetName.equalsIgnoreCase(NULL_REFERENCE);
	}

	@Override
	public void writeOnStream(SFOutputStream stream) {
		if (isNullReference() && dataset==null) {
			stream.writeName(MISSING_REFERENCE);
			return;
		}
		stream.writeName(this.datasetName);
		
		if (isNullReference()) {
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
	
	public SFDataAsset<T> getDataAsset() {
		
		if (dataset==null && !isNullReference()) {
			SFDataAsset<T> asset=SFDataCenter.makeDatasetAvailable(datasetName);
			dataset=asset;
		}
		return dataset;
	}
	
	public T getResource() {
		if (!isNullReference()) {
			SFDataAsset<T> asset=SFDataCenter.makeDatasetAvailable(datasetName);
			return asset.getResource();
		}
		if(dataset==null)
			return null;
		return dataset.getResource();
	}
	
	public SFAsset<T> getAsset() {
		if(isNullReference()){
			return new SFSimpleAsset<T>(dataset.getResource());
		}else {
			if(index==-1){
				index=SFDataCenter.getDataCenter().getDictionary().getIndex(this.datasetName);
			}
			asset=SFAssetsLibrary.getLibrary().makeAssetAvailable(index);
		}
		return asset;
	}

}
