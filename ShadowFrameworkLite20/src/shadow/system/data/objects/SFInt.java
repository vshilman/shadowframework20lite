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
		intValue = stream.readInt();
	}

	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeInt(intValue);
	}

	@Override
	public SFInt clone() {
		return new SFInt(intValue);
	}

	public int getByte(int byteIndex) {
		int value = intValue >> (byteIndex * 8);
		return value & 0xff;
	}

	public void setByte(int byteIndex, int value) {
		value = value & 0xff;
		value = value << (byteIndex * 8);
		int mask = 0xff << (byteIndex * 8);
		mask = (-1) - mask;
		intValue = intValue & mask;
		intValue += value; 
	}
	
	public int getShort(int shortIndex) {
		int value = intValue >> (shortIndex * 16);
		return value & 0xffff;
	}

	public void setShort(int shortIndex, int value) {
		value = value & 0xffff;
		value = value << (shortIndex * 16);
		int mask = 0xffff << (shortIndex * 16);
		mask = (-1) - mask;
		intValue = intValue & mask;
		intValue += value; 
	}
}
