

#include "SFIntShortField.h"


namespace sf{

	SFIntShortField::SFIntShortField(int intValue) : SFInt(intValue){

    }

	int SFIntShortField::getShort(int shortIndex) {
		int value = intValue >> (shortIndex * 16);
		return value & 0xffff;
	}
}
