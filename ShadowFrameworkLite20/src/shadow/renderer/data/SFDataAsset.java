package shadow.renderer.data;

import shadow.system.SFInitiable;
import shadow.system.SFInitiator;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;

/**
 * Abstract class for Assets which can be stored and retrieved through SFDataset
 * mechanisms. <br/>
 * Subclasses should be able to provide an implementation of the method required
 * to build up the resource from its data. <br/>
 * Each DataAsset will be built only once. <br/>
 * The Default {@link SFDataObject} is null and should be assigned through the
 * protected {@link setData} method.
 * 
 * @author Alessandro Martinelli
 * 
 * @param <T>
 *            the Type of graphical element which is kept by this asset.
 */
public abstract class SFDataAsset<T extends SFInitiable> extends SFAssetModule implements SFDataset, SFAsset<T> {

	private T resource = null;
	
	private static boolean updateMode=false;
	
	/**
	 * Build up a valid resource using data content available from
	 * {@linkplain SFDataObject}. This method will be called only once
	 * 
	 * @return a valid resource of type T
	 */
	protected abstract T buildResource();

	@Override
	public T getResource() {
		if (resource == null || updateMode) {
			resource = buildResource();
			SFInitiator.addInitiable(resource);
		}
		return resource;
	}

	protected static boolean isUpdateMode() {
		return updateMode;
	}

	protected static void setUpdateMode(boolean updateMode) {
		SFDataAsset.updateMode = updateMode;
	}

}