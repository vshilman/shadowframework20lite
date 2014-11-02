package shadow.system;


/**
 * 
 * @author Alessandro Martinelli
 */
public class SFSimpleAsset<T> implements SFAsset<T> {

	private T asset = null;
	
	public SFSimpleAsset() {
		super();
	}

	public SFSimpleAsset(T asset) {
		super();
		this.asset=asset;
	}
	
	/**
	 * @param asset
	 * @return the previous asset; library will have to decide if it has to be removed
	 */
	@SuppressWarnings("unchecked")
	protected T setAsset(SFInitiable asset) {
		T prevAsset=this.asset;
		this.asset = (T)asset;
		return prevAsset;
	}
	
	public T getResource() {
		return asset;
	}
	
	@Override
	public void release() {
		//nothing to do
	}

}
