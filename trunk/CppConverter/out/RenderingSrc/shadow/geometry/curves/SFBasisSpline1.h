#ifndef shadow_geometry_curves_H_
#define shadow_geometry_curves_H_

#include "java/util/Arrays.h"

#include "shadow/math/SFValuenf.h"

class SFBasisSpline1 extends SFBasisSpline {

//	float[] values;
//	int size;
//	int n;

//	SFBasisSpline1(boolean closed,int size) {
//		super(closed,size);
	}

	
//	void compileCurve() {

//		n=this->vertices[0].getSize();

//		if(!closed){
//			size=vertices.length-1;

//			int totalSize=size+1;

//			if(values==null || values.length!=totalSize*n){
//				//DELETE old values
//				values=new float[totalSize*n];
			}

//			for (int j = 0; j < size+1; j++) {
//				float[] v=vertices[j].getV();
//				for (int i = 0; i < n; i++) {
//					values[j*n+i]=v[i];
				}
			}
		}
//			//closed
//			size=vertices.length;

//			int totalSize=size+1;

//			if(values==null || values.length!=totalSize*n){
//				//DELETE old values
//				values=new float[totalSize*n];
			}

//			for (int j = 0; j < size; j++) {
//				float[] v=vertices[j].getV();
//				for (int i = 0; i < n; i++) {
//					values[j*n+i]=v[i];
				}
			}

//			for (int i = 0; i < n; i++) {
//				values[size*n+i]=values[i];
			}


		}
	}

	
//	void getDev2Dt(float T, SFValuenf write) {
//		float[] v=write.getV();
//		//write on the shorter vector length
//		int n=v.length<this->n?v.length:this->n;
//		for (int i = 0; i < n ; i++) {
//			v[i]=0;
		}
	}


	
//	void getDevDt(float T, SFValuenf write) {
//		float T_index=T*size;
//		int t_index=(int)(T_index);
//		if(t_index==size)
//			t_index--;
//		int k=t_index;

//		float[] v=write.getV();
//		//write on the shorter vector length
//		int n=v.length<this->n?v.length:this->n;
//		for (int i = 0; i < n ; i++) {
//			v[i]=values[(k+1)*n+i]-values[(k)*n+i];
		}
	}


	
//	void getVertex(float T, SFValuenf write) {

//		float T_index=T*size;
//		int t_index=(int)(T_index);
//		if(t_index==size)
//			t_index--;
//		float t=(T_index)-t_index;
//		float tl=1-t;
//		int k=t_index;

//		float[] v=write.getV();
//		//write on the shorter vector length
//		int n=v.length<this->n?v.length:this->n;

//		for (int i = 0; i < n ; i++) {
//			v[i]=values[(k)*n+i]*tl+values[(k+1)*n+i]*t;
		}

	}


}
;
}
#endif
