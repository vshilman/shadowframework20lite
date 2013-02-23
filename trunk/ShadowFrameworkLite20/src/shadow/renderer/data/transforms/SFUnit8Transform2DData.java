package shadow.renderer.data.transforms;

import shadow.geometry.data.SFFixedFloatUnit8;
import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBinaryObject;

public class SFUnit8Transform2DData extends SFDataAsset<SFTransform3f>{

	private SFBinaryObject<SFFixedFloatUnit8> x=new SFBinaryObject<SFFixedFloatUnit8>(new SFFixedFloatUnit8(0));
	private SFBinaryObject<SFFixedFloatUnit8> y=new SFBinaryObject<SFFixedFloatUnit8>(new SFFixedFloatUnit8(0));
	private SFBinaryObject<SFFixedFloatUnit8> rot=new SFBinaryObject<SFFixedFloatUnit8>(new SFFixedFloatUnit8(0));
	private SFBinaryObject<SFFixedFloatUnit8> scale=new SFBinaryObject<SFFixedFloatUnit8>(new SFFixedFloatUnit8(1));
	
	public SFUnit8Transform2DData(){
		setup();
	}

	public void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("x", x);
		parameters.addObject("y", y);
		parameters.addObject("rot", rot);
		parameters.addObject("scale", scale);
		setData(parameters);
	}
	
	@Override
	protected SFTransform3f buildResource() {
		SFMatrix3f matrix=SFMatrix3f.getRotationY(rot.getBinaryValue().getFloat()*(float)(2*Math.PI));
		matrix.mult(scale.getBinaryValue().getFloat());
		
		SFTransform3f transform=new SFTransform3f();
		transform.setMatrix(matrix);
		transform.setPosition(x.getBinaryValue().getFloat(),y.getBinaryValue().getFloat(),0);
		
		return transform;
	}
}