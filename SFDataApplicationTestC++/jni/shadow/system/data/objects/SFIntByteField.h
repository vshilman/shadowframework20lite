
#ifndef SFIntByteField_H
#define SFIntByteField_H


#include "SFInt.h"

namespace sf{
class SFIntByteField :SFInt{

public:
	SFIntByteField(int intValue);
    
	int getByte(int byteIndex) ;
    
};
}

#endif /* SFIntByteField_H */
