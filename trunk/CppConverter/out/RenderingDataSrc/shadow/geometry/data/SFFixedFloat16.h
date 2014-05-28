#ifndef shadow_geometry_data_H_
#define shadow_geometry_data_H_

#include "shadow/system/data.objects.SFGenericFixedFloat.h"


class SFFixedFloat16 extends SFGenericFixedFloat{

	static float MULTIPLIER=0.001f;
	static float BACKMULTIPLIER=1000f;

	SFFixedFloat16() {
		super(MULTIPLIER,BACKMULTIPLIER);
	}

	SFFixedFloat16(float multiplier, float backmultiplier) {
		super(multiplier,backmultiplier);
	}

	SFFixedFloat16(float f) {
		super(MULTIPLIER,BACKMULTIPLIER);
		setFloat(f);
	}

	
	SFFixedFloat16 clone() {
		return new SFFixedFloat16();
	}

	
	int getBitSize() {
		return 16;
	}
}
;
}
#endif
