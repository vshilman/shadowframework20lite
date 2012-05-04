package shadow.system.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SFGenericDatasetFactory implements SFAbstractDatasetFactory{

	private boolean optimizeFile=true;
	
	private HashMap<String, SFDataset> assets = new HashMap<String, SFDataset>();
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
		if(optimizeFile){
			String datasetName = assetsList.get(stream.readInt());
			SFDataset dataset = (createDataset(datasetName));
			dataset.getSFDataObject().readFromStream(stream);
			return dataset;
		}else{
			String datasetName = stream.readString();
			SFDataset dataset = (createDataset(datasetName));
			dataset.getSFDataObject().readFromStream(stream);
			return dataset;
		}
	}
	
	@Override
	public void writeDataset(SFOutputStream stream, SFDataset dataset) {
		if(optimizeFile){
			int index=assetsList.indexOf(dataset.getType());
			stream.writeInt(index);
			dataset.getSFDataObject().writeOnStream(stream);
		}else{
			stream.writeString(dataset.getType());
			dataset.getSFDataObject().writeOnStream(stream);
		}
	}

	public SFDataset createDataset(String typeName) {
		
		SFDataset asset=assets.get(typeName);
		
		if(asset!=null){
			return asset.generateNewDatasetInstance();
		}
		throw new RuntimeException("Cannot instantiate "+typeName);
	}

}