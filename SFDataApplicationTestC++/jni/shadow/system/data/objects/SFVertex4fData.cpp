
#include "SFVertex4fData.h"


namespace sf{
SFVertex4fData::SFVertex4fData() : SFVectorData(4){
	vertex.setArray(getFloatValues());
}

SFVertex4f SFVertex4fData::getVertex4f(){
	return vertex;
}

SFVectorData* SFVertex4fData::clone() {
	return new SFVertex4fData();
}
}
