
#include "SFBinaryFloatArrayObject.h"


namespace sf{
SFBinaryFloatArrayObject::SFBinaryFloatArrayObject(int size,int dim) {
		this->size = size;
		this->dim = dim;
		this->values=0;
	}

SFBinaryFloatArrayObject::~SFBinaryFloatArrayObject(){
	delete values;
}

SFBinaryFloatArrayObject* SFBinaryFloatArrayObject::clone() {
	return new SFBinaryFloatArrayObject(size,dim);
}

float* SFBinaryFloatArrayObject::getValues() {
	return values;
}

void SFBinaryFloatArrayObject::setValues(float* values) {
	this->values = values;
}


void SFBinaryFloatArrayObject::readFromStream(SFInputStream* stream) {
	short length=stream->readShort();
	int* values=stream->readBinaryData(length, size);
	this->values=new float[length];
	float recDim=1.0f/dim;
	for (int i = 0; i < length; i++) {
		this->values[i]=values[i]*recDim;
	}
}
}
