package shadow.renderer.data.transforms;

import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFVertex3fData;

public class SFTranslateAndScaleData extends SFDataAsset<SFTransform3f>{

	private SFVertex3fData position=new SFVertex3fData();
	private SFFloat scale=new SFFloat(1);

	public SFTranslateAndScaleData(){
		setup();
	}
	
	public SFTranslateAndScaleData(float x,float y,float z,float scale){
		setup();
		place(x, y, z, scale);
	}

	public void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("position", position);
		parameters.addObject("scale", scale);
		setData(parameters);
	}
	
	public void place(float x,float y,float z,float scale){
		position.getVertex3f().set(x,y,z);
		this.scale.setFloatValue(scale);
	}
	
	@Override
	protected SFTransform3f buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();
		matrix.mult(scale.getFloatValue());
		
		SFTransform3f transform=new SFTransform3f();
		transform.setMatrix(matrix);
		transform.setPosition(this.position.getVertex3f());
		
		return transform;
	}
}
