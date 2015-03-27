package shadow.system.data.formats;

import shadow.system.data.objects.SFGenericFixedFloat;


public class SFFixedFloat16 extends SFGenericFixedFloat{

	private static int BIT_SIZE=16;
	static float MULTIPLIER=0.001f;
	static float BACKMULTIPLIER=1000f;
	
	public SFFixedFloat16() {
		super(MULTIPLIER,BACKMULTIPLIER);
	}
	
	public SFFixedFloat16(float multiplier, float backmultiplier) {
		super(multiplier,backmultiplier);
	}

	public SFFixedFloat16(float f) {
		super(MULTIPLIER,BACKMULTIPLIER);
		setFloat(f);
	}
	
	@Override
	public SFFixedFloat16 copyDataObject() {
		return new SFFixedFloat16();
	}
	
	@Override
	public int getBitSize() {
		return BIT_SIZE;
	}
}
