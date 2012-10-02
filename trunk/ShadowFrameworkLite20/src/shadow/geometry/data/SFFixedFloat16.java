package shadow.geometry.data;

import shadow.system.data.objects.SFGenericFixedFloat;


public class SFFixedFloat16 extends SFGenericFixedFloat{

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
	public SFFixedFloat16 clone() {
		return new SFFixedFloat16();
	}
	
	@Override
	public int getBitSize() {
		return 16;
	}
}
