package shadow.system.data.objects;

import shadow.system.data.SFCharsetObject;


public class SFShortByteField extends SFShort implements SFCharsetObject{

	public SFShortByteField(short shortValue) {
		super(shortValue);
	}

	public int getByte(int byteIndex) {
		int value = shortValue >> (byteIndex * 8);
		return value & 0xff;
	}

	public void setByte(int byteIndex, int value) {
		value = value & 0xff;
		value = value << (byteIndex * 8);
		int mask = 0xff << (byteIndex * 8);
		mask = (-1) - mask;
		shortValue = (short)(shortValue & mask);
		shortValue += value; 
	}

	@Override
	public SFShortByteField copyDataObject(){
		return new SFShortByteField(shortValue);
	}
	
	@Override
	public void setStringValue(String value) {
		int[] byteValues=new int[2];
		SFCharsetObjectUtils.readInts(byteValues, value, getClass().getSimpleName());
		setByte(0, byteValues[0]);
		setByte(1, byteValues[1]);
	}
	
	@Override
	public String toStringValue() {
		int[] byteValues=new int[2];
		byteValues[0]=getByte(0);
		byteValues[1]=getByte(1);
		return SFCharsetObjectUtils.writeInts(byteValues);
	}
}
