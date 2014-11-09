
#include "SFVertex2fData.h"


namespace sf{

SFVertex2fData::SFVertex2fData() :SFVectorData(2){
	this->vertex.set2f(0.0f,0.0f);
	vertex.setArray(getFloatValues());
}

SFVertex2f SFVertex2fData::getVertex2f(){
	return vertex;
}


SFVectorData* SFVertex2fData::clone() {
	return new SFVertex2fData();
}
}
