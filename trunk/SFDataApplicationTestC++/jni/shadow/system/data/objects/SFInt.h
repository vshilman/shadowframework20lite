
#ifndef SFInt_H
#define SFInt_H

#include "SFPrimitiveType.h"

namespace sf{
class SFInt : public SFPrimitiveType {
    
public:
    int intValue;
    
	SFInt(int intValue);
    
	int getIntValue();
    
	void setIntValue(int intValue);
    
	void readFromStream(SFInputStream* stream);
    
    SFInt* clone();
	
};
}


#endif /* SFInt_H */
