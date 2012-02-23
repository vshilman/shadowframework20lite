package shadow.system.data.objects;

import shadow.system.data.SFDataObject;

public abstract class SFPrimitiveType implements SFDataObject{

	public int elementsSize(){
		return 0;
	}
	
	//NOTE: should never be called
	public SFDataObject sonsObject(int son){
		return null;
	}
	
	public abstract SFPrimitiveType clone();
}
