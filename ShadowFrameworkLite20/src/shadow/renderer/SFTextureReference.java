package shadow.renderer;

import shadow.image.SFTexture;

public class SFTextureReference {

	private int textureLevel;
	private SFTexture texture;
	
	public SFTextureReference(int textureLevel, SFTexture texture) {
		super();
		this.textureLevel = textureLevel;
		this.texture = texture;
	}
	
	public void apply(){
		texture.apply(textureLevel);
	}

	public int getTextureLevel() {
		return textureLevel;
	}

	public void setTextureLevel(int textureLevel) {
		this.textureLevel = textureLevel;
	}

	public SFTexture getTexture() {
		return texture;
	}

	public void setTexture(SFTexture texture) {
		this.texture = texture;
	}
	
	
}
