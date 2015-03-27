package shadow.system;


/**
 * This class describes an asset, that's a module
 * keeping some graphical element.
 * 
 * @author Alessandro Martinelli
 */
public abstract class SFAsset extends SFElement implements SFInitiable,SFUpdatable{

	private boolean unique;
	
	public SFAsset(boolean unique) {
		super();
		this.unique = unique;
	}

	public void free(){
		if(unique){
			destroy();
		}else{
			super.free();	
		}
	}

}