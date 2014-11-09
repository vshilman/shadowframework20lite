
#include "SFBinaryValue.h"

namespace sf{
	SFBinaryValue::SFBinaryValue() {
        //Solve this
		this->value=0;
        //this->value = this->getBitSize();
	}

	int SFBinaryValue::getValue() {
		return value;
	}

	void SFBinaryValue::setValue(int value) {
		this->value = value;
	}
}
