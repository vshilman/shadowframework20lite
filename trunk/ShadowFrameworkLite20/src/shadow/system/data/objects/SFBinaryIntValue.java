package shadow.system.data.objects;

public class SFBinaryIntValue extends SFBinaryValue{

	public SFBinaryIntValue(int bits) {		
		super();
		setValue(bits);
	}
	
	@Override
	public void setValue(int value) {
		super.setValue(value);
	}

	@Override
	public SFBinaryValue clone() {
		return new SFBinaryIntValue(getValue());
	}
	
	@Override
	protected int getBitSize() {
		return 0;
	}
}
