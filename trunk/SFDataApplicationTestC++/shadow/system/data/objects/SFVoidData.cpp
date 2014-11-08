

#include "SFVoidData.h"



namespace sf{


SFVoidData* SFVoidData::getData() {
	return &voidData;
}

SFVoidData* SFVoidData::clone() {
	return &voidData;
}

void SFVoidData::readFromStream(SFInputStream* stream) {
	//DO not read anything
}
}
