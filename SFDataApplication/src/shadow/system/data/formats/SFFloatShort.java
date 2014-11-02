package shadow.system.data.formats;

import shadow.system.data.objects.SFGenericFixedFloat;

public class SFFloatShort extends SFGenericFixedFloat{
	
	static float MULTIPLIER=1;
	static float BACKMULTIPLIER=1;
	
	public SFFloatShort() {
		super(MULTIPLIER,BACKMULTIPLIER);
	}
	
	public SFFloatShort(float multiplier, float backmultiplier) {
		super(multiplier,backmultiplier);
	}

	public SFFloatShort(float f) {
		super(MULTIPLIER,BACKMULTIPLIER);
		setFloat(f);
	}
	
	@Override
	public SFFloatShort copyDataObject() {
		return new SFFloatShort();
	}
	
	@Override
	public int getBitSize() {
		return 16;
	}
}
