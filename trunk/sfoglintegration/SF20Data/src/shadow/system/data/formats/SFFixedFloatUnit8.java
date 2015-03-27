package shadow.system.data.formats;

import shadow.system.data.objects.SFGenericFixedFloat;


public class SFFixedFloatUnit8 extends SFGenericFixedFloat{

	private static int BIT_SIZE=8;
	private static float MULTIPLIER=0.008f;
	private static float BACKMULTIPLIER=125f;
	
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
	public SFFixedFloatUnit8 copyDataObject() {
		return new SFFixedFloatUnit8();
	}
	
	@Override
	public int getBitSize() {
		return BIT_SIZE;
	}
}
