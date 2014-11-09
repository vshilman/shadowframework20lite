
#include "SFFixedFloatUnit8.h"

namespace sf{
	SFFixedFloatUnit8::SFFixedFloatUnit8():SFGenericFixedFloat(MULTIPLIER,BACKMULTIPLIER) {

	}

	SFFixedFloatUnit8::SFFixedFloatUnit8(float multiplier, float backmultiplier):SFGenericFixedFloat(multiplier,backmultiplier)  {

	}

	SFFixedFloatUnit8::SFFixedFloatUnit8(float f) :SFGenericFixedFloat(MULTIPLIER,BACKMULTIPLIER) {
		setFloat(f);
	}

	SFFixedFloatUnit8* SFFixedFloatUnit8::clone() {
		return new SFFixedFloatUnit8();
	}

	int SFFixedFloatUnit8::getBitSize() {
		return BIT_SIZE;
	}
}
