package shadow.system;

import java.util.ArrayList;

public class SFAssetsLibrary {
	
	private int ASSETNAME_SET_SHIFT = 8;
	private int ASSETNAME_SET_SIZE = 1<<ASSETNAME_SET_SHIFT;

	private static SFAssetsLibrary library = new SFAssetsLibrary();

	private ArrayList<SFSimpleAsset<?>[]> assets=new ArrayList<SFSimpleAsset<?>[]>();
	private SFAssetsBuilder assetsBuilder; 
	
	private SFAssetsLibrary() {
		// TODO Auto-generated constructor stub
	}
	
	public static SFAssetsLibrary getLibrary() {
		return library;
	}
	
	public void setAssetsBuilder(SFAssetsBuilder assetsBuilder) {
		this.assetsBuilder = assetsBuilder;
	}
	
	private SFSimpleAsset<?> getTokenAsset(int assetName){
		int assetsSet=assetName >> ASSETNAME_SET_SHIFT;
		int assetsIndex = (assetName)-(assetsSet << ASSETNAME_SET_SHIFT);
		if(assetsSet<assets.size()){
			SFSimpleAsset<?>[] set=assets.get(assetsSet);
			if(assetsIndex<set.length)
				return set[assetsIndex];
		}	
		return null;
	}
	
	private void setTokenAsset(int assetName,SFSimpleAsset<?> tokenAsset){
		int assetsSet=assetName >> ASSETNAME_SET_SHIFT;
		int assetsIndex = (assetName)-(assetsSet << ASSETNAME_SET_SHIFT);
		while(assetsSet>=assets.size()){
			assets.add(new SFSimpleAsset<?>[ASSETNAME_SET_SIZE]);
		}
		assets.get(assetsSet)[assetsIndex]=tokenAsset;
	}
	
	//So : someone come here and 'make Asset Available'
	@SuppressWarnings("unchecked")
	public <T> SFAsset<T> makeAssetAvailable(int assetName){
		SFSimpleAsset<?> tokenAsset=getTokenAsset(assetName);
		if(tokenAsset==null){
			T asset = assetsBuilder.makeAssetAvailable(assetName);
			setTokenAsset(assetName,new SFSimpleAsset<T>(asset));
		}
		return (SFAsset<T>)(getTokenAsset(assetName));
	}
	
	
}
