#ifndef shadow_geometry_curves_H_
#define shadow_geometry_curves_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/SFResource.h"

class SFSpline implements SFCurve{

//	SFCurve curve;
//	int curveSize;
//	int curveCount;

//	SFValuenf[] controlPoints;

//	SFSpline(int size) {
//		super();
		this->controlPoints = new SFValuenf[size];
	}

//	void setSize(int size){
//		//delete vertices here
//		//than

//		controlPoints=new SFValuenf[size];
	}

//	SFResource resource=new SFResource(0);

//	void setCurve(SFCurve curve) {
		this->curve = curve;
	}

	
//	void compileCurve() {
//		curve.compileCurve();
		this->curveSize=curve.getControlPointSize();
		this->curveCount=(controlPoints.length-1)/(curveSize-1);
	}

//	SFResource getResource() {
//		return resource;
	}

	
//	void setControlPoint(int index, SFValuenf vertex) {
//		controlPoints[index] = vertex;
	}

	
//	int getControlPointSize() {
//		return controlPoints.length;
	}

	
//	SFValuenf getControlPoint(int index) {
//		return controlPoints[index];
	}

	
//	void getDev2Dt(float T, SFValuenf read) {
//		T = selectCurve(T);
//		curve.getDev2Dt(T, read);
	}

//	void getDevDt(float T, SFValuenf read) {
//		T = selectCurve(T);
//		curve.getDevDt(T, read);
	}

	
//	void getVertex(float T, SFValuenf read) {
//		T = selectCurve(T);
//		curve.getVertex(T, read);
	}

//	float selectCurve(float T) {
//		float t=T*curveCount;
//		int index=(int)t;
//		if(index==curveCount){
//			index--;
		}
//		t-=index;
//		for (int i = 0; i < curveSize; i++) {
//			System.err.println("index "+index+" "+curveSize+" "+i+ " "+(index*(curveSize-1)+i)+" "+T);
//			curve.setControlPoint(i, controlPoints[index*(curveSize-1)+i]);
		}
//		return t;
	}

	
//	SFValuenf generateValue() {
//		return new SFValuenf(controlPoints[0].getSize());
	}

	
//	float getTMax() {
//		return 1;
	}

	
//	float getTMin() {
//		return 0;
	}

}
;
}
#endif
