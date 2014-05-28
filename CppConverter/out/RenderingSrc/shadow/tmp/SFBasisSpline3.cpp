#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/curves.SFBasisSpline.h"
#include "shadow/math/SFValuenf.h"

class SFBasisSpline3<T extends SFValuenf> extends SFBasisSpline{

//	SFBasisSpline3(boolean closed,int size) {
//		super(closed,size); 
	}

	
//	void compileCurve() {
//		//TODO
	}

	
//	void getDev2Dt(float v, SFValuenf write) {

//		int v_index=(int)(v*vertices.length);
//		if(v_index==vertices.length)
//			v_index--;

//		float t=(v*vertices.length)-v_index;

//		if(closed){

//			int indexl1=v_index>0?v_index-1:vertices.length-1;
//			int indexm1=v_index<vertices.length-1?v_index+1:0;

//			SFValuenf A=SFValuenf.middle(vertices[indexl1], vertices[v_index]);
//			SFValuenf B=vertices[v_index];
//			SFValuenf C=SFValuenf.middle(vertices[v_index], vertices[indexm1]);

//			write.set(A);
//			//DERIVED FROM write.mult(1.5f*(1-t)*(-1-t));
//			write.mult(3*t);
//			//DERIVED FROM write.addMult(1.5f-3*t, B);
//			write.addMult(-3, B);
//			//DERIVED FROM write.addMult(t*t*(1.5f-0.5f*t), C);
//			write.addMult(3-3*t, C);
		}
//			if(v_index==0){
//				write.mult(0);

			}
//				write.mult(0);

			}
//				SFValuenf A=SFValuenf.middle(vertices[v_index-1], vertices[v_index]);
//				SFValuenf B=vertices[v_index];
//				SFValuenf C=SFValuenf.middle(vertices[v_index], vertices[v_index+1]);
//				write.set(A);
//				//DERIVED FROM write.mult(1.5f*(1-t)*(-1-t));
//				write.mult(3*t);
//				//DERIVED FROM write.addMult(1.5f-3*t, B);
//				write.addMult(-3, B);
//				//DERIVED FROM write.addMult(t*t*(1.5f-0.5f*t), C);
//				write.addMult(3-3*t, C);
			}
		}
	}


	
//	void getDevDt(float v, SFValuenf write) {

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
////			write.set(A);
////			//DERIVED FROM write.mult((1-t)*(1-t)*(1+0.5f*t));
////			write.mult(1.5f*(1-t)*(-1-t));
////			//DERIVED FROM write.addMult(1.5f*t*(1-t), B);
////			write.addMult(1.5f-3*t, B);
////			//DERIVED FROM write.addMult(t*t*(1.5f-0.5f*t), C);
////			write.addMult(3*t-1.5f*t*t, C);
}
////			if(v_index==0){
////				SFValuenf A=vertices.get(0);
////				SFValuenf B=SFValuenf.middle(vertices.get(0),vertices.get(1));
////				write.set(B);
////				write.subtract(A);
////				
}
////				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1),vertices.get(v_index));
////				SFValuenf B=vertices.get(v_index);
////				
////				write.set(B);
////				write.subtract(A);
////				
}
////				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1), vertices.get(v_index));
////				SFValuenf B=vertices.get(v_index);
////				SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(v_index+1));
////				write.set(A);
////				//DERIVED FROM write.mult((1-t)*(1-t)*(1+0.5f*t));
////				write.mult(1.5f*(1-t)*(-1-t));
////				//DERIVED FROM write.addMult(1.5f*t*(1-t), B);
////				write.addMult(1.5f-3*t, B);
////				//DERIVED FROM write.addMult(t*t*(1.5f-0.5f*t), C);
////				write.addMult(3*t-1.5f*t*t, C);
//////				write.mult(-2*(1-t));
//////				write.addMult(2-4*t, B);
//////				write.addMult(2*t, C);
}
}
	}


	
//	void getVertex(float T, SFValuenf write) {

////		int v_index=(int)(T*vertices.size());
////		if(v_index==vertices.size())
////			v_index--;
////		
////		float t=(T*vertices.size())-v_index;
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
////			write.mult((1-t)*(1-t)*(1+0.5f*t));
////			write.addMult(1.5f*t*(1-t), B);
////			write.addMult(t*t*(1.5f-0.5f*t), C);
}
////			if(v_index==0){
////				SFValuenf A=vertices.get(0);
////				SFValuenf B=SFValuenf.middle(vertices.get(0),vertices.get(1));
////				write.set(A);
////				write.mult(1-t);
////				write.addMult(t, B);
////				
}
////				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1),vertices.get(v_index));
////				SFValuenf B=vertices.get(v_index);
////				
////				write.set(A);
////				write.mult(1-t);
////				write.addMult(t, B);
////			
}
////				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1), vertices.get(v_index));
////				SFValuenf B=vertices.get(v_index);
////				SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(v_index+1));
////				
////				write.set(A);
////				write.mult((1-t)*(1-t)*(1+0.5f*t));
////				write.addMult(1.5f*t*(1-t), B);
////				write.addMult(t*t*(1.5f-0.5f*t), C);
}
}
////		

	}


}
;
}
#endif
