package shadow.system.data;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * A Composition of Libraries
 * 
 * The Composition of Libraries supports the format:
 * 
 * compositionname.dataassetname
 * 
 * and can be hierarchical
 * 
 * @author Alessandro Martinelli
 *
 * @param <T>
 */
public class SFCompositeLibrary implements SFLibrary{

	private LinkedHashMap<String,SFLibrary> libraries=new LinkedHashMap<String,SFLibrary>();
	private SFObjectsLibrary mainLibrary=new SFObjectsLibrary();

	public SFCompositeLibrary() {
		super();
	}
	
	@Override
	public void put(String name, SFDataAsset<?> dataset)
			throws NullPointerException {
		mainLibrary.put(name, dataset);
	}
	
	@Override
	public Collection<String> getNames() {
		LinkedList<String> names=new LinkedList<String>();
		names.addAll(mainLibrary.getNames());
		for (SFLibrary library : libraries.values()) {
			names.addAll(library.getNames());
		}
		return names;
	}
	
	@Override
	public SFDataAsset<?> retrieveDataset(String name) {
		
		int indexOf=name.lastIndexOf(".");
		if(indexOf>=0){
			String libraryName=name.substring(0,indexOf);
			String dataAssetName=name.substring(indexOf+1);
			
			SFLibrary dataCenter=libraries.get(libraryName);
			if(dataCenter!=null){
				SFDataAsset<?> dataAsset=dataCenter.retrieveDataset(dataAssetName);
				if(dataAsset!=null){
					return dataAsset;
				}
			}
		}
		
		return mainLibrary.retrieveDataset(name);
		
	}

	public Map<String,SFLibrary> getDataCenters() {
		return libraries;
	}
	
	public void add(String name,SFLibrary dataCenter){
		libraries.put(name,dataCenter);
	}
	
}
