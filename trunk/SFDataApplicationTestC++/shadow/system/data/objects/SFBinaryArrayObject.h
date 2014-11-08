
#ifndef SFBinaryArrayObject_H
#define SFBinaryArrayObject_H

#include "SFPrimitiveType.h"

namespace sf{
class SFBinaryArrayObject : public SFPrimitiveType {
    
private:
	int* values;
	int size;
	
public:
	SFBinaryArrayObject(int size);
    
    ~SFBinaryArrayObject();
    
	int* getBytes();
    
	void setBytes(int* bytes);
    
	
	SFPrimitiveType* clone();
	
	int* getValues();
    
	void readFromStream(SFInputStream* stream);
	
};
}

#endif /* SFBinaryArrayObject_H */
