package shadow.geometry.data;

import shadow.system.data.objects.SFGenericFixedFloat;


public class SFFixedFloatUnit8 extends SFGenericFixedFloat{

	private static int BIT_SIZE=8;
	private static float MULTIPLIER=0.004f;
	private static float BACKMULTIPLIER=250f;
	
	public SFFixedFloatUnit8() {
		super(MULTIPLIER,BACKMULTIPLIER);
	}
	
	public SFFixedFloatUnit8(float multiplier, float backmultiplier) {
		super(multiplier,backmultiplier);
	}

	public SFFixedFloatUnit8(float f) {
		super(MULTIPLIER,BACKMULTIPLIER);
		setFloat(f);
	}
	
	@Override
	public SFFixedFloatUnit8 clone() {
		return new SFFixedFloatUnit8();
	}
	
	@Override
	public int getBitSize() {
		return BIT_SIZE;
	}
}
