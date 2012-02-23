package shadow.system.data;

import java.util.HashMap;

public class SFGenericDatasetFactory implements SFAbstractDatasetFactory{

	private HashMap<String, SFDataset> assets = new HashMap<String, SFDataset>();

	public SFGenericDatasetFactory() {
		super();
	}

	public void addSFDataset(SFDataset sfasset) {
		assets.put(sfasset.getType(), sfasset);
	}

	@Override
	public SFDataset createDataset(String typeName) {
		
		SFDataset asset=assets.get(typeName);
		
		if(asset!=null){
			return asset.generateNewDatasetInstance();
		}
		throw new RuntimeException("Cannot instantiate "+typeName);
	}

}