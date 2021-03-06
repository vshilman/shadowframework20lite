/*
 * SFBasisSpline3.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFBasisSpline3.h"

namespace sf {

SFBasisSpline3::SFBasisSpline3(bool closed,int size):SFBasisSpline(closed,size) {
	n=0;
	this->size=0;
	values_length=0;
	values=0;
}

SFBasisSpline3::~SFBasisSpline3(){
	delete[] values;
}

void SFBasisSpline3::compileCurve() {

	n=this->vertices[0].getSize();

	if(!closed){

		size=vertices_length-2;

		int totalSize=size*4+1;

		if(values==0 || values_length!=totalSize*n){
			//DELETE old values
			values=new float[totalSize*n];
		}

		//Setting the first A and the first G..
		float* v=vertices[0].getV();
		float* v2=vertices[1].getV();
		float* v3=vertices[2].getV();
		for (int i = 0; i < n; i++) {
			values[i]=v[i];
			values[((2)*n)+i]=(v[i]+6*v2[i]+v3[i])*0.125f;
		}

		//A/B and G values
		for (int k = 1; k < size; k++) {
			v=vertices[k].getV();
			v2=vertices[k+1].getV();
			v3=vertices[k+2].getV();
			for (int i = 0; i < n; i++) {
				values[(k*4*n)+i]=(v[i]+v2[i])*0.5f;
				values[((k*4+2)*n)+i]=(v[i]+6*v2[i]+v3[i])*0.125f;
			}
		}

		//Setting the last B
		v=vertices[vertices_length-1].getV();
		for (int i = 0; i < n; i++) {
			values[(size*4)*n+i]=v[i];
		}

		//A+, B-
		for (int k = 0; k < size; k++) {
			for (int i = 0; i < n; i++) {
				if(k!=0)
					values[((k*4+1)*n)+i]=values[((k*4)*n)+i]+0.25f*(values[((k*4+2)*n)+i]-values[((k*4-2)*n)+i]);
				if(k!=size-1)
					values[((k*4+3)*n)+i]=values[((k*4+4)*n)+i]+0.25f*(values[((k*4+2)*n)+i]-values[((k*4+6)*n)+i]);
			}
		}

		//First A, last B
		v=vertices[1].getV();
		v2=vertices[vertices_length-2].getV();
		for (int i = 0; i < n; i++) {
			values[n+i]=(v[i]+values[i])*0.5f;
			values[(size*4-1)*n+i]=(v2[i]+values[size*4*n+i])*0.5f;
		}


	}else{
		//closed
		size=vertices_length;

		int totalSize=size*4+1;

		if(values==0 || values_length!=totalSize*n){
			if(values!=0)
				delete values;
			values_length=totalSize*n;
			values=new float[values_length];
		}


		//A/B and G values (All)
		for (int k = 0; k < size; k++) {
			int index1=k;
			int index2=k+1>=size?k+1-size:k+1;
			int index3=k+2>=size?k+2-size:k+2;
			float* v=vertices[index1].getV();
			float* v2=vertices[index2].getV();
			float* v3=vertices[index3].getV();
			for (int i = 0; i < n; i++) {
				values[(k*4*n)+i]=(v[i]+v2[i])*0.5f;
				values[((k*4+2)*n)+i]=(v[i]+6*v2[i]+v3[i])*0.125f;
			}
		}

		for (int k = 0; k < size; k++) {
			int indexl=(k-1<0?k-1+size:k-1)*4;
			int indexp=(k+1>=size?k+1-size:k+1)*4;
			int index=k*4;
			for (int i = 0; i < n; i++) {
				values[((index+1)*n)+i]=values[((index)*n)+i]+0.25f*(values[((index+2)*n)+i]-values[((indexl+2)*n)+i]);
				values[((index+3)*n)+i]=values[((indexp)*n)+i]+0.25f*(values[((index+2)*n)+i]-values[((indexp+2)*n)+i]);
			}
		}

		for (int i = 0; i < n; i++) {
			values[(size*4)*n+i]=values[i];
		}
	}
}

void SFBasisSpline3::getDev2Dt(float T, SFValuenf* write) {
	float T_index=T*size;
	int t_index=(int)(T_index);
	if(t_index==size)
		t_index--;
	float t=(T_index)-t_index;
	float tl=1-t;
	int k=t_index*4;

	float* v=write->getV();
	//write on the shorter vector length
	int n=write->getSize()<this->n?write->getSize():this->n;
	for (int i = 0; i < n ; i++) {
		v[i]=12*((values[(k)*n+i]+values[(k+2)*n+i]-2*values[(k+1)*n+i])*tl*tl+
				(values[(k+1)*n+i]+values[(k+3)*n+i]-2*values[(k+2)*n+i])*tl*t*2+
				(values[(k+2)*n+i]+values[(k+4)*n+i]-2*values[(k+3)*n+i])*t*t);
	}
}


void SFBasisSpline3::getDevDt(float T, SFValuenf* write) {
	float T_index=T*size;
	int t_index=(int)(T_index);
	if(t_index==size)
		t_index--;
	float t=(T_index)-t_index;
	float tl=1-t;
	int k=t_index*4;

	float* v=write->getV();
	//write on the shorter vector length
	int n=write->getSize()<this->n?write->getSize():this->n;
	for (int i = 0; i < n ; i++) {
		v[i]=4*((values[(k+1)*n+i]-values[(k)*n+i])*tl*tl*tl+
				(values[(k+2)*n+i]-values[(k+1)*n+i])*tl*tl*t*3+
				(values[(k+3)*n+i]-values[(k+2)*n+i])*tl*t*t*3+
				(values[(k+4)*n+i]-values[(k+3)*n+i])*t*t*t);
	}
}


void SFBasisSpline3::getVertex(float T, SFValuenf* write) {

	float T_index=T*size;
	int t_index=(int)(T_index);
	if(t_index==size)
		t_index--;
	float t=(T_index)-t_index;
	float tl=1-t;
	int k=t_index*4;

	float* v=write->getV();
			//write on the shorter vector length
			int n=write->getSize()<this->n?write->getSize():this->n;

	for (int i = 0; i < n ; i++) {
		v[i]=values[k*n+i]*tl*tl*tl*tl+
				values[(k+1)*n+i]*tl*tl*tl*t*4+
				values[(k+2)*n+i]*tl*tl*t*t*6+
				values[(k+3)*n+i]*tl*t*t*t*4+
				values[(k+4)*n+i]*t*t*t*t;
	}

}


} /* namespace sf */
