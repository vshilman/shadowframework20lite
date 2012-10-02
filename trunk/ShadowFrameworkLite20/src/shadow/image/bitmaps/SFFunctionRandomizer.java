package shadow.image.bitmaps;

import shadow.math.SFRandomizer;

public class SFFunctionRandomizer implements SFBitmapFunction{

	private SFRandomizer randomizer;

	public SFFunctionRandomizer(int seed) {
		super();
		this.randomizer=new SFRandomizer(seed);
	}
	
	@Override
	public float getValue(float u, float v) {
		return randomizer.randomFloat();
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init() {
		
	}
}
