#include SFParameter.h
#include <iostream>

//package shadow.pipeline.parameters;




//protected short type=GLOBAL_GENERIC;


SFParameter::SFParameter( ):SFParameteri() {
	this->ancheMe=false;
}

SFParameter::SFParameter( string name,short type):SFParameteri() {
	this->name = name;
	this->type = type;
	this->ancheMe=false;
}

SFParameter::SFParameter( string name):SFParameteri() {
	this->name = name;
	this->ancheMe=false;
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


