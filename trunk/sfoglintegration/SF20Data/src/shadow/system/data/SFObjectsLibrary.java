package shadow.system.data;

import java.util.Collection;
import java.util.LinkedHashMap;

import shadow.system.SFElement;

/**
 * A Library of Datasets, each Dataset being identified by name
 * @author Alessandro Martinelli
 */
public class SFObjectsLibrary extends SFElement implements SFLibrary,SFDataObject{
	
	//private SFDataList<SFLibraryRecord> records=new SFDataList<SFLibraryRecord>(new SFLibraryRecord("",null));
	
	protected LinkedHashMap<String, SFDataAsset<?>> assets = new LinkedHashMap<String, SFDataAsset<?>>();
	
	//public class SFLibraryDataObject implements SFDataObject{
	@Override
	public SFDataObject copyDataObject() {
		return new SFObjectsLibrary();
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		int count=stream.readInt();
		for (int i = 0; i < count; i++) {
			String name=stream.readName();
			//TODO : GRAN BRUTTO GIRO :(
			SFDataCenter.getDataCenter().readDataset(stream, name);
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeInt(assets.size());
		for (String name : assets.keySet()) {
			stream.writeName(name);
			//TODO: GRAN BRUTTO GIRO :(
			SFDataCenter.getDataCenter().writeDataset(stream, assets.get(name));
		}
	}
	//}
	
	public SFObjectsLibrary() {
		//addObject("records", new SFLibraryDataObject());
	}

	
	/**
	 * Insert a new record to this Library. 
	 * @param name The name of this record.
	 * @param dataset The dataset called with that name. 
	 */
	public void put(String name,SFDataAsset<?> dataset) throws NullPointerException{
		if(dataset==null)
			throw new NullPointerException("Dataset cannot be null");
		assets.put(name, dataset);
	}
	
	
	/* (non-Javadoc)
	 * @see shadow.system.data.SFLibrary#retrieveDataset(java.lang.String)
	 */
	@Override
	public synchronized SFDataAsset<?> retrieveDataset(String name){
		return assets.get(name);
	}

	public void removeRecord(String name){
		assets.remove(name);
	}
	
	/**
	 * @return the number of Dataset in this Library
	 */
	public int size(){
		return assets.size();
	}
	
	@Override
	public Collection<String> getNames() {
		return assets.keySet();
	}
}
