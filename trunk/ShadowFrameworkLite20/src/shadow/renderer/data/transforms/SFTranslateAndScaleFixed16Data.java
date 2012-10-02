package shadow.renderer.data.transforms;

import shadow.geometry.data.SFFixedFloat16;
import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBinaryObject;

public class SFTranslateAndScaleFixed16Data extends SFDataAsset<SFTransform3f>{

	private SFBinaryObject<SFFixedFloat16> x=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(0));
	private SFBinaryObject<SFFixedFloat16> y=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(0));
	private SFBinaryObject<SFFixedFloat16> z=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(0));
	private SFBinaryObject<SFFixedFloat16> scale=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(1));
	
	public SFTranslateAndScaleFixed16Data(){
		setup();
	}
	
	public SFTranslateAndScaleFixed16Data(float x,float y,float z,float scale){
		setup();
		place(x, y, z, scale);
	}

	public void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("x", x);
		parameters.addObject("y", y);
		parameters.addObject("z", z);
		parameters.addObject("scale", scale);
		setData(parameters);
	}
	
	public void place(float x,float y,float z,float scale){
		this.x.getBinaryValue().setFloat(x);
		this.y.getBinaryValue().setFloat(y);
		this.z.getBinaryValue().setFloat(z);
		this.scale.getBinaryValue().setFloat(scale);
	}
	
	@Override
	protected SFTransform3f buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();
		matrix.mult(scale.getBinaryValue().getFloat());
		
		SFTransform3f transform=new SFTransform3f();
		transform.setMatrix(matrix);
		transform.setPosition(x.getBinaryValue().getFloat(),y.getBinaryValue().getFloat(),z.getBinaryValue().getFloat());
		
		return transform;
	}
}
