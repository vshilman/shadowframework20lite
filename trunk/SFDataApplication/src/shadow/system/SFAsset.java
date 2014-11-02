package shadow.system;


/**
 * This class describes an asset, that's a module
 * keeping some graphical element. 
 * 
 * @author Alessandro Martinelli
 *
 * @param <T> the Type of graphical element which is kept by this asset.
 */
public interface SFAsset<T> {

	/**
	 * Used to retrieved the Resource this asset is keeping.
	 * @param listener A module which need the kept resource
	 */
	public T getResource();
	
	/**
	 * Used to release the resource this Asset was keeping
	 */
	public void release();
}