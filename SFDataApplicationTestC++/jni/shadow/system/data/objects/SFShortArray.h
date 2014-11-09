
#ifndef SFShortArray_H
#define SFShortArray_H

#include "SFPrimitiveType.h"

namespace sf{
class SFShortArray : public SFPrimitiveType{
    
private:
	short* shortValues;
    int n;
	
public:
	SFShortArray(int n);
    
    ~SFShortArray();
    
	short* getShortValues();
    
	void readFromStream(SFInputStream* stream);
	
	SFShortArray* clone();
	
	int getN();

};
}



#endif /* SFShortArray_H */
