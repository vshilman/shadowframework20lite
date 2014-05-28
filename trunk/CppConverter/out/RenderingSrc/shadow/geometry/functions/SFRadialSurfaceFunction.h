#ifndef shadow_geometry_functions_H_
#define shadow_geometry_functions_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFVertex3f.h"

///**
// * @author Alessandro
// */
class SFRadialSurfaceFunction  extends SFGenericSurfaceFunction {

//	SFCurve borderCurve;
//	SFCurve rayCurve;
//	SFVertex3f r0=new SFVertex3f();
//	SFVertex3f r1=new SFVertex3f();
//	SFVertex3f pv=new SFVertex3f();
	A,B;

//	SFRadialSurfaceFunction() {

	}

//	SFRadialSurfaceFunction(SFCurve borderCurve,
//			SFCurve rayCurve) {
//		super();
//		setBorderCurve(borderCurve);
//		setRayCurve(rayCurve);
	}

//	void setBorderCurve(SFCurve borderCurve) {
		this->borderCurve = borderCurve;
	}

//	void setRayCurve(SFCurve rayCurve) {
		this->rayCurve = rayCurve;
//		rayCurve.getVertex(0, r0);
//		rayCurve.getVertex(1, r1);
//		r1.subtract(r0);
	}


////	SFVertex3f getDv(float u, float v) {
////		SFVertex3f p=super.getDv(u, v);
////		//p.mult(-1);
////		return p;
}
////	

////	SFVertex3f getDu(float u, float v) {
////		SFVertex3f p=new SFVertex3f();
////		borderCurve.getDevDt(u, p);
////		p.mult(-1);
////		//float dot=r1.dot3f(r1);
////		//A=(r1.getX()*p.getX()-r1.getY()*p.getY())/dot;
////		//B=(+r1.getY()*p.getX()+r1.getX()*p.getY())/dot;
////		return p;
}


	AB(float u){
//		SFVertex3f p=new SFVertex3f();
//		borderCurve.getVertex(/*u*borderCurve.getTMax()+borderCurve.getTMin()*(1-u)*/u, p);
//		p.subtract(r0);
//		float dot=r1.dot3f(r1);
//		A=(r1.getX()*p.getX()-r1.getY()*p.getY())/dot;
//		B=(+r1.getY()*p.getX()+r1.getX()*p.getY())/dot;
	}

	
//	float getX(float u, float v) {
//		evaluateAB(u);
//		rayCurve.getVertex(/*u*rayCurve.getTMax()+rayCurve.getTMin()*(1-u)*/v, pv);
//		pv.subtract(r0);
//		return A*pv.getX()-B*pv.getY()+r0.getX();
	}

	
//	float getY(float u, float v) {
//		return B*pv.getX()+A*pv.getY()+r0.getY();
	}

	
//	float getZ(float u, float v) {
//		return pv.getZ();
	}
}
;
}
#endif
