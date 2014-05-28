#ifndef shadow_geometry_curves_H_
#define shadow_geometry_curves_H_

#include "shadow/math/SFValuenf.h"

namespace sf{
class SFLine extends SFStandardAbstractCurve{

//	SFLine(SFValuenf a, SFValuenf b) {
//		super(2);
//		vertices[0] = a;
//		vertices[1] = b;
	}

//	SFLine(int size) {
//		super(2);
//		vertices[0] = new SFValuenf(size);
//		vertices[1] = new SFValuenf(size);
	}

	
//	void getDev2Dt(float t, SFValuenf read) {
//		float[] data = read.getV();
//		for (int i = 0; i < data.length; i++) {
//			data[i]=0;
		}
	}

	
//	void getDevDt(float t, SFValuenf read) {
//		read.set(vertices[1]);
//		read.addMult(-1,vertices[0]);
	}

	
//	void getVertex(float t, SFValuenf read) {
//		//System.err.println("line "+vertices[0].getV()+" "+vertices[1].getV());
//		read.set(vertices[0]);
//		read.mult(1-t);
//		read.addMult(t,vertices[1]);
	}

	
//	String toString() {
//		return "LINE : "+vertices[0]+" "+vertices[1];
	}

	
//	protected SFLine clone() {
//		return new SFLine(vertices[0], vertices[1]);
	}
}
;
}
#endif
