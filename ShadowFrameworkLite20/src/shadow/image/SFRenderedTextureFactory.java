package shadow.image;

import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;

public interface SFRenderedTextureFactory {

	public SFBufferData generatePlainBuffer(int width, int height);
	
	public SFBufferData generatePlainBuffer(int width, int height, SFImageFormat format);
	
	public SFPipelineTexture generateTextureBuffer(int width, int height, SFImageFormat format,Filter filters, WrapMode wrapS, WrapMode wrapT);
	
	public SFPipelineTexture generateBitmapTexture(SFBitmap bitmap,Filter filters, WrapMode wrapS, WrapMode wrapT);
	
	public void destroyBuffer(SFBufferData bufferData);
}
