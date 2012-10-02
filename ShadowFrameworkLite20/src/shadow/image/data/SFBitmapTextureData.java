package shadow.image.data;

import shadow.image.SFBitmap;
import shadow.image.SFBitmapTexture;
import shadow.image.SFRenderedTexturesSet;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataAssetObject;
import shadow.system.data.SFDataset;
import shadow.system.data.SFNamedParametersObject;

public class SFBitmapTextureData extends SFDataAsset<SFRenderedTexturesSet> {

	private SFDataAssetObject<SFBitmap> bitmap=new SFDataAssetObject<SFBitmap>(null);
	
	public SFBitmapTextureData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("bitmap", bitmap);
		setData(parameters);
	}

	public void setBitmap(SFDataAsset<SFBitmap> bitmap) {
		this.bitmap.setDataset(bitmap);
	}
	
	public SFDataset getBitmap() {
		return this.bitmap.getDataset();
	}

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFBitmapTextureData();
	}

	@Override
	protected SFRenderedTexturesSet buildResource() {
		SFBitmapTexture bitmapTexture=new SFBitmapTexture(bitmap.getResource());
		return bitmapTexture;
	}
}
