package shadow.renderer.data;

import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SF2DCameraData extends SFDataAsset<SFCamera>{

	private SFFloat leftL=new SFFloat(0);
	private SFFloat upL=new SFFloat(0);
	
	public SF2DCameraData() {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(leftL,upL));
	}
	
	public SF2DCameraData(float leftL,float upL) {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(this.leftL,this.upL));
		setDimensions(leftL, upL);
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
