package shadow.pipeline;

public interface SFTexturePipeline {

	public int beginNewRenderedTexture(SFTextureData textureData);
	
	public void endRenderedTexture(int texture);
	
	public void destroyRenderedTexture(int texture);
	
}
