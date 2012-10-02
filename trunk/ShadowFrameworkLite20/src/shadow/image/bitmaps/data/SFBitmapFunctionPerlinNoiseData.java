package shadow.image.bitmaps.data;

import shadow.image.SFBitmap;
import shadow.image.bitmaps.SFBitmapFunction;
import shadow.image.bitmaps.SFBitmapFunctionPerlinNoise;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;

public class SFBitmapFunctionPerlinNoiseData extends SFPerlineNoiseData{

	private SFLibraryReference<SFBitmapFunction> bitmap=new SFLibraryReference<SFBitmapFunction>();
	
	public SFBitmapFunctionPerlinNoiseData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("width", width);
		parameters.addObject("height", height);
		parameters.addObject("weights", weights);
		parameters.addObject("bitmap", bitmap);
		setData(parameters);
	}

	
	public void setBitmap(String reference){
		bitmap.setReference(reference);
	}
	
	public void setBitmap(SFDataAsset<SFBitmapFunction> reference){
		bitmap.setDataset(reference);
	}

	@Override
	public SFBitmapFunctionPerlinNoiseData generateNewDatasetInstance() {
		return new SFBitmapFunctionPerlinNoiseData();
	}
	
	
	@Override
	protected SFBitmap buildResource() {
		
		float weights[]=this.weights.getValues();
		final SFBitmapFunctionPerlinNoise perlinNoise=new SFBitmapFunctionPerlinNoise(width.getShortValue(),
				height.getShortValue(),weights,
				false);
		
		bitmap.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFBitmapFunction>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFBitmapFunction> dataset) {
				perlinNoise.setBitmap(dataset.getResource());
			}
		});
		
		return perlinNoise;
	}
	
}
