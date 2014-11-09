

#ifndef SFIntArray_H
#define SFIntArray_H

#include "SFPrimitiveType.h"

namespace sf{
class SFIntArray : public SFPrimitiveType{
    
private:
    int* intValues;
	int n;
    
public:
	SFIntArray(int intValuesSize);
    
    ~SFIntArray();
    
	int* getIntValues();
	
	
	void setIntValues(int* intValues);
    
	
	void readFromStream(SFInputStream* stream);
	
	SFIntArray* clone();
    
};
}


#endif /* SFIntArray_H */
