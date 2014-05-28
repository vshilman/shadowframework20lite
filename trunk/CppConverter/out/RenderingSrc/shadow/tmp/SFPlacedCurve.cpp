#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/SFResource.h"

class SFPlacedCurve<T extends SFValuenf> implements SFCurve{

//	float tMin;
//	float tMax;
//	SFCurve curve;

//	SFResource resource=new SFResource(0);

//	SFPlacedCurve(float tMin, float tMax, SFCurve curve) {
//		super();
		this->tMin = tMin;
		this->tMax = tMax;
		this->curve = curve;
	}

	
//	void compileCurve() {
//		// TODO Auto-generated method stub		
	}

	
//	int getControlPointSize() {
//		return curve.getControlPointSize();
	}

	
//	void setControlPoint(int index, SFValuenf vertex) {
//		curve.setControlPoint(index, vertex);
	}

	
//	SFValuenf getControlPoint(int index) {
//		return curve.getControlPoint(index);
	}

//	SFResource getResource() {
//		return resource;
	}

//	protected float gettMin() {
//		return tMin;
	}

	
//	SFValuenf generateValue() {
//		return curve.generateValue();
	}

//	protected void settMin(float tMin) {
		this->tMin = tMin;
	}

//	protected float gettMax() {
//		return tMax;
	}

//	protected void settMax(float tMax) {
		this->tMax = tMax;
	}

//	protected SFCurve getCurve() {
//		return curve;
	}

//	protected void setCurve(SFCurve curve) {
		this->curve = curve;
	}

	
//	void getDev2Dt(float t, SFValuenf read) {
//		curve.getDev2Dt(t*(tMax-tMin)+tMin, read);
	}

	
//	void getDevDt(float t, SFValuenf read) {
//		curve.getDevDt(t*(tMax-tMin)+tMin, read);
	}

	
//	void getVertex(float t, SFValuenf read) {
//		curve.getVertex(t*(tMax-tMin)+tMin, read);
	}

//	float getTMin() {
//		return curve.getTMin()*(tMax-tMin)+tMin;
	}

//	float getTMax() {
//		return curve.getTMax()*(tMax-tMin)+tMin;
	}

}
;
}
#endif
