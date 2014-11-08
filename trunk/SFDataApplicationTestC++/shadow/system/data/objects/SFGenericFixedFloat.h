
#ifndef SFGenericFixedFloat_H
#define SFGenericFixedFloat_H

#include "SFBinaryValue.h"

namespace sf{
class SFGenericFixedFloat : public SFBinaryValue {
    
public:
	float multiplier ;
	float backmultiplier ;
    
	virtual int getBitSize()=0;
	virtual SFGenericFixedFloat* clone()=0;
    
	SFGenericFixedFloat(float multiplier, float backmultiplier);
    
	float getFloat();
	
	void setFloat(float f);

};

}


#endif /* SFGenericFixedFloat_H */
