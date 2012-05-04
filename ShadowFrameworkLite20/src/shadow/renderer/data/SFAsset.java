package shadow.renderer.data;

import shadow.system.SFInitiable;

/**
 * This class describes an asset, that's a module
 * keeping some graphical element. 
 * 
 * @author Alessandro Martinelli
 *
 * @param <T> the Type of graphical element which is kept by this asset.
 */
public interface SFAsset<T extends SFInitiable> {

	/**
	 * Used to retrieved the Resource this asset is keeping.
	 * @param listener A module which need the kept resource
	 */
	public abstract T getResource();

}