
#include "SFIntByteField.h"


namespace sf{
SFIntByteField::SFIntByteField(int intValue):SFInt(intValue) {

}

int SFIntByteField::getByte(int byteIndex) {
	int value = intValue >> (byteIndex * 8);
	return value & 0xff;
}
}
