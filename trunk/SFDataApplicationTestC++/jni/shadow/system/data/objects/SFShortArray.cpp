
#include "SFShortArray.h"



namespace sf{
SFShortArray::SFShortArray(int n) {
	this->shortValues = new short[n];
	this->n=n;
}

SFShortArray::~SFShortArray(){
	delete shortValues;
}

short* SFShortArray::getShortValues() {
	return shortValues;
}

void SFShortArray::readFromStream(SFInputStream* stream) {
	delete shortValues;
	int n=stream->readInt();
	shortValues=stream->readShorts(n);
}

SFShortArray* SFShortArray::clone(){
	return new SFShortArray(n);
}

int SFShortArray::getN(){
	return n;
}

}
