
#include "SFShort.h"


namespace sf{
SFShort::SFShort(short shortValue) {
	this->shortValue = shortValue;
}

short SFShort::getShortValue() {
	return shortValue;
}

void SFShort::setShortValue(short shortValue) {
	this->shortValue = shortValue;
}

void SFShort::readFromStream(SFInputStream* stream) {
	shortValue=stream->readShort();
}

SFShort* SFShort::clone(){
	return new SFShort(shortValue);
}
}
