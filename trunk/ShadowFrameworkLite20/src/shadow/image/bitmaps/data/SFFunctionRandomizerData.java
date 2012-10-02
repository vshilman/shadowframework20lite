package shadow.image.bitmaps.data;

import shadow.image.bitmaps.SFBitmapFunction;
import shadow.image.bitmaps.SFFunctionRandomizer;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFInt;

public class SFFunctionRandomizerData extends SFDataAsset<SFBitmapFunction>{
	
	private SFInt seed=new SFInt(9000);

	public SFFunctionRandomizerData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("seed", seed);
		setData(parameters);
	}

	@Override
	protected SFBitmapFunction buildResource() {
		return new SFFunctionRandomizer(seed.getIntValue());
	}
}
