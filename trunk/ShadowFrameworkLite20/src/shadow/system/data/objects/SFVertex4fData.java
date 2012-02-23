package shadow.system.data.objects;

import shadow.math.SFVertex4f;

public class SFVertex4fData extends SFVectorData{
	
	private SFVertex4f vertex;
	
	public SFVertex4fData() {
		super(3);
		vertex=new SFVertex4f();
		vertex.setArray(getFloatValues());
	}

	public SFVertex4f getVertex4f(){
		return vertex;
	}
	
	@Override
	public SFVectorData clone() {
		return new SFVertex4fData();
	}
}
