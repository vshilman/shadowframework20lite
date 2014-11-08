
#ifndef SFShort_H
#define SFShort_H

#include "SFPrimitiveType.h"

namespace sf{
class SFShort : public SFPrimitiveType {
    
public:
	short shortValue;
	
	SFShort(short shortValue);
    
	short getShortValue();
    
	void setShortValue(short shortValue);
    
	void readFromStream(SFInputStream* stream);
	
	SFShort* clone();
	
};
}
#endif /* SFShort_H */
