#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.SFGenericCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex4f.h"
#include "shadow/system/SFResource.h"

class SFRationalCurve3f extends SFGenericCurve implements SFCurve{

//	SFCurve curve;
//	SFResource resource=new SFResource(0);

//	SFRationalCurve3f(SFCurve curve) {
//		super();
		this->curve = curve;
	}

//	SFResource getResource() {
//		return resource;
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

	
//	SFValuenf generateValue() {
//		return new SFVertex4f();
	}

	
//	float getTMax() {
//		return curve.getTMax();
	}

	
//	float getTMin() {
//		return curve.getTMin();
	}

//	static SFVertex4f getVertex=new SFVertex4f();

	
//	void getVertex(float t, SFValuenf read) {
//		this->curve.getVertex(t, getVertex);
//		float recW=1.0f/getVertex.getW();
		}
//		read.setArray(vs);
	}


}
;
}
#endif
