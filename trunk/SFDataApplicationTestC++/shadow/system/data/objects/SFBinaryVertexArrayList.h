

#ifndef SFBinaryVertexArrayList_H
#define SFBinaryVertexArrayList_H


#include "SFPrimitiveType.h"
#include "../../../math/SFValuenf.h"

namespace sf{
template <class T>
class SFBinaryVertexArrayList : public SFPrimitiveType {
    
private:
	//TODO i would appreciate if this was an array of integers instead.. or shorts..
	vector<T*> dataObject;
	T* type;
	int* vertexSize;
	int totalSize;
	int bitSize;
    
public:
    
	SFBinaryVertexArrayList(T* type) {
		this->type = type;
		this->bitSize = type->getBitSize();
		this->totalSize=-1;
		this->vertexSize=0;
	}
    
    ~SFBinaryVertexArrayList(){
        delete type;
        for(int i=0;i<dataObject.size();i++){
            delete dataObject[i];
        }
        delete vertexSize; 
    }
	
	void setType(T type) {
		this->type = type;
	}
    
	int getBitSize() {
		return bitSize;
	}
    
	int getVertexCount(){
		return dataObject.size()/totalSize;
	}
	
	void setVertexSize(int* vertexSize) {
		this->vertexSize = vertexSize;
	}
    
	SFValuenf getValue(int index,int vertexIndex,SFValuenf value){
		int pos=index*totalSize;
		for (int i = 0; i < vertexIndex; i++) {
			pos+=vertexSize[i];
		}
		for (int i = 0; i < vertexSize[vertexIndex]; i++) {
			value.getV()[i]=dataObject.get(pos+i).getFloat();
		}
		return value;
	}
	
	vector<T*> getDataObject() {
		return dataObject;
	}
    
	
	int elementsSize() {
		return dataObject.size();
	}
    
	T* getType() {
		return type;
	}
    
	void readFromStream(SFInputStream* stream) {
		int n = stream->readShort();
        int length=stream->readByte();
		vertexSize=new int[length];
		totalSize=0;
		for (int i = 0; i < length; i++) {
			vertexSize[i]=stream->readByte();
			totalSize+=vertexSize[i];
		}
		// int bitSize=stream.readInt();
		int* data = stream->readBinaryData(n, bitSize);
		for (int i = 0; i < n; i++) {
			dataObject.push_back((T*) (type->clone()));
			dataObject.at(i).setValue(data[i]);
		}
	}
    
	SFPrimitiveType* clone() {
		return new SFBinaryVertexArrayList<T>(type);
	}
    
	
	int getSize() {
		return dataObject.size()/totalSize;
	}
    
	
};
}



#endif /* SFBinaryVertexArrayList_H */
