package shadow.system.data.objects;

import shadow.system.data.objects.SFBinaryValue;

public abstract class SFGenericFixedFloat extends SFBinaryValue {

	protected float multiplier ;
	protected float backmultiplier ;

	public abstract int getBitSize();
	public abstract SFGenericFixedFloat clone();

	public SFGenericFixedFloat(float multiplier, float backmultiplier) {
		super();
		this.multiplier = multiplier;
		this.backmultiplier = backmultiplier;
	}

	public float getFloat() {
		short value=(short)(this.value);
		return value*multiplier;
	}
	
	public void setFloat(float f) {
		short data=(short)(f*backmultiplier);
		this.value=(data<0?data+256*256:data);
	}

	@Override
	public void setValue(int value) {
		super.setValue(value);
	}

	@Override
	public void setStringValue(String value) {
		setFloat(new Float(value));
	}

	@Override
	public String toStringValue() {
		return getFloat()+"";
	}

}