

#ifndef SFIntShortField_H
#define SFIntShortField_H

#include "SFInt.h"

namespace sf{
class SFIntShortField : public SFInt{
    
public:
    
	SFIntShortField(int intValue);
    
	int getShort(int shortIndex);

};
}

#endif /* SFIntShortField_H */
