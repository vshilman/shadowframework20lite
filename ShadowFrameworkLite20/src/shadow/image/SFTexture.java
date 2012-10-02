package shadow.image;


public class SFTexture {

	private SFRenderedTexturesSet texturesSet;
	private int index;
	
	public SFTexture(SFRenderedTexturesSet texturesSet, int index) {
		super();
		this.texturesSet = texturesSet;
		this.index = index;
	}
	
	public SFPipelineTexture getTexture(){
		return texturesSet.getTexture(index);
	}
	
	public SFRenderedTexturesSet getTexturesSet() {
		return texturesSet;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setTexturesSet(SFRenderedTexturesSet texturesSet) {
		this.texturesSet = texturesSet;
	}
	
	
}
