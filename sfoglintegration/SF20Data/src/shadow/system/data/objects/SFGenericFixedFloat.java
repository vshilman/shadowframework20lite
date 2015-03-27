package shadow.system.data.objects;


public abstract class SFGenericFixedFloat extends SFBinaryValue {

	protected float multiplier ;
	protected float backmultiplier ;

	public abstract int getBitSize();
	public abstract SFGenericFixedFloat copyDataObject();

	public SFGenericFixedFloat(float multiplier, float backmultiplier) {
		super();
		this.multiplier = multiplier;
		this.backmultiplier = backmultiplier;
	}

	public float getFloat() {
		int value=(int)(this.value);
		return value*multiplier;
	}
	
	public void setFloat(float f) {
		int data=(int)(f*backmultiplier);
		this.value=data;
		//this.value=(data<0?data+256*256:data);
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

	@Override
	public String toString() {
		return toStringValue();
	}
}