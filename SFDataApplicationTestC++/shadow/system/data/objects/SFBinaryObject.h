

#ifndef SFBinaryObject_H
#define SFBinaryObject_H

#include "SFPrimitiveType.h"
#include "SFBinaryValue.h"


namespace sf{
template <class T>
class SFBinaryObject:public SFPrimitiveType{
	
private:
    T* binaryValue;
    
public:
	SFBinaryObject(T* binaryValue) {
		this->binaryValue = binaryValue;
	}
    
    ~SFBinaryObject(){
        delete this->binaryValue;
    }
    
	T* getBinaryValue() {
		return binaryValue;
	}
	
	SFPrimitiveType* clone() {
		return new SFBinaryObject<T>(binaryValue->clone());
	}
	
	void readFromStream(SFInputStream* stream) {
		this->binaryValue->value=stream->readBinaryData(binaryValue->getBitSize());
	}

	
};

}




#endif /* defined(SFBinaryObject__) */
