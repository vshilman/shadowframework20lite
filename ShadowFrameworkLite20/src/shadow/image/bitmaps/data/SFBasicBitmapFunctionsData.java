package shadow.image.bitmaps.data;

import shadow.image.bitmaps.SFBasicBitmapFunctions;
import shadow.image.bitmaps.SFBitmapFunction;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFEnumObject;

public class SFBasicBitmapFunctionsData extends SFDataAsset<SFBitmapFunction>{

	private static String[] functionsNames=SFBasicBitmapFunctions.names();
	
	private static SFBasicBitmapFunctions[] functions=SFBasicBitmapFunctions.values();
	
	private SFEnumObject<SFBasicBitmapFunctions> function=new SFEnumObject<SFBasicBitmapFunctions>(functions,functionsNames);
	
	public SFBasicBitmapFunctionsData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("function", function);
		setData(parameters);
	}
	
	@Override
	protected SFBitmapFunction buildResource() {
		return function.getElement();
	}
}
