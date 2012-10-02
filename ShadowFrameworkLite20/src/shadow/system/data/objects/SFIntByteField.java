package shadow.system.data.objects;

public class SFIntByteField extends SFInt{

	public SFIntByteField(int intValue) {
		super(intValue);
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
	

	
	@Override
	public void setStringValue(String value) {
		int[] byteValues=new int[4];
		SFCharsetObjectUtils.readInts(byteValues, value, getClass().getSimpleName());
		setByte(0, byteValues[0]);
		setByte(1, byteValues[1]);
		setByte(2, byteValues[2]);
		setByte(3, byteValues[3]);
	}
	
	@Override
	public String toStringValue() {
		int[] byteValues=new int[2];
		byteValues[0]=getByte(0);
		byteValues[1]=getByte(1);
		byteValues[2]=getByte(2);
		byteValues[3]=getByte(3);
		return SFCharsetObjectUtils.writeInts(byteValues);
	}
}
