package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

public class SFInt implements SFWritableDataObject{

	protected int intValue;

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
		intValue = stream.readInt();
	}

	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeInt(intValue);
	}

	@Override
	public SFInt copyDataObject() {
		return new SFInt(intValue);
	}
	
	@Override
	public void setStringValue(String value) {
		intValue=new Integer(value);
	}
	
	@Override
	public String toStringValue() {
		return intValue+"";
	}
}
