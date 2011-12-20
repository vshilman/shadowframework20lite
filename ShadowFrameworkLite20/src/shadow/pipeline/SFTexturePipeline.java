package shadow.pipeline;

import shadow.image.SFBufferData;
import shadow.image.SFRenderedTexture;
import shadow.image.SFRenderedTextureFactory;

public interface SFTexturePipeline {

	public void beginNewRenderedTexture(SFRenderedTexture textureData);
	
	public void endRenderedTexture(SFRenderedTexture textureData);
	
	public void destroyRenderedTexture(SFRenderedTexture textureData);

	public void destroyBufferData(SFBufferData bufferData);
	
	public SFRenderedTextureFactory getRenderedTextureFactory();
}
