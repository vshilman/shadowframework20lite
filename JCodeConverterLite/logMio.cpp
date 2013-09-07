#include SFParameter.h

//package shadow.pipeline.parameters;






//protected short type=GLOBAL_GENERIC;


SFParameter::SFParameter( ):SFParameteri() {
}

SFParameter::SFParameter( string name,short type):SFParameteri() {
	this->name = name;
	this->type = type;
}

SFParameter::SFParameter( string name):SFParameteri() {
	this->name = name;
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

//@Override	public String toString() {
//return name+"("+type+")";
//}


