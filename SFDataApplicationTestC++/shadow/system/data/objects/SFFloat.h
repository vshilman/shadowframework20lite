
#ifndef SFFloat_H
#define SFFloat_H

#include "SFPrimitiveType.h"

namespace sf{
class SFFloat : public SFPrimitiveType{
    
private:
    float floatValue;
	
public:
	SFFloat() ;
	
	SFFloat(float floatValue) ;
    
	float getFloatValue() ;
    
	void setFloatValue(float floatValue) ;
    
	
	void readFromStream(SFInputStream* stream);
	
	SFFloat* clone() ;
	
};
}


#endif /* SFFloat_H */
