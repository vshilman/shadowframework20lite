package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

public class SFByte implements SFWritableDataObject{

	protected byte byteValue;

	public SFByte(int intValue) {
		super();
		this.byteValue = (byte)intValue;
	}

	public byte getByteValue() {
		return byteValue;
	}

	public void setByteValue(int intValue) {
		this.byteValue = (byte)intValue;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		byteValue = stream.readByte();
	}

	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeByte(byteValue);
	}

	@Override
	public SFByte copyDataObject() {
		return new SFByte(byteValue);
	}
	
	@Override
	public void setStringValue(String value) {
		byteValue=new Byte(value);
	}
	
	@Override
	public String toStringValue() {
		return byteValue+"";
	}
}

