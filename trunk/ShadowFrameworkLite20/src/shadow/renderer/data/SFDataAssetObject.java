package shadow.renderer.data;

import shadow.system.SFInitiable;
import shadow.system.data.objects.SFDatasetObject;

public class SFDataAssetObject<T extends SFInitiable> extends SFDatasetObject<SFDataAsset<T>>{

	public SFDataAssetObject(SFDataAsset<T> dataset) {
		super(dataset);
	}
	
	public T getResource(){
		return getDataset().getResource();
	}
	
}
