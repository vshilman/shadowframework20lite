#ifndef shadow_geometry_curves_H_
#define shadow_geometry_curves_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFTransform3f.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/SFResource.h"

class SFTransformedCurve implements SFCurve{

//	SFCurve curve;
//	SFTransform3f trasnform;

//	SFResource resource=new SFResource(0);

//	SFTransformedCurve() {
//		super();
	}

	
//	void compileCurve() {
//		curve.compileCurve();
	}

	
//	SFValuenf getControlPoint(int index) {
//		return curve.getControlPoint(index);
	}

	
//	int getControlPointSize() {
//		return curve.getControlPointSize();
	}

	
//	void setControlPoint(int index, SFValuenf vertex) {
//		curve.setControlPoint(index, vertex);
	}

//	SFTransformedCurve(SFCurve curve, SFTransform3f trasnform) {
//		super();
		this->curve = curve;
		this->trasnform = trasnform;
	}

//	SFResource getResource() {
//		return resource;
	}

//	void setCurve(SFCurve curve) {
		this->curve = curve;
	}

//	void setTransform(SFTransform3f trasnform) {
		this->trasnform = trasnform;
	}

	
//	SFValuenf generateValue() {
//		return curve.generateValue();
	}


	
//	void getDev2Dt(float ts, SFValuenf read) {
//		curve.getDev2Dt(ts,read);
//		trasnform.transformDir(read);
	}


	
//	void getDevDt(float t, SFValuenf read) {
//		curve.getDevDt(t, read);
//		trasnform.transformDir(read);
	}

	
//	float getTMax() {
//		return curve.getTMax();
	}

	
//	float getTMin() {
//		return curve.getTMin();
	}

	
//	void getVertex(float t, SFValuenf read) {
//		curve.getVertex(t, read);
//		trasnform.transform(read);
	}


}
;
}
#endif
