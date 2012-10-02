package shadow.renderer.data.transforms;

import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFVertex3fData;

public class SFTranslationData extends SFDataAsset<SFTransform3f>{

	private SFVertex3fData position=new SFVertex3fData();

	public SFTranslationData(){
		setup();
	}
	
	public SFTranslationData(float x,float y,float z){
		setup();
		position.getVertex3f().set(x,y,z);
	}

	public void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("position", position);
		setData(parameters);
	}
	
	@Override
	protected SFTransform3f buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();
		
		SFTransform3f transform=new SFTransform3f();
		transform.setMatrix(matrix);
		transform.setPosition(this.position.getVertex3f());
		
		return transform;
	}
}
