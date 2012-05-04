package shadow.image.data;

import shadow.image.SFBitmap;
import shadow.image.SFBitmapTexture;
import shadow.image.SFRenderedTexturesSet;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFDatasetObject;

public class SFBitmapTextureData extends SFDataAsset<SFRenderedTexturesSet> {

	private class SFBitmapTextureDataObject extends SFCompositeDataArray {

		private SFDatasetObject<SFDataAsset<SFBitmap>> bitmap;

		@Override
		public void generateData() {
			bitmap=new SFDatasetObject<SFDataAsset<SFBitmap>>(null);
			addDataObject(bitmap);
		}
		
		@Override
		public SFCompositeDataArray clone() {
			return new SFBitmapTextureDataObject();
		}
	}
	
	private SFBitmapTextureDataObject data=new SFBitmapTextureDataObject();
	
	public SFBitmapTextureData() {
		setData(data);
	}

	public void setBitmap(SFDataAsset<SFBitmap> bitmap) {
		this.data.bitmap.setDataset(bitmap);
	}
	
	public SFDataset getBitmap() {
		return this.data.bitmap.getDataset();
	}

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFBitmapTextureData();
	}

	@Override
	protected SFRenderedTexturesSet buildResource() {
		SFBitmapTexture bitmapTexture=new SFBitmapTexture(data.bitmap.getDataset().getResource());
		return bitmapTexture;
	}
}
