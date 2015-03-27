package shadow.system.data;

import java.util.Collection;


/**
 * Common Interface for Packages of Data Assets
 * 
 * @author Alessandro Martinelli
 */
public interface SFLibrary {

	/**
	 * Look for the {@link Dataset} registered with the given name
	 * @param name the name of the {@link Dataset}
	 * @return
	 */
	public abstract SFDataAsset<?> retrieveDataset(String name);

	public void put(String name,SFDataAsset<?> dataset) throws NullPointerException;

	public Collection<String> getNames();
	
	//TODO : here you Add Your Retrieval Function, ok?
}