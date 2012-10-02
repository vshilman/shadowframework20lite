package shadow.image.bitmaps.data;

import shadow.image.SFBitmap;
import shadow.image.bitmaps.SFBitmapFunction;
import shadow.image.bitmaps.SFFunction2DBitmap;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;

public class SFFunction2DBitmapData extends SFBitmapData{

	private SFLibraryReference<SFBitmapFunction> function=new SFLibraryReference<SFBitmapFunction>(); 
	
	public SFFunction2DBitmapData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("width", width);
		parameters.addObject("height", height);
		parameters.addObject("function", function);
		setData(parameters);
	}
	
	public void setFunction(SFDataAsset<SFBitmapFunction> function) {
		this.function.setDataset(function);
	}

	@Override
	protected SFBitmap buildResource() {
		final SFFunction2DBitmap bitmap=new SFFunction2DBitmap(width.getShortValue(), height.getShortValue(), false, null);
		function.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFBitmapFunction>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFBitmapFunction> dataset) {
				bitmap.setFunction(dataset.getResource());
			}
		});
		return bitmap;
	}
}
