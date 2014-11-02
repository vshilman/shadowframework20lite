package shadow.system.data;

import java.util.ArrayList;
import java.util.HashMap;

import shadow.system.SFAssetsBuilder;
import shadow.system.SFException;

public abstract class SFDictionary implements SFAssetsBuilder{

	protected HashMap<String, SFDataAsset<?>> assets = new HashMap<String, SFDataAsset<?>>();
	/* Note: SF Editors will need to use a List of Strings, while Sf Viewers can
	 * directly use a List of Datasets
	 */
	protected ArrayList<String> assetsList = new ArrayList<String>();
	
	public SFDictionary() {
		super();
		addSFDataset(new SFObjectsLibrary());
		addSFDataset(new SFTemplateDataAsset());
	}

	public void addSFDataset(SFDataAsset<?> sfasset) {
		String name=sfasset.getName();
		assets.put(name, sfasset);
		assetsList.add(name);
//		if(sfasset instanceof SFDataAsset<?>)
//			((SFDataAsset<?>)sfasset).setName(name);
	}
	
	
	public void writeDataset(SFOutputStream stream, SFDataAsset<?> dataset) {
		int index=assetsList.indexOf(dataset.getName());
		//System.err.println(dataset.getName()+" "+index);
		if(index<0)
			throw new SFException("Error writing dataset :"+(dataset.getName()));
		stream.writeInt(index);
		dataset.writeOnStream(stream);
	}

	public SFDataAsset<?> readDataset(SFInputStream stream,String name) {
		String datasetName = assetsList.get(stream.readInt());
		SFDataAsset<?> dataset=assets.get(datasetName);
		dataset = dataset.copyDataset();
		dataset.readFromStream(stream);
		//this is the point: here I store the dataset.
		if(checkName(name))
			assets.put(name, dataset);
		return dataset;
	}
	
	public SFDataAsset<?> createDataset(String typeName, String name) {
		SFDataAsset<?> asset=assets.get(typeName);
		if(asset!=null){
			SFDataAsset<?> newDataset=asset.copyDataset();
			if(checkName(name)){	
				((SFDataAsset<?>)(newDataset)).setName(typeName);
				assets.put(name, newDataset);
				assetsList.add(name);
			}
			return newDataset;
		}
		throw new SFException("Cannot instantiate "+typeName);
	}

	public SFDataAsset<?> getDataAsset(String name){
		return assets.get(name);
	}
	
	public SFDataAsset<?> getDataAsset(int index){
		return assets.get(assetsList.get(index));
	}
	
	public int getIndex(String name){
		return assetsList.indexOf(name);
	}

	private boolean checkName(String name) {
		return name!=null;
	}

	public HashMap<String, SFDataAsset<?>> getAssets() {
		return assets;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T makeAssetAvailable(int index) {
		return (T)assets.get(assetsList.get(index)).getResource();
	}
	
}


//Note : composition should be solved in the dictionary; or ignored at all!!

//public class SFCompositeDataCenter<T extends SFIDataCenter> implements SFIDataCenter{
//
//	private LinkedHashMap<String,T> dataCenters=new LinkedHashMap<String,T>();
//
//	public SFCompositeDataCenter() {
//		super();
//	}
//	
//	@Override
//	public SFDataAsset<?> makeDatasetAvailable(String name) {
//		
//		int indexOf=name.lastIndexOf(".");
//		if(indexOf>=0){
//			String libraryName=name.substring(0,indexOf);
//			String dataAssetName=name.substring(indexOf+1);
//			
//			T dataCenter=dataCenters.get(libraryName);
//			if(dataCenter!=null){
//				SFDataAsset<?> dataAsset=dataCenter.makeDatasetAvailable(dataAssetName);
//				if(dataAsset!=null){
//					return dataAsset;
//				}
//			}
//		}
//		
//		for (String key : dataCenters.keySet()) {
//			SFDataAsset<?> dataAsset=dataCenters.get(key).makeDatasetAvailable(name);
//			if(dataAsset!=null){
//				return dataAsset;
//			}
//		}
//	
//		return null;
//	}
//
//	public Map<String,T> getDataCenters() {
//		return dataCenters;
//	}
//	
//	public void add(String name,T dataCenter){
//		dataCenters.put(name,dataCenter);
//	}
//	
//}
