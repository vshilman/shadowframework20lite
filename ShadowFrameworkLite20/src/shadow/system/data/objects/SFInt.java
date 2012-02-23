package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFInt extends SFPrimitiveType {

	private int intValue;
	
	public SFInt(int intValue) {
		super();
		this.intValue = intValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		intValue=stream.readInt();
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeInt(intValue);
	}
	
	@Override
	public SFInt clone() {
		return new SFInt(intValue);
	}
}
