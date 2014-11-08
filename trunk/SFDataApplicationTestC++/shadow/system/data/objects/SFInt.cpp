

#include "SFInt.h"


namespace sf{
	SFInt::SFInt(int intValue) {
		this->intValue = intValue;
	}

	int SFInt::getIntValue() {
		return intValue;
	}

	void SFInt::setIntValue(int intValue) {
		this->intValue = intValue;
	}

	void SFInt::readFromStream(SFInputStream* stream) {
		intValue = stream->readInt();
	}

    SFInt* SFInt::clone() {
		return new SFInt(intValue);
	}
}
