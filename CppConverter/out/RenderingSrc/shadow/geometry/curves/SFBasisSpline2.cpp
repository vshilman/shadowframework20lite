#ifndef shadow_geometry_curves_H_
#define shadow_geometry_curves_H_

#include "shadow/math/SFValuenf.h"

class SFBasisSpline2 extends SFBasisSpline{

//	float[] values;
//	int size;
//	int n;

//	SFBasisSpline2(boolean closed,int size) {
//		super(closed,size); 
	}

	
//	void compileCurve() {

//		n=this->vertices[0].getSize();

//		if(!closed){

//			size=vertices.length-2;

//			int totalSize=size*2+1;

//			if(values==null || values.length!=totalSize*n){
//				//DELETE old values
//				values=new float[totalSize*n];
			}

//			//Setting the first A and the first G..
//			float[] v=vertices[0].getV();
//			float[] v2=vertices[1].getV();
//			//float[] v3=vertices[2].getV();
//			for (int i = 0; i < n; i++) {
//				values[i]=v[i];
//				values[n+i]=v2[i];
			}

//			//A/B and G values
//			for (int k = 1; k < size; k++) {
//				v=vertices[k].getV();
//				v2=vertices[k+1].getV();
//				for (int i = 0; i < n; i++) {
//					values[(k*2*n)+i]=(v[i]+v2[i])*0.5f;
//					values[((k*2+1)*n)+i]=v2[i];
				}
			}

//			//last vertex
//			v=vertices[vertices.length-1].getV();
//			for (int i = 0; i < n; i++) {
//				values[(totalSize-1)*n+i]=v[i];
			}

		}
//			//closed
//			size=vertices.length;

//			int totalSize=size*2+1;

//			if(values==null || values.length!=totalSize*n){
//				//DELETE old values
//				values=new float[totalSize*n];
			}

//			//A/B and G values (All)
//			for (int k = 0; k < size; k++) {
//				int index1=k;
//				int index2=k+1>=size?k+1-size:k+1;
//				float[] v=vertices[index1].getV();
//				float[] v2=vertices[index2].getV();
//				for (int i = 0; i < n; i++) {

//					values[(k*2*n)+i]=(v[i]+v2[i])*0.5f;
//					values[((k*2+1)*n)+i]=v2[i];
				}
			}

//			for (int i = 0; i < n; i++) {
//				values[(size*2)*n+i]=values[i];
			}
		}
	}


////	void getDev2Dt(float T, SFValuenf write) {
////		ArrayList<SFValuenf> vertices=this->vertices;
////		
////		int v_index=(int)(T*vertices.size());
////		if(v_index==vertices.size())
////			v_index--;
////		
////		if(closed){
////			
////			int indexl1=v_index>0?v_index-1:vertices.size()-1;
////			int indexm1=v_index<vertices.size()-1?v_index+1:0;
////			
////			SFValuenf A=SFValuenf.middle(vertices.get(indexl1), vertices.get(v_index));
////			SFValuenf B=vertices.get(v_index);
////			SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(indexm1));
////			
////			write.set(A);
////			write.mult(2);
////			write.addMult(-4, B);
////			write.addMult(2, C);
}
////			if(v_index==0){
////				write.mult(0);
}
////				write.mult(0);
}
////				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1), vertices.get(v_index));
////				SFValuenf B=vertices.get(v_index);
////				SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(v_index+1));
////				write.set(A);
////				write.mult(2);
////				write.addMult(-4, B);
////				write.addMult(2, C);
}
}
}
////	
////	

////	void getDevDt(float v, SFValuenf write) {
////		ArrayList<SFValuenf> vertices=this->vertices;
////		
////		int v_index=(int)(v*vertices.size());
////		if(v_index==vertices.size())
////			v_index--;
////		
////		float t=(v*vertices.size())-v_index;
////		
////		if(closed){
////			
////			int indexl1=v_index>0?v_index-1:vertices.size()-1;
////			int indexm1=v_index<vertices.size()-1?v_index+1:0;
////			
////			SFValuenf A=SFValuenf.middle(vertices.get(indexl1), vertices.get(v_index));
////			SFValuenf B=vertices.get(v_index);
////			SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(indexm1));
////			
////			write.set(A);
////			write.mult(-2*(1-t));
////			write.addMult(2-4*t, B);
////			write.addMult(2*t, C);
}
////			if(v_index==0){
////				SFValuenf A=vertices.get(0);
////				SFValuenf B=SFValuenf.middle(vertices.get(0),vertices.get(1));
////				write.set(B);
////				write.subtract(A);
}
////				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1),vertices.get(v_index));
////				SFValuenf B=vertices.get(v_index);
////				write.set(B);
////				write.subtract(A);
}
////				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1), vertices.get(v_index));
////				SFValuenf B=vertices.get(v_index);
////				SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(v_index+1));
////				write.set(A);
////				write.mult(-2*(1-t));
////				write.addMult(2-4*t, B);
////				write.addMult(2*t, C);
}
}
}
////	
////	

////	void getVertex(float T, SFValuenf write) {
////		
////		int v_index=(int)(T*vertices.length);
////		if(v_index==vertices.length)
////			v_index--;
////		
////		float t=(T*vertices.length)-v_index;
////		
////		if(closed){
////			
////			int indexl1=v_index>0?v_index-1:vertices.length-1;
////			int indexm1=v_index<vertices.length-1?v_index+1:0;
////			
////			SFValuenf A=SFValuenf.middle(vertices[indexl1], vertices.get(v_index));
////			SFValuenf B=vertices.get(v_index);
////			SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(indexm1));
////			
////			write.set(A);
////			write.mult((1-t)*(1-t));
////			write.addMult(2*t*(1-t), B);
////			write.addMult(t*t, C);
}
////			if(v_index==0){
////				SFValuenf A=vertices.get(0);
////				SFValuenf B=SFValuenf.middle(vertices.get(0),vertices.get(1));
////				write.set(A);
////				write.mult(1-t);
////				write.addMult(t, B);
}
////				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1),vertices.get(v_index));
////				SFValuenf B=vertices.get(v_index);
////				write.set(A);
////				write.mult(1-t);
////				write.addMult(t, B);
}
////				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1), vertices.get(v_index));
////				SFValuenf B=vertices.get(v_index);
////				SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(v_index+1));
////				
////				write.set(A);
////				write.mult((1-t)*(1-t));
////				write.addMult(2*t*(1-t), B);
////				write.addMult(t*t, C);
}
}
////		
}
////	


	
//	void getDev2Dt(float T, SFValuenf write) {
//		float T_index=T*size;
//		int t_index=(int)(T_index);
//		if(t_index==size)
//			t_index--;
//		//float t=(T_index)-t_index;
//		//float tl=1-t;
//		int k=t_index*2;

//		float[] v=write.getV();
//		//write on the shorter vector length
//		int n=v.length<this->n?v.length:this->n;
//		for (int i = 0; i < n ; i++) {
//			v[i]=2*((values[(k)*n+i]+values[(k+2)*n+i]-2*values[(k+1)*n+i]));
		}
	}


	
//	void getDevDt(float T, SFValuenf write) {
//		float T_index=T*size;
//		int t_index=(int)(T_index);
//		if(t_index==size)
//			t_index--;
//		float t=(T_index)-t_index;
//		float tl=1-t;
//		int k=t_index*2;

//		float[] v=write.getV();
//		//write on the shorter vector length
//		int n=v.length<this->n?v.length:this->n;
//		for (int i = 0; i < n ; i++) {
//			v[i]=2*((values[(k+1)*n+i]-values[(k)*n+i])*tl+
//					(values[(k+2)*n+i]-values[(k+1)*n+i])*t*3);
		}
	}


	
//	void getVertex(float T, SFValuenf write) {

//		float T_index=T*size;
//		int t_index=(int)(T_index);
//		if(t_index==size)
//			t_index--;
//		float t=(T_index)-t_index;
//		float tl=1-t;
//		int k=t_index*2;

//		float[] v=write.getV();
//		//write on the shorter vector length
//		int n=v.length<this->n?v.length:this->n;

//		for (int i = 0; i < n ; i++) {
//			v[i]=values[k*n+i]*tl*tl+
//					values[(k+1)*n+i]*tl*t*2+
//					values[(k+2)*n+i]*t*t;
		}

	}

}
;
}
#endif
