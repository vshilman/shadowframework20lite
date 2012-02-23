package shadow.pipeline.openGL20.tutorials.bitmapsExample;

import shadow.image.SFBitmap;
import shadow.image.bitmaps.SFSpecialPerlinNoise;

public class PerlinNoise3 {

	public static SFBitmap generateBitmap() {
		float[] weights={0.6f,0.2f,0.1f,0.05f,0.05f,0.05f,0.05f};
		float[] colors={1,1,0,
						1,0,0,
						1,0,1,
						1,0,1,
						1,0,1,
						0,1,1,
						4,4,0};
		SFSpecialPerlinNoise.randomizeFunction();
		SFBitmap bitmap=new SFSpecialPerlinNoise(100, 100, weights, colors);
		return bitmap;
	}

}
