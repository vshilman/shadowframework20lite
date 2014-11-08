
#ifndef SFBinaryDataList_H
#define SFBinaryDataList_H


#include "SFPrimitiveType.h"

namespace sf{
template <class T>
class SFBinaryDataList: public SFPrimitiveType {
    
private:
	vector<T*> dataObject;
	T* type;
	int bitSize;
    
public:
	SFBinaryDataList(T* type) {
		this->type = type;
		this->bitSize = type->getBitSize();
	}

    ~SFBinaryDataList() {
		delete type;
		for(int i=0;i<dataObject.size();i++){
            delete dataObject[i];
        }
	}
    
	void setType(T type) {
		this->type = type;
	}
    
	int getBitSize() {
		return bitSize;
	}
    
	vector<T*> getDataObject() {
		return dataObject;
	}
    
	
	int elementsSize() {
		return dataObject.size();
	}
    
	T getType() {
		return type;
	}
    
	
	void readFromStream(SFInputStream* stream) {
		int n = stream->readInt();
		// int bitSize=stream.readInt();
		int* data = stream->readBinaryData(n, bitSize);
		for (int i = 0; i < n; i++) {
			dataObject.push_back((T*) (type->clone()));
			dataObject[i]->setValue(data[i]);
		}
	}
    
	SFPrimitiveType* clone() {
		return new SFBinaryDataList<T>(type);
	}
    
	int getSize() {
		return dataObject.size();
	}
    
};
}



#endif /* SFBinaryDataList_H */
