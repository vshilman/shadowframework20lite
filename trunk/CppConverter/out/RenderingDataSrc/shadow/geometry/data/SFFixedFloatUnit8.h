#ifndef shadow_geometry_data_H_
#define shadow_geometry_data_H_

#include "shadow/system/data.objects.SFGenericFixedFloat.h"


class SFFixedFloatUnit8 extends SFGenericFixedFloat{

	static int BIT_SIZE=8;
	static float MULTIPLIER=0.004f;
	ACKMULTIPLIER=250f;

	SFFixedFloatUnit8() {
		super(MULTIPLIER,BACKMULTIPLIER);
	}

	SFFixedFloatUnit8(float multiplier, float backmultiplier) {
		super(multiplier,backmultiplier);
	}

	SFFixedFloatUnit8(float f) {
		super(MULTIPLIER,BACKMULTIPLIER);
		setFloat(f);
	}

	
	SFFixedFloatUnit8 clone() {
		return new SFFixedFloatUnit8();
	}

	
	int getBitSize() {
		return BIT_SIZE;
	}
}
;
}
#endif
