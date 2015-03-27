package shadow.system.data.objects;

import shadow.math.SFVertex2f;

public class SFVertex2fData extends SFVectorData{
	
	private SFVertex2f vertex;
	
	public SFVertex2fData() {
		super(2);
		vertex=new SFVertex2f(getFloatValues());
	}

	public SFVertex2f getVertex2f(){
		return vertex;
	}
	
	@Override
	public SFVectorData copyDataObject() {
		return new SFVertex2fData();
	}
}
