package shadow.image.bitmaps;

import shadow.image.SFBitmap;

public interface SFImageInterpolant {

	public abstract float getOctaveValue(float u, float v, int octave, SFBitmap bitmap);

}