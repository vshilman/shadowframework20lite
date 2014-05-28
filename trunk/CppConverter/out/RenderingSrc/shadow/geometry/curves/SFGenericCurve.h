#ifndef shadow_geometry_curves_H_
#define shadow_geometry_curves_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFValuenf.h"

//abstract class SFGenericCurve implements SFCurve{

	AULT_APPROXIMATION = 0.001f;

	AULT_APPROXIMATION;

	
//	void getDevDt(float t, SFValuenf read) {
//		SFValuenf value1=new SFValuenf(read.getSize());
//		SFValuenf value2=new SFValuenf(read.getSize());
//		getVertex((float)(t+aproximation), value1);
//		getVertex((float)(t-aproximation), value2);
//		read.set(value1);
//		read.subtract(value2);
//		read.mult((float)(1.0/(2*aproximation)));
	}

	
//	void getDev2Dt(float t, SFValuenf read) {
//		SFValuenf value1=new SFValuenf(read.getSize());
//		SFValuenf value2=new SFValuenf(read.getSize());
//		SFValuenf value3=new SFValuenf(read.getSize());
//		getVertex((float)(t+aproximation), value1);
//		getVertex((float)(t-aproximation), value2);
//		getVertex((float)(t), value3);
//		read.set(value1);
//		read.addMult(-2, value2);
//		read.add(value3);
//		read.mult((float)(1.0/(aproximation*aproximation)));
	}
}
;
}
#endif
