package shadow.renderer.data.transforms;

import shadow.geometry.data.SFFixedFloat16;
import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBinaryObject;

public class SFTranslateFixed16Data extends SFDataAsset<SFTransform3f>{

	private SFBinaryObject<SFFixedFloat16> x=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	private SFBinaryObject<SFFixedFloat16> y=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	private SFBinaryObject<SFFixedFloat16> z=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	
	public SFTranslateFixed16Data(){
		setup();
	}
	
	public SFTranslateFixed16Data(float x,float y,float z){
		setup();
		place(x, y, z);
	}

	public void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("x", x);
		parameters.addObject("y", y);
		parameters.addObject("z", z);
		setData(parameters);
	}
	
	public void place(float x,float y,float z){
		this.x.getBinaryValue().setFloat(x);
		this.y.getBinaryValue().setFloat(y);
		this.z.getBinaryValue().setFloat(z);
	}
	
	@Override
	protected SFTransform3f buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();
		
		SFTransform3f transform=new SFTransform3f();
		transform.setMatrix(matrix);
		transform.setPosition(x.getBinaryValue().getFloat(),y.getBinaryValue().getFloat(),z.getBinaryValue().getFloat());
		
		return transform;
	}
}
