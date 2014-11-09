
#include "SFFloat.h"



namespace sf{
	SFFloat::SFFloat() {
		this->floatValue = 0;
	}

	SFFloat::SFFloat(float floatValue) {
		this->floatValue = floatValue;
	}

	float SFFloat::getFloatValue() {
		return floatValue;
	}

	void SFFloat::setFloatValue(float floatValue) {
		this->floatValue = floatValue;
	}


	void SFFloat::readFromStream(SFInputStream* stream) {
		floatValue=stream->readFloat();
	}

	SFFloat* SFFloat::clone() {
		return new SFFloat(floatValue);
	}
}
