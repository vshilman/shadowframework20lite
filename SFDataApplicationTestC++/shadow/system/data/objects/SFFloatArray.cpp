

#include "SFFloatArray.h"



namespace sf{
	SFFloatArray::SFFloatArray(int floatValuesSize) {
		this->floatValues = new float[floatValuesSize];
        this->n=floatValuesSize;
	}

	SFFloatArray::~SFFloatArray(){
        delete floatValues;
    }

	float* SFFloatArray::getFloatValues() {
		return floatValues;
	}

	void SFFloatArray::setFloatValues(float* FloatValues) {
		delete floatValues;
		this->floatValues = FloatValues;
	}


	void SFFloatArray::readFromStream(SFInputStream* stream) {
		delete floatValues;
		n=stream->readShort();
		floatValues=stream->readFloats(n);
	}

	SFFloatArray* SFFloatArray::clone(){
		return new SFFloatArray(n);
	}
}
