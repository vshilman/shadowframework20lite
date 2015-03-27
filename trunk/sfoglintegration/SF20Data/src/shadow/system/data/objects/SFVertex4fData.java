package shadow.system.data.objects;

import shadow.math.SFVertex4f;

public class SFVertex4fData extends SFVectorData{
	
	private SFVertex4f vertex;
	
	public SFVertex4fData() {
		super(4);
		vertex=new SFVertex4f(getFloatValues());
	}

	public SFVertex4f getVertex4f(){
		return vertex;
	}
	
	@Override
	public SFVectorData copyDataObject() {
		return new SFVertex4fData();
	}
}
