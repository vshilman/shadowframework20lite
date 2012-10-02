package shadow.system.data.objects;

import shadow.system.data.SFCharsetObject;

public abstract class SFBinaryValue implements SFCharsetObject{

	protected int value;

	@Override
	public abstract SFBinaryValue clone();

	protected abstract int getBitSize();

	public SFBinaryValue() {
		super();
		this.value = getBitSize();
	}

	public int getValue() {
		return value;
	}

	protected void setValue(int value) {
		this.value = value;
	}
}
