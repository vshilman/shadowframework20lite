

#include "SFVectorData.h"

#include <iostream>
using namespace std;


namespace sf{


SFVectorData::SFVectorData(float* floatValues,int floatValuesSize){
	this->floatValues=0;
    this->floatValues=floatValues;
	this->n=floatValuesSize;
    cout << "BUILT VALUES 1 "<< floatValues ;
}

SFVectorData::SFVectorData(int floatValuesSize) {
    	this->floatValues=0;
	this->floatValues = new float[floatValuesSize];
	this->n=floatValuesSize;
    cout << "BUILT VALUES 0 "<< floatValues ;
    
}

SFVectorData::~SFVectorData(){
	delete floatValues;
}

float* SFVectorData::getFloatValues() {
	return floatValues;
}

void SFVectorData::readFromStream(SFInputStream* stream) {
	float* values=stream->readFloats(n);
	cout << "DATA READ " <<n <<endl;
    cout << values[0] << " " << values[1] << " " << values[2];
    cout << floatValues << endl;
    this->floatValues[0]=1.1f;
	for (int i = 0; i < n; i++) {
		floatValues[i]=values[i];
		cout << i <<" "<<values[i]  <<endl;
	}

	delete values;
}

SFVectorData* SFVectorData::clone(){
	return new SFVectorData(n);
}
}
