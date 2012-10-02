package shadow.renderer.data.transforms;

import shadow.math.SFEulerAngles3f;
import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFVertex3fData;

public class SFRigidTransformData extends SFDataAsset<SFTransform3f>{

	private SFVertex3fData position=new SFVertex3fData();

	private SFVertex3fData orientation=new SFVertex3fData();
	
	private SFFloat scale=new SFFloat(1);

	public SFRigidTransformData(){
		setup();
	}
	
	public SFRigidTransformData(float x,float y,float z,float scale){
		setup();
		place(x, y, z, scale);
	}

	public SFRigidTransformData(float x,float y,float z,float scale,SFMatrix3f orientation){
		setup();
		place(x, y, z, scale,orientation);
	}
	
	public void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("position", position);
		parameters.addObject("orientation", orientation);
		parameters.addObject("scale", scale);
		setData(parameters);
	}
	
	public void place(float x,float y,float z,float scale){
		position.getVertex3f().set(x,y,z);
		this.scale.setFloatValue(scale);
	}
	
	public void place(float x,float y,float z,SFMatrix3f orientation){
		position.getVertex3f().set(x,y,z);
		SFEulerAngles3f angles=new SFEulerAngles3f(orientation);
		this.orientation.getVertex3f().set(angles);
	}
	
	public void place(float x,float y,float z,float scale,SFMatrix3f orientation){
		position.getVertex3f().set(x,y,z);
		this.scale.setFloatValue(scale);
		SFEulerAngles3f angles=new SFEulerAngles3f(orientation);
		this.orientation.getVertex3f().set(angles);
	}
	
	@Override
	protected SFTransform3f buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();
		SFEulerAngles3f angles=new SFEulerAngles3f();
		angles.set(orientation.getVertex3f());
		angles.getMatrix(matrix);
		matrix.mult(scale.getFloatValue());
		
		SFTransform3f transform=new SFTransform3f();
		transform.setMatrix(matrix);
		transform.setPosition(this.position.getVertex3f());
		
		return transform;
	}
}
