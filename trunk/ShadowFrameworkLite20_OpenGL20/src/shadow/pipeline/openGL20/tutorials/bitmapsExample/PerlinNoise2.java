package shadow.pipeline.openGL20.tutorials.bitmapsExample;

import shadow.image.SFBitmap;
import shadow.image.bitmaps.SFSimplePerlinNoise;

public class PerlinNoise2 {

	public static SFBitmap generateBitmap() {
		float[] weights={0.6f,0.2f,0.1f,0.05f,0.05f,0.05f,0.05f};
		
		SFSimplePerlinNoise.randomizeFunction();
		SFBitmap bitmap=new SFSimplePerlinNoise(100, 100, weights, false);
		return bitmap;
	}

}
