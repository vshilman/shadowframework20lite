

#include "SFShortByteField.h"


namespace sf{
SFShortByteField::SFShortByteField(short shortValue) : SFShort(shortValue){
}

int SFShortByteField::getByte(int byteIndex) {
	int value = shortValue >> (byteIndex * 8);
	return value & 0xff;
}
}
