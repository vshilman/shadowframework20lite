package shadow.system;


/**
 * 
 * @author Alessandro Martinelli
 */
public class SFTokenAsset<T extends SFInitiable> implements SFAsset<T> {

	public static final int NULL_REFERENCE = -1;
	public static final int MISSING_REFERENCE = -2;

	private T asset = null;
	private short use;
	
	public SFTokenAsset() {
		super();
	}

	public SFTokenAsset(T asset) {
		super();
		this.asset=asset;
	}
	
	public void useAsset(){
		if(use>0)
			SFInitiator.addInitiable(asset);
		use++;
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
		use--;
		if(use==0)
			SFInitiator.addDestroyable(asset);
	}

	public boolean isUsed(){
		return use>0;
	}
}
