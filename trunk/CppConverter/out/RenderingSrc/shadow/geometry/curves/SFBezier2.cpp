#ifndef shadow_geometry_curves_H_
#define shadow_geometry_curves_H_

#include "shadow/math/SFValuenf.h"

namespace sf{
class SFBezier2 extends SFStandardAbstractCurve{

//	SFBezier2(int n) {
//		super(3);
//		for (int i = 0; i < vertices.length; i++) {
//			vertices[i]=new SFValuenf(n);
		}
	}

//	SFBezier2(SFValuenf a, SFValuenf b, SFValuenf c) {
//		super(3);
//		vertices[0] = a;
//		vertices[1] = b;
//		vertices[2] = c;
	}


	
//	void getDev2Dt(float t, SFValuenf read) {
//		read.set(vertices[0]);
//		read.mult(2);
//		read.addMult(-4,vertices[1]);
//		read.addMult(2,vertices[2]);
	}

	
//	void getDevDt(float t, SFValuenf read) {
//		read.set(vertices[0]);
//		read.mult(-2*(1-t));
//		read.addMult(2-4*t,vertices[1]);
//		read.addMult(2*t,vertices[2]);
	}

//	void getVertex(float t, SFValuenf read) {
//		float tm = 1-t;
//		read.set(vertices[0]);
//		read.mult(tm*tm);
//		read.addMult(2*t*tm,vertices[1]);
//		read.addMult(t*t,vertices[2]);
	}

	
//	String toString() {
//		return "B2 : "+vertices[0]+" "+vertices[1]+" "+vertices[2];
	}

	
//	protected SFBezier2 clone() {
//		return new SFBezier2(vertices[0], vertices[1], vertices[2]);
	}
}
;
}
#endif
