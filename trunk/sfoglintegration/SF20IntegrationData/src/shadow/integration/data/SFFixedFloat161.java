package shadow.integration.data;

import shadow.system.data.objects.SFGenericFixedFloat;

public class SFFixedFloat161 extends SFGenericFixedFloat{

	private static int BIT_SIZE=16;
	static float MULTIPLIER=0.0001f;
	static float BACKMULTIPLIER=10000f;
	
	public SFFixedFloat161() {
		super(MULTIPLIER,BACKMULTIPLIER);
	}
	
	public SFFixedFloat161(float multiplier, float backmultiplier) {
		super(multiplier,backmultiplier);
	}

	public SFFixedFloat161(float f) {
		super(MULTIPLIER,BACKMULTIPLIER);
		setFloat(f);
	}
	
	@Override
	public SFFixedFloat161 copyDataObject() {
		return new SFFixedFloat161();
	}
	
	@Override
	public int getBitSize() {
		return BIT_SIZE;
	}
}
