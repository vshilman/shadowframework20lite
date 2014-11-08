/*
 * SFBasisSpline2.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFBasisSpline2.h"

namespace sf {


	SFBasisSpline2::SFBasisSpline2(bool closed,int size):SFBasisSpline(closed,size){
		this->values=0;
		this->values_length=0;
		this->size=0;
		this->n=0;
	}

	SFBasisSpline2::~SFBasisSpline2(){
		delete[] values;
	}


	void SFBasisSpline2::compileCurve() {

		n=this->vertices[0].getSize();

		if(!closed){

			size=vertices_length-2;

			int totalSize=size*2+1;

			if(values==0 || values_length!=totalSize*n){
				//DELETE old values
				if(values!=0)
					delete values;
				values_length=totalSize*n;
				values=new float[values_length];
			}

			//Setting the first A and the first G..
			float* v=vertices[0].getV();
			float* v2=vertices[1].getV();
			//float[] v3=vertices[2].getV();
			for (int i = 0; i < n; i++) {
				values[i]=v[i];
				values[n+i]=v2[i];
			}

			//A/B and G values
			for (int k = 1; k < size; k++) {
				v=vertices[k].getV();
				v2=vertices[k+1].getV();
				for (int i = 0; i < n; i++) {
					values[(k*2*n)+i]=(v[i]+v2[i])*0.5f;
					values[((k*2+1)*n)+i]=v2[i];
				}
			}

			//last vertex
			v=vertices[vertices_length-1].getV();
			for (int i = 0; i < n; i++) {
				values[(totalSize-1)*n+i]=v[i];
			}

		}else{
			//closed
			size=vertices_length;

			int totalSize=size*2+1;

			if(values==0 || values_length!=totalSize*n){
				//DELETE old values
				values=new float[totalSize*n];
			}

			//A/B and G values (All)
			for (int k = 0; k < size; k++) {
				int index1=k;
				int index2=k+1>=size?k+1-size:k+1;
				float* v=vertices[index1].getV();
				float* v2=vertices[index2].getV();
				for (int i = 0; i < n; i++) {

					values[(k*2*n)+i]=(v[i]+v2[i])*0.5f;
					values[((k*2+1)*n)+i]=v2[i];
				}
			}

			for (int i = 0; i < n; i++) {
				values[(size*2)*n+i]=values[i];
			}
		}
	}



	void SFBasisSpline2::getDev2Dt(float T, SFValuenf* write) {
		float T_index=T*size;
		int t_index=(int)(T_index);
		if(t_index==size)
			t_index--;
		//float t=(T_index)-t_index;
		//float tl=1-t;
		int k=t_index*2;

		float* v=write->getV();
		//write on the shorter vector length
		int n=write->getSize()<this->n?write->getSize():this->n;
		for (int i = 0; i < n ; i++) {
			v[i]=2*((values[(k)*n+i]+values[(k+2)*n+i]-2*values[(k+1)*n+i]));
		}
	}


	void SFBasisSpline2::getDevDt(float T, SFValuenf* write) {
		float T_index=T*size;
		int t_index=(int)(T_index);
		if(t_index==size)
			t_index--;
		float t=(T_index)-t_index;
		float tl=1-t;
		int k=t_index*2;

		float* v=write->getV();
		//write on the shorter vector length
		int n=write->getSize()<this->n?write->getSize():this->n;
		for (int i = 0; i < n ; i++) {
			v[i]=2*((values[(k+1)*n+i]-values[(k)*n+i])*tl+
					(values[(k+2)*n+i]-values[(k+1)*n+i])*t*3);
		}
	}


	void SFBasisSpline2::getVertex(float T, SFValuenf* write) {

		float T_index=T*size;
		int t_index=(int)(T_index);
		if(t_index==size)
			t_index--;
		float t=(T_index)-t_index;
		float tl=1-t;
		int k=t_index*2;

		float* v=write->getV();
		//write on the shorter vector length
		int n=write->getSize()<this->n?write->getSize():this->n;

		for (int i = 0; i < n ; i++) {
			v[i]=values[k*n+i]*tl*tl+
					values[(k+1)*n+i]*tl*t*2+
					values[(k+2)*n+i]*t*t;
		}

	}

} /* namespace sf */
