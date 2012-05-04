package shadow.geometry.curves.data;

import shadow.system.data.objects.SFBinaryValue;

public class SFFixedFloat extends SFBinaryValue{

	private static float MULTIPLIER=0.001f;
	private static float BACKMULTIPLIER=1000f;
	
	public SFFixedFloat() {
	
	}
	
	public SFFixedFloat(float f) {
		setFloat(f);
	}
	
	@Override
	public SFBinaryValue clone() {
		return new SFFixedFloat();
	}
	
	@Override
	protected int getBitSize() {
		return 16;
	}
	
	public float getFloat(){
		short value=(short)(this.value);
		return value*MULTIPLIER;
	}
	
	public void setFloat(float f){
		short data=(short)(f*BACKMULTIPLIER);
		this.value=(data<0?data+256*256:data);
	}
	
	@Override
	public void setValue(int value) {
		super.setValue(value);
	}
}
