#ifndef shadow_geometry_curves_H_
#define shadow_geometry_curves_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/system/SFResource.h"

class SFNCurve extends SFGenericCurve implements SFCurve{

//	SFCurve P;
//	SFVertex3f firstNormal;
//	SFVertex3f secondNormal;

//	SFResource resource=new SFResource(0);

//	SFNCurve() {
//		super();
	}

	
//	void compileCurve() {
//		P.compileCurve();
	}

	
//	SFValuenf getControlPoint(int index) {
//		return P.getControlPoint(index);
	}

	
//	int getControlPointSize() {
//		return P.getControlPointSize();
	}

	
//	void setControlPoint(int index, SFValuenf vertex) {
//		P.setControlPoint(index, vertex);
	}

//	SFResource getResource() {
//		return resource;
	}

	
//	void getVertex(float t, SFValuenf read) {

//		float T=(t-P.getTMin())/(P.getTMax()-P.getTMin());

//		read.set(firstNormal);
//		read.mult(1-T);
//		read.addMult(T, secondNormal);

//		SFVertex3f derivative=new SFVertex3f();
//		P.getDevDt(t, derivative);

//		float K=-read.dot(derivative)/derivative.dot(derivative);

//		read.addMult(K, derivative);
	}

	
//	SFValuenf generateValue() {
//		return new SFVertex3f();
	}

	
//	float getTMax() {
//		return P.getTMax();
	}

	
//	float getTMin() {
//		return P.getTMin();
	}

//	SFCurve getP() {
//		return P;
	}

//	void setP(SFCurve p) {
//		P = p;
	}

//	SFVertex3f getFirstNormal() {
//		return firstNormal;
	}

//	void setFirstNormal(SFVertex3f firstNormal) {
		this->firstNormal = firstNormal;
	}

//	SFVertex3f getSecondNormal() {
//		return secondNormal;
	}

//	void setSecondNormal(SFVertex3f secondNormal) {
		this->secondNormal = secondNormal;
	}

}
;
}
#endif
