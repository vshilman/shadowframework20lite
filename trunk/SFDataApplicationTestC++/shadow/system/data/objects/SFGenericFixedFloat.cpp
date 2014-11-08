

#include "SFGenericFixedFloat.h"


namespace sf{

	SFGenericFixedFloat::SFGenericFixedFloat(float multiplier, float backmultiplier) {
		this->multiplier = multiplier;
		this->backmultiplier = backmultiplier;
	}

	float SFGenericFixedFloat::getFloat() {
		short value=(short)(this->value);
		return value*multiplier;
	}

	void SFGenericFixedFloat::setFloat(float f) {
		short data=(short)(f*backmultiplier);
		this->value=(data<0?data+256*256:data);
	}
}
