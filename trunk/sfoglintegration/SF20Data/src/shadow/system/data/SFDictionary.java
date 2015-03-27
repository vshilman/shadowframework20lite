package shadow.system.data;

import java.util.ArrayList;
import java.util.Collection;

import shadow.system.SFContext;
import shadow.system.SFDatabase;
import shadow.system.SFElement;
import shadow.system.SFException;

public class SFDictionary implements SFDatabase{

	//private ArrayList<String> typesNames=new ArrayList<String>();
	//protected TreeMap<String, SFDataAsset<?>> assets = new TreeMap<String, SFDataAsset<?>>();
	
	private SFLibrary library;
	
	/* Note: SF Editors will need to use a List of Strings, while Sf Viewers can
	 * directly use a List of Datasets
	 */
	protected ArrayList<String> assetsList = new ArrayList<String>();
	
	//That is still bad .. 
	
	public SFDictionary() {
		super();
		addSFDataAsset(new SFTemplateDataAsset());
	}

	public SFDictionary(SFLibrary library) {
		super();
		this.library=library;
		addSFDataAsset(new SFTemplateDataAsset());
	}
	
	public void addSFDataAsset(SFDataAsset<?> sfasset) {
		String name=sfasset.getName();
		addSFDataAsset(name,sfasset);
	}

	public void addSFDataAsset(String name,SFDataAsset<?> sfasset) {
		library.put(name, sfasset);
		assetsList.add(name);
	}
	
	public void addAssets(SFDictionary otherDictonary){
		Collection<String> names=otherDictonary.library.getNames();
		for (String name : names) {
			library.put(name, otherDictonary.library.retrieveDataset(name));
		}
	}
	
	public SFDataAsset<?> getDataAsset(String name){
		return library.retrieveDataset(name);
	}
	
	public void writeDataset(SFOutputStream stream, SFDataAsset<?> dataset) {
		int index=assetsList.indexOf(dataset.getName());
		if(index<0)
			throw new SFException("Error writing dataset :"+(dataset.getName()));
		stream.writeInt(index);
		dataset.writeOnStream(stream);
	}

	public SFDataAsset<?> readDataset(SFInputStream stream,String name) {
		int index=stream.readInt();
		//System.err.println("#"+name+" "+" "+index);
		String datasetName = assetsList.get(index);
		SFDataAsset<?> dataset=library.retrieveDataset(datasetName);
		dataset = dataset.copyDataset();
		dataset.readFromStream(stream);
		//this is the point: here I store the dataset.
		if(checkName(name)){
			assetsList.add(name);
			library.put(name, dataset);
		}
		return dataset;
	}
	
	public SFDataAsset<?> createDataset(String typeName, String name) {
		
		SFDataAsset<?> asset=library.retrieveDataset(typeName);
		if(asset!=null){
			SFDataAsset<?> newDataset=asset.copyDataset();
			if(checkName(name)){	
				((SFDataAsset<?>)(newDataset)).setName(typeName);
				addSFDataAsset(name,newDataset);
			}
			return newDataset;
		}
		throw new SFException("Cannot instantiate "+typeName);
	}

	
	public int getIndex(String name){
		return assetsList.indexOf(name);
	}

	private boolean checkName(String name) {
		return name!=null;
	}
	
	@Override
	public SFElement getElement(SFContext context,String name) {
		return (SFElement)library.retrieveDataset(name).getResource(context);
	}
	
}
