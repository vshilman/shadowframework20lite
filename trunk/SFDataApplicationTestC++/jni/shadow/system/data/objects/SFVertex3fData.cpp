
#include "SFVertex3fData.h"


namespace sf{

SFVertex3fData::SFVertex3fData(float x,float y,float z) :SFVectorData(vertex.getV(),3){
    vertex.set3f(x,y,z);
}

SFVertex3fData::SFVertex3fData() :SFVectorData(vertex.getV(),3){
	vertex.set3f(0,0,0);
}

SFVertex3f SFVertex3fData::getVertex3f(){
	return vertex;
}

SFVectorData* SFVertex3fData::clone() {
	return new SFVertex3fData();
}
}
