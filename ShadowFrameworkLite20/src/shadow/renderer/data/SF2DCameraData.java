package shadow.renderer.data;

import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;

public class SF2DCameraData extends SFDataAsset<SFCamera>{

	private SFFloat leftL=new SFFloat(0);
	private SFFloat upL=new SFFloat(0);
	
	public SF2DCameraData() {
		prepare();
	}
	public SF2DCameraData(float leftL,float upL) {
		prepare();
		setDimensions(leftL, upL);
	}

	private void prepare() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("leftL", leftL);
		parameters.addObject("upL", upL);
		setData(parameters);
	}
	
	@Override
	protected SFCamera buildResource() {
		SFCamera camera=new SFCamera(new SFVertex3f(0,0,0),
				new SFVertex3f(1,0,0),new SFVertex3f(0,1,0),new SFVertex3f(0,0,1),0,leftL.getFloatValue(),
				upL.getFloatValue());
		return camera;
	}
	
	public void setDimensions(float leftL,float upL){
		this.leftL.setFloatValue(leftL);
		this.upL.setFloatValue(upL);
	}
}
