

#include "SFBinaryArrayObject.h"


namespace sf{
SFBinaryArrayObject::SFBinaryArrayObject(int size) {
    this->size = size;
    this->values=0;
}

SFBinaryArrayObject::~SFBinaryArrayObject(){
    delete this->values;
}


int* SFBinaryArrayObject::getBytes() {
    return values;
}

void SFBinaryArrayObject::setBytes(int* bytes) {
    this->values = bytes;
}


SFPrimitiveType* SFBinaryArrayObject::clone() {
    return new SFBinaryArrayObject(size);
}

int* SFBinaryArrayObject::getValues() {
    return values;
}


void SFBinaryArrayObject::readFromStream(SFInputStream* stream) {
    short length=stream->readShort();
    this->values=stream->readBinaryData(length, size);
}
}
