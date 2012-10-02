package shadow.system.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shadow.system.SFException;

public class SFGenericDatasetFactory implements SFAbstractDatasetFactory{

	private HashMap<String, SFDataset> assets = new HashMap<String, SFDataset>();
	/* Note: SF Editors will need to use a List of Strings, while Sf Viewers can
	 * directly use a List of Datasets
	 */
	private List<String> assetsList = new ArrayList<String>();
	
	public SFGenericDatasetFactory() {
		super();
	}

	public void addSFDataset(SFDataset sfasset) {
		assets.put(sfasset.getType(), sfasset);
		assetsList.add(sfasset.getType());
	}
	
	@Override
	public SFDataset readDataset(SFInputStream stream) {
		String datasetName = assetsList.get(stream.readInt());
		SFDataset dataset=assets.get(datasetName);
		dataset = dataset.generateNewDatasetInstance();
		dataset.getSFDataObject().readFromStream(stream);
		return dataset;
	}
	
	@Override
	public void writeDataset(SFOutputStream stream, SFDataset dataset) {
		
		int index=assetsList.indexOf(dataset.getType());
		if(index<0)
			throw new SFException("Error writing dataset :"+(dataset.getType()));
		stream.writeInt(index);
		dataset.getSFDataObject().writeOnStream(stream);
	}

	public SFDataset createDataset(String typeName) {
		
		SFDataset asset=assets.get(typeName);
		
		if(asset!=null){
			return asset.generateNewDatasetInstance();
		}
		throw new SFException("Cannot instantiate "+typeName);
	}

}