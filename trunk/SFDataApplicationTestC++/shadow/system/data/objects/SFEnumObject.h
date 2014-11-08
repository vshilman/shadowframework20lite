

#ifndef SFEnumObject_H
#define SFEnumObject_H

#include "SFPrimitiveType.h"

namespace sf{
template <class T>
class SFEnumObject: public SFPrimitiveType{
    
private:

    T* elements;
	int index=0;
	
public:
	SFEnumObject(T* elements) {
		this->elements = elements;
		this->index=0;
	}
	
    ~SFEnumObject(){
        delete elements;
    }
	
	SFEnumObject<T> clone() {
		return new SFEnumObject<T>(elements);
	}
    
	int getIndex() {
		return index;
	}
    
	void setIndex(int index) {
		this->index = index;
	}
	
	
	void readFromStream(SFInputStream* stream) {
		index=stream->readShort();
	}
	
	T getElement(){
		return elements[index];
	}
	
};
}


#endif /* SFEnumObject_H */
