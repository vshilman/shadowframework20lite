package shadow.geometry.functions.data;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.data.SFFixedFloat16;
import shadow.geometry.functions.SFRectangle2DFunction;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBinaryObject;

public class SFRectangle2DFunctionData extends SFDataAsset<SFSurfaceFunction>{

	private SFBinaryObject<SFFixedFloat16> x=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	private SFBinaryObject<SFFixedFloat16> y=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	private SFBinaryObject<SFFixedFloat16> w=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	private SFBinaryObject<SFFixedFloat16> h=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	
	public SFRectangle2DFunctionData() {
		SFNamedParametersObject parametersObject=new SFNamedParametersObject();
		parametersObject.addObject("x", x);
		parametersObject.addObject("y", y);
		parametersObject.addObject("w", w);
		parametersObject.addObject("h", h);
		setData(parametersObject);
	}
	
	public SFRectangle2DFunctionData(float x,float y,float w,float h) {
		//setData(data);
		SFNamedParametersObject parametersObject=new SFNamedParametersObject();
		parametersObject.addObject("x", this.x);
		parametersObject.addObject("y", this.y);
		parametersObject.addObject("w", this.w);
		parametersObject.addObject("h", this.h);
		setData(parametersObject);
		this.x.getBinaryValue().setFloat(x);
		this.y.getBinaryValue().setFloat(y);
		this.w.getBinaryValue().setFloat(w);
		this.h.getBinaryValue().setFloat(h);
	}
	
	@Override
	protected SFRectangle2DFunction buildResource() {
		SFRectangle2DFunction function=new SFRectangle2DFunction(
				x.getBinaryValue().getFloat(),
				y.getBinaryValue().getFloat(),
				w.getBinaryValue().getFloat(),
				h.getBinaryValue().getFloat()
		);
		return function;
	}
	
}
