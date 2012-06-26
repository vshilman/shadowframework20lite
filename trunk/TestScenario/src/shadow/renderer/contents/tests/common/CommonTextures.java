package shadow.renderer.contents.tests.common;

import shadow.image.SFTexture;
import shadow.image.bitmaps.data.SFSimplePerlinNoiseData;
import shadow.renderer.viewer.SFFrameController;

public class CommonTextures {

	public static SFSimplePerlinNoiseData generateSimplePerlinNoiseData() {
		SFSimplePerlinNoiseData perlinNoise=new SFSimplePerlinNoiseData();
			perlinNoise.setHeight(200);
			perlinNoise.setWidth(200);
			perlinNoise.setWeights(0.5f,0.1f,0.05f,0.05f,0.1f,0.1f,0.1f);
			perlinNoise.setRGB(false);
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
}
