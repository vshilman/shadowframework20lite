package shadow.system.data.objects;

import shadow.math.SFVertex3f;

public class SFVertex3fData extends SFVectorData{
	
	private SFVertex3f vertex;
	
	public SFVertex3fData(float x,float y,float z) {
		super(3);
		vertex=new SFVertex3f();
		vertex.setArray(getFloatValues());
		vertex.set3f(x,y,z);
	}
	
	public SFVertex3fData() {
		super(3);
		vertex=new SFVertex3f();
		vertex.setArray(getFloatValues());
	}

	public SFVertex3f getVertex3f(){
		return vertex;
	}
	
	@Override
	public SFVectorData clone() {
		return new SFVertex3fData();
	}
}
