#ifndef shadow_geometry_data_H_
#define shadow_geometry_data_H_

#include "shadow/system/data.objects.SFGenericFixedFloat.h"

class SFFloatShort extends SFGenericFixedFloat{

	static float MULTIPLIER=1;
	static float BACKMULTIPLIER=1;

	SFFloatShort() {
		super(MULTIPLIER,BACKMULTIPLIER);
	}

	SFFloatShort(float multiplier, float backmultiplier) {
		super(multiplier,backmultiplier);
	}

	SFFloatShort(float f) {
		super(MULTIPLIER,BACKMULTIPLIER);
		setFloat(f);
	}

	
	SFFloatShort clone() {
		return new SFFloatShort();
	}

	
	int getBitSize() {
		return 16;
	}
}
;
}
#endif
