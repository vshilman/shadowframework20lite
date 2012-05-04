package shadow.system.data.objects;

import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFVoidData implements SFDataObject{

	private static final SFVoidData data = new SFVoidData();
	
	private SFVoidData(){
		
	}
	
	public static SFVoidData getData() {
		return data;
	}

	@Override
	public SFVoidData clone() {
		return data;
	}
	
	
	@Override
	public void readFromStream(SFInputStream stream) {
		//DO not read anything
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		//DO not write anything
	}
	
}
