#include SFParameter.h

//#ifndef __SpotLightTownTest__SFParameter__;


//#define __SpotLightTownTest__SFParameter__;


//#include <string>;


//#include "SFParameteri.h";


//using namespace std;




SFParameter::SFParameter( ):SFParameteri() {
}

SFParameter::SFParameter( string name):SFParameteri() {
}

SFParameter::SFParameter( string name,short type):SFParameteri() {
}

string SFParameter::getName( ) {
	return this->name;
}

short SFParameter::getType( ) {
	return this->type;
}

void SFParameter::setType (short type ) {
	this->type=type;
}

