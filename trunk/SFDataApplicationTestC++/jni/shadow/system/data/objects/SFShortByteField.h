

#ifndef SFShortByteField_H
#define SFShortByteField_H

#include "SFShort.h"

namespace sf{
class SFShortByteField :public SFShort{
    
public:
	SFShortByteField(short shortValue);
    
	int getByte(int byteIndex);
    
};
}

#endif /* SFShortByteField_H */
