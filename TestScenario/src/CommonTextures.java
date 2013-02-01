
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.image.bitmaps.data.SFBasicBitmapFunctionsData;
import shadow.image.bitmaps.data.SFBitmapPerlinNoiseData;
import shadow.image.bitmaps.data.SFFunction2DBitmapData;

public class CommonTextures {

	public static SFBitmapPerlinNoiseData generateSimplePerlinNoiseData() {
		SFBitmapPerlinNoiseData perlinNoise=new SFBitmapPerlinNoiseData();
			perlinNoise.setHeight(200);
			perlinNoise.setWidth(200);
			perlinNoise.setWeights(0.5f,0.1f,0.05f,0.05f,0.1f,0.1f,0.1f);
			SFFunction2DBitmapData function2dBitmapData=new SFFunction2DBitmapData();
			function2dBitmapData.setFunction(new SFBasicBitmapFunctionsData());
			perlinNoise.setBitmap(new SFFunction2DBitmapData());
		return perlinNoise;
	}

	public static SFFrameController generateTextureSelectionController(final SFTexture texture,
			int textureSize){
		final String[] names=new String[textureSize];
		for (int i = 0; i < names.length; i++) {
			names[i]="Texture "+i;
		}
		return new SFFrameController() {
			@Override
			public String getName() {
				return "Texture";
			}
			
			@Override
			public String[] getAlternatives() {
				return names;
			}
			
			@Override
			public void select(int index) {
				texture.setIndex(index);
			}
		};
	}
	
	
	public static SFFrameController generateTextureSelectionController(
			final SFTexture texture,final String[] names,final SFRenderedTexturesSet... sets){
		
		return new SFFrameController() {
			@Override
			public String getName() {
				return "Texture";
			}
			
			@Override
			public String[] getAlternatives() {
				return names;
			}
			
			@Override
			public void select(int index) {
				texture.setTexturesSet(sets[index]);
			}
		};
	}
}
