package shadow.image;


public class SFTexture {

	private SFRenderedTexturesSet texturesSet;
	private int index;
	
	public SFTexture(SFRenderedTexturesSet texturesSet, int index) {
		super();
		this.texturesSet = texturesSet;
		this.index = index;
	}
	
	public void apply(int level){
		texturesSet.apply(index, level);
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
