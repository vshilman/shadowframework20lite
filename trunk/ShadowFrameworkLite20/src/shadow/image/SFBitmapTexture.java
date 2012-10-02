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
	public SFPipelineTexture getTexture(int index) {
		return texture;
	}
	
	@Override
	public void init() {
		if(texture==null)
			texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, Filter.LINEAR_MIPMAP,
					WrapMode.REPEAT, WrapMode.REPEAT);
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void destroy() {
		if(texture!=null){
			
		}
	}

	@Override
	public int getTextureSize() {
		return 1;
	}
}
