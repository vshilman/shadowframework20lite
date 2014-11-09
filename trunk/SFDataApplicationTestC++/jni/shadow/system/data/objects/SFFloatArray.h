

#ifndef SFFloatArray_H
#define SFFloatArray_H


#include "SFPrimitiveType.h"

namespace sf{
class SFFloatArray : public SFPrimitiveType{
    
private:
	float* floatValues;
    int n;
    
public:
	SFFloatArray(int floatValuesSize);
    
    ~SFFloatArray();
	
	float* getFloatValues();
    
	void setFloatValues(float* FloatValues);
    
	
	void readFromStream(SFInputStream* stream);
	
	SFFloatArray* clone();
  
};
}

#endif /* SFFloatArray_H */
