
#include "SFFixedFloat16.h"

namespace sf{

SFFixedFloat16::SFFixedFloat16():SFGenericFixedFloat(MULTIPLIER,BACKMULTIPLIER) {
}

SFFixedFloat16::SFFixedFloat16(float multiplier, float backmultiplier):SFGenericFixedFloat(multiplier,backmultiplier) {
}

SFFixedFloat16::SFFixedFloat16(float f):SFGenericFixedFloat(MULTIPLIER,BACKMULTIPLIER) {
	setFloat(f);
}


SFFixedFloat16* SFFixedFloat16::clone() {
	return new SFFixedFloat16();
}


int SFFixedFloat16::getBitSize() {
	return 16;
}

}
