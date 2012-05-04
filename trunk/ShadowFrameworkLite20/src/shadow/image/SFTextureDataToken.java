package shadow.image;

public class SFTextureDataToken extends SFPipelineTexture{

	public SFTextureDataToken(int width, int height, SFImageFormat format,
			Filter filters, WrapMode wrapS, WrapMode wrapT) {
		super(width, height, format, filters, wrapS, wrapT);
	}

	@Override
	public void apply(int textureLevel) {
		//Do nothing
	}
	
}
