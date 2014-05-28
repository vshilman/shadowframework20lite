#ifndef shadow_lwc_H_
#define shadow_lwc_H_

#include "shadow/math/SFMatrix3f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFViewTransform.h"

class SFPortal implements SFViewTransform{

//	SFVertex3f F;
//	SFVertex3f P;
//	SFVertex3f Dv;
//	SFVertex3f Du;

//	SFVertex3f getP() {
//		return P;
	}

//	void setP(SFVertex3f p) {
//		P = p;
	}

//	SFVertex3f getDv() {
//		return Dv;
	}

//	void setDv(SFVertex3f dv) {
//		Dv = dv;
	}

//	SFVertex3f getDu() {
//		return Du;
	}

//	void setDu(SFVertex3f du) {
//		Du = du;
	}

//	SFVertex3f getF() {
//		return F;
	}

//	void setF(SFVertex3f f) {
//		F = f;
	}

//	float getDistance(){
//		return 100;
	}

//	float getDelta(){
//		return 1;
	}

//	float[] extractTransform() {		

//		float matrix[]=new float[16];

//		SFVertex3f FP=new SFVertex3f();

//		FP.subtract(P);

//		SFMatrix3f mat=new SFMatrix3f(
//				Du.getX(),Dv.getX(),FP.getX(),
//				Du.getY(),Dv.getY(),FP.getY(),
//				Du.getZ(),Dv.getZ(),FP.getZ()
//		);

//		//setDelta((float)Math.sqrt(getDir().dot3f(getDir())));
//		mat=SFMatrix3f.getInverse(mat);

//		matrix[0]=mat.getA();
//		matrix[1]=mat.getD();
//		matrix[2]=mat.getG()*getDelta();

//		matrix[4]=mat.getB();
//		matrix[5]=mat.getE();
//		matrix[6]=mat.getH()*getDelta();

//		matrix[8]=mat.getC();
//		matrix[9]=mat.getF();
//		matrix[10]=mat.getI()*getDelta();

//		matrix[12]=-(matrix[0]*getF().getX()+matrix[4]*getF().getY()+matrix[8]*getF().getZ());
//		matrix[13]=-(matrix[1]*getF().getX()+matrix[5]*getF().getY()+matrix[9]*getF().getZ());
//		matrix[14]=-(matrix[2]*getF().getX()+matrix[6]*getF().getY()+matrix[10]*getF().getZ());
//		matrix[15]=1;

//		float al=(getDistance()+getDelta())/(getDistance()-getDelta());
//		float bl=(-2*getDistance()*getDelta())/(getDistance()-getDelta());

//		matrix[0]=getDelta()*matrix[0];
//		matrix[1]=getDelta()*matrix[1];
//		matrix[3]=matrix[2];
//		matrix[2]=al*matrix[2];

//		matrix[4]=getDelta()*matrix[4];
//		matrix[5]=getDelta()*matrix[5];
//		matrix[7]=matrix[6];
//		matrix[6]=al*matrix[6];

//		matrix[8]=getDelta()*matrix[8];
//		matrix[9]=getDelta()*matrix[9];
//		matrix[11]=matrix[10];
//		matrix[10]=al*matrix[10];

//		matrix[12]=getDelta()*matrix[12];
//		matrix[13]=getDelta()*matrix[13];
//		matrix[15]=matrix[14];
//		matrix[14]=al*matrix[14]+bl;

//		return matrix;
	}

}
;
}
#endif
