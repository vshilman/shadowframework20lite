package shadow.image.bitmaps.data;

import shadow.image.SFBitmap;
import shadow.image.bitmaps.SFBitmapPerlinNoise;
import shadow.image.bitmaps.SFCubicInterpolation;
import shadow.image.bitmaps.SFImageInterpolant;
import shadow.image.bitmaps.SFLinearInterpolation;
import shadow.image.bitmaps.SFNearestInterpolation;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFEnumObject;

public class SFBitmapPerlinNoiseData extends SFPerlineNoiseData{

	private static String[] interpolantsNames={
		"Nearest","Linear","Cubic"
	};
	
	private static SFImageInterpolant[] interpolants={
		new SFNearestInterpolation(),new SFLinearInterpolation(),new SFCubicInterpolation()
	};
	
	private SFLibraryReference<SFBitmap> bitmap=new SFLibraryReference<SFBitmap>();
	private SFEnumObject<SFImageInterpolant> interpolant=new SFEnumObject<SFImageInterpolant>(interpolants,interpolantsNames);
	
	public SFBitmapPerlinNoiseData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("width", width);
		parameters.addObject("height", height);
		parameters.addObject("weights", weights);
		parameters.addObject("interpolant", interpolant);
		parameters.addObject("bitmap", bitmap);
		setData(parameters);
	}

	
	public void setBitmap(String reference){
		bitmap.setReference(reference);
	}
	
	public void setBitmap(SFDataAsset<SFBitmap> reference){
		bitmap.setDataset(reference);
	}


	public void setInterpolant(String interpolant){
		this.interpolant.setStringValue(interpolant);
	}
	
	@Override
	public SFBitmapPerlinNoiseData generateNewDatasetInstance() {
		return new SFBitmapPerlinNoiseData();
	}
	
	
	@Override
	protected SFBitmap buildResource() {

		float weights[]=this.weights.getValues();
		final SFBitmapPerlinNoise perlinNoise=new SFBitmapPerlinNoise(width.getShortValue(),
				height.getShortValue(),weights,
				false);
		perlinNoise.setInterpolant(interpolant.getElement());
		
		bitmap.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFBitmap>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFBitmap> dataset) {
				if(dataset!=null){
					SFBitmap bitmap=dataset.getResource();
					if(bitmap!=null)
						perlinNoise.setBitmap(bitmap);
				}
			}
		});
		
		return perlinNoise;
	}
	
}
