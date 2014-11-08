
#include "SFFloatShort.h"

namespace sf{

SFFloatShort::SFFloatShort():SFGenericFixedFloat(MULTIPLIER,BACKMULTIPLIER) {

}

SFFloatShort::SFFloatShort(float multiplier, float backmultiplier):SFGenericFixedFloat(multiplier,backmultiplier) {

}

SFFloatShort::SFFloatShort(float f):SFGenericFixedFloat(MULTIPLIER,BACKMULTIPLIER) {
	setFloat(f);
}

SFFloatShort* SFFloatShort::clone() {
	return new SFFloatShort();
}

int SFFloatShort::getBitSize() {
	return 16;
}

}
