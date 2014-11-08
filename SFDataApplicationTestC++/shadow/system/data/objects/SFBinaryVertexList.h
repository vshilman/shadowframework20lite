
#ifndef SFBinaryVertexList_H
#define SFBinaryVertexList_H

#include "SFPrimitiveType.h"
#include "../../../math/SFValuenf.h" 


namespace sf{
template <class T>
class SFBinaryVertexList: public SFPrimitiveType{
    
private:
	//TODO i would appreciate if this was an array of integers instead.. or shorts..
	vector<T*> dataObject = new vector<T>();
	T* type;
	int vertexSize=-1;
	int bitSize;
    
public:
	SFBinaryVertexList(T* type) {
		this->type = type;
		this->bitSize = type->getBitSize();
		this->vertexSize=0;
	}
    
    ~SFBinaryVertexList(){
        delete type;
        for(int i=0;i<dataObject.size();i++){
            delete dataObject[i];
        }
    }
	
	void setType(T* type) {
		this->type = type;
	}
    
	int getBitSize() {
		return bitSize;
	}
    
	int getVertexCount(){
		return dataObject.size()/vertexSize;
	}
	
	void setVertexSize(int vertexSize) {
		this->vertexSize = vertexSize;
	}

	int getVertexSize() {
		return this->vertexSize;
	}
    
	SFValuenf getValue(int index,SFValuenf* value){
		for (int i = 0; i < vertexSize && i<value->getSize(); i++) {
			value->getV()[i]=dataObject.get(index*vertexSize+i).getFloat();
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
		vertexSize=stream->readByte();
		// int bitSize=stream.readInt();
		int* data = stream->readBinaryData(n, bitSize);
		for (int i = 0; i < n; i++) {
			dataObject.push_back((T*) (type->clone()));
			dataObject.at(i).setValue(data[i]);
		}
	}
    
	SFPrimitiveType* clone() {
		return new SFBinaryVertexList<T>(type);
	}
    
	int getSize() {
		return dataObject.size()/vertexSize;
	}
    
};

}

#endif /* SFBinaryVertexList_H */
