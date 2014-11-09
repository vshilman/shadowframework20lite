/*
 * SFBasisSpline1.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFBasisSpline1.h"

namespace sf {


SFBasisSpline1::SFBasisSpline1(bool closed,int size):SFBasisSpline(closed,size){
	values=0;
	this->size=0;
	this->n=0;
	values_length=0;
}

SFBasisSpline1::~SFBasisSpline1(){
	delete[] values;//delete [] values???
};

void SFBasisSpline1::compileCurve() {

	n=this->vertices[0].getSize();

	if(!closed){
		size=vertices_length-1;

		int totalSize=size+1;

		if(values==0 || vertices_length!=totalSize*n){
			if(values!=0)
				delete values;
			values=new float[totalSize*n];
		}

		for (int j = 0; j < size+1; j++) {
			float* v=vertices[j].getV();
			for (int i = 0; i < n; i++) {
				values[j*n+i]=v[i];
			}
		}
	}else{
		//closed
		size=vertices_length;

		int totalSize=size+1;

		if(values==0 || values_length!=totalSize*n){
			//DELETE old values
			if(values!=0)
				delete values;
			values_length=totalSize*n;
			values=new float[values_length];
		}

		for (int j = 0; j < size; j++) {
			float* v=vertices[j].getV();
			for (int i = 0; i < n; i++) {
				values[j*n+i]=v[i];
			}
		}

		for (int i = 0; i < n; i++) {
			values[size*n+i]=values[i];
		}


	}
}

void SFBasisSpline1::getDev2Dt(float T, SFValuenf* write) {
	float* v=write->getV();
	//write on the shorter vector length
	int n=write->getSize()<this->n?write->getSize():this->n;
	for (int i = 0; i < n ; i++) {
		v[i]=0;
	}
}


void SFBasisSpline1::getDevDt(float T, SFValuenf* write) {
	float T_index=T*size;
	int t_index=(int)(T_index);
	if(t_index==size)
		t_index--;
	int k=t_index;

	float* v=write->getV();
	//write on the shorter vector length
	int n=write->getSize()<this->n?write->getSize():this->n;
	for (int i = 0; i < n ; i++) {
		v[i]=values[(k+1)*n+i]-values[(k)*n+i];
	}
}


void SFBasisSpline1::getVertex(float T, SFValuenf* write) {

	float T_index=T*size;
	int t_index=(int)(T_index);
	if(t_index==size)
		t_index--;
	float t=(T_index)-t_index;
	float tl=1-t;
	int k=t_index;

	float* v=write->getV();
	//write on the shorter vector length
	int n=write->getSize()<this->n?write->getSize():this->n;

	for (int i = 0; i < n ; i++) {
		v[i]=values[(k)*n+i]*tl+values[(k+1)*n+i]*t;
	}

}


} /* namespace sf */
