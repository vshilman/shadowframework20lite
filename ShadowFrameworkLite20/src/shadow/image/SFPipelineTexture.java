package shadow.image;


public abstract class SFPipelineTexture extends SFBufferData{

	public enum WrapMode{
		REPEAT,
		MIRRORED_REPEAT,
		CLAMP_TO_EDGE
	}
	
	public enum Filter{
		NEAREST,
		LINEAR,
		LINEAR_MIPMAP
	}
	
	public abstract void apply(int textureLevel);
	
	private Filter filters;
	private WrapMode WrapS;
	private WrapMode WrapT;
	
	public SFPipelineTexture(int width, int height, SFImageFormat format,Filter filters, WrapMode wrapS, WrapMode wrapT) {
		super(width,height,format);
		this.filters = filters;
		WrapS = wrapS;
		WrapT = wrapT;
	}

	public Filter getFilters() {
		return filters;
	}

	public WrapMode getWrapS() {
		return WrapS;
	}

	public WrapMode getWrapT() {
		return WrapT;
	}
}
