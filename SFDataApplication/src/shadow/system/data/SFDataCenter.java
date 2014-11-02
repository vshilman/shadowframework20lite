package shadow.system.data;

import shadow.system.SFAssetsLibrary;

/**
 * SFDataCenter is a SFDictionary Keeper
 * 
 * @author Alessandro Martinelli
 */
public class SFDataCenter {

	public static SFDataCenter dataCenter=new SFDataCenter(); 
	
	private SFDictionary dictionary;

	private SFDataCenter(){
		//singleton
	}
	
	public static void setDictionary(SFDictionary dictionary) {
		dataCenter.dictionary = dictionary;
		SFAssetsLibrary.getLibrary().setAssetsBuilder(dictionary);
	}
	
	public SFDictionary getDictionary() {
		return dictionary;
	}

	public static SFDataCenter getDataCenter() {
		return dataCenter;
	}

	public SFDataAsset<?> readDataset(SFInputStream stream,String name) {
		return dataCenter.dictionary.readDataset(stream,name);
	}

	public void writeDataset(SFOutputStream stream, SFDataAsset<?> dataset) {
		dataCenter.dictionary.writeDataset(stream, dataset);
	}
	
	public SFDataAsset<?> createDataset(String type, String name) {
		return dataCenter.dictionary.createDataset(type,name);
	}
	
	@SuppressWarnings("unchecked")
	public static <E> SFDataAsset<E> makeDatasetAvailable(int index){
		return (SFDataAsset<E>)dataCenter.dictionary.getDataAsset(index);
	}
	
	@SuppressWarnings("unchecked")
	public static <E> SFDataAsset<E> makeDatasetAvailable(String name){
		return (SFDataAsset<E>)dataCenter.dictionary.getDataAsset(name);
	}
}
