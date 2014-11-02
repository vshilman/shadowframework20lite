package shadow.system.data.objects;

public class SFIntShortField extends SFInt{

	public SFIntShortField(int intValue) {
		super(intValue);
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

	@Override
	public SFIntShortField copyDataObject() {
		return new SFIntShortField(intValue);
	}
	
	@Override
	public void setStringValue(String value) {
		int[] byteValues=new int[2];
		SFCharsetObjectUtils.readInts(byteValues, value, getClass().getSimpleName());
		setShort(0, byteValues[0]);
		setShort(1, byteValues[1]);
	}
	
	@Override
	public String toStringValue() {
		int[] byteValues=new int[2];
		byteValues[0]=getShort(0);
		byteValues[1]=getShort(1);
		return SFCharsetObjectUtils.writeInts(byteValues);
	}
}
