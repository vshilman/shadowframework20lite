package shadow.system.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A Composition of Libraries
 * 
 * @author Alessandro
 *
 * @param <T>
 */
public class SFCompositeDataCenter<T extends SFLibrary> implements SFLibrary{

	private LinkedHashMap<String,T> libraries=new LinkedHashMap<String,T>();

	public SFCompositeDataCenter() {
		super();
	}
	
	@Override
	public SFDataAsset<?> retrieveDataset(String name) {
		
		int indexOf=name.lastIndexOf(".");
		if(indexOf>=0){
			String libraryName=name.substring(0,indexOf);
			String dataAssetName=name.substring(indexOf+1);
			
			T dataCenter=libraries.get(libraryName);
			if(dataCenter!=null){
				SFDataAsset<?> dataAsset=dataCenter.retrieveDataset(dataAssetName);
				if(dataAsset!=null){
					return dataAsset;
				}
			}
		}
		
		for (String key : libraries.keySet()) {
			SFDataAsset<?> dataAsset=libraries.get(key).retrieveDataset(name);
			if(dataAsset!=null){
				return dataAsset;
			}
		}
	
		return null;
	}

	public Map<String,T> getDataCenters() {
		return libraries;
	}
	
	public void add(String name,T dataCenter){
		libraries.put(name,dataCenter);
	}
	
}
