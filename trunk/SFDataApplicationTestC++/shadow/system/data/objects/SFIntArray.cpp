

#include "SFIntArray.h"


namespace sf{

	SFIntArray::SFIntArray(int intValuesSize) {
		this->intValues = new int[intValuesSize];
        this->n=intValuesSize;
	}

	SFIntArray::~SFIntArray(){
        delete intValues;
    }

	int* SFIntArray::getIntValues() {
		return intValues;
	}


	void SFIntArray::setIntValues(int* intValues) {
		delete intValues;
		this->intValues = intValues;
	}


	void SFIntArray::readFromStream(SFInputStream* stream) {
		delete intValues;
		n=stream->readShort();
		intValues=stream->readInts(n);
	}

	SFIntArray* SFIntArray::clone(){
		return new SFIntArray(n);
	}
}
