

#include "SFMatrix3fData.h"


namespace sf{
SFMatrix3fData::SFMatrix3fData(SFMatrix3f matrix) : SFVectorData(9){
	this->matrix.setArray(getFloatValues());
	this->matrix.set(&matrix);
}

SFMatrix3fData::SFMatrix3fData() : SFVectorData(9){
	matrix.setArray(getFloatValues());
    SFMatrix3f temp=SFMatrix3f::getIdentity();
	this->matrix.set(&(temp));
}

SFMatrix3f SFMatrix3fData::getMatrix3f(){
	return matrix;
}

SFVectorData* SFMatrix3fData::clone() {
	return new SFMatrix3fData();
}
}
