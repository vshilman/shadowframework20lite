package shadow.image;

import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.pipeline.SFPipeline;

public class SFBitmapTexture implements SFRenderedTexturesSet{

	private SFPipelineTexture texture;
	private SFBitmap bitmap;
	
	public SFBitmapTexture(SFBitmap bitmap) {
		super();
		this.bitmap = bitmap;
	}
	
	protected SFBitmap getBitmap() {
		return bitmap;
	}

	protected void setBitmap(SFBitmap bitmap) {
		this.bitmap = bitmap;
	}

	@Override
	public void apply(int index,int level) {
		texture.apply(level);
	}
	
	@Override
	public void init() {
		if(texture==null)
			texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, Filter.LINEAR_MIPMAP,
					WrapMode.REPEAT, WrapMode.REPEAT);
	}

	@Override
	public int getTextureSize() {
		return 1;
	}
}
