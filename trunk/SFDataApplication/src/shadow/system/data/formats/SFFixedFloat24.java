package shadow.system.data.formats;

import shadow.system.data.objects.SFGenericFixedFloat;


public class SFFixedFloat24 extends SFGenericFixedFloat{

	static float MULTIPLIER=0.0001f;
	static float BACKMULTIPLIER=10000f;
	
	public SFFixedFloat24() {
		super(MULTIPLIER,BACKMULTIPLIER);
	}
	
	public SFFixedFloat24(float multiplier, float backmultiplier) {
		super(multiplier,backmultiplier);
	}

	public SFFixedFloat24(float f) {
		super(MULTIPLIER,BACKMULTIPLIER);
		setFloat(f);
	}
	
	@Override
	public SFFixedFloat24 copyDataObject() {
		return new SFFixedFloat24();
	}
	
	@Override
	public int getBitSize() {
		return 24;
	}
}
