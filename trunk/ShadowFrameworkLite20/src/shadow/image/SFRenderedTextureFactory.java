package shadow.image;

import shadow.image.SFTextureData.Filter;
import shadow.image.SFTextureData.WrapMode;

public interface SFRenderedTextureFactory {

	public SFBufferData generatePlainBuffer(int width, int height);
	
	public SFBufferData generatePlainBuffer(int width, int height, SFFormat format);
	
	public SFTextureData generateTextureBuffer(int width, int height, SFFormat format,Filter filters, WrapMode wrapS, WrapMode wrapT);
	
	public SFTextureData generateBitmapTexture(SFBitmap bitmap,Filter filters, WrapMode wrapS, WrapMode wrapT);
	
}
