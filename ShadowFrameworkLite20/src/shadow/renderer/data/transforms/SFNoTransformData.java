package shadow.renderer.data.transforms;

import shadow.math.SFTransform3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;

public class SFNoTransformData extends SFDataAsset<SFTransform3f>{

	private SFTransform3f voidTransform=new SFTransform3f();
	
	public SFNoTransformData() {
		setData(new SFNamedParametersObject());
	}
	
	@Override
	public SFNoTransformData generateNewDatasetInstance() {
		return this;
	}
	
	@Override
	protected SFTransform3f buildResource() {
		return voidTransform;
	}
}
