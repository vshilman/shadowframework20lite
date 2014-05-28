#ifndef shadow_geometry_functions_H_
#define shadow_geometry_functions_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFValuenf.h"

class SFTensorProductSurface extends SFGenericSurfaceFunction{

//	SFCurve[] guideLines;
//	SFCurve productCurve;

//	SFValuenf lastVertex;

//	SFTensorProductSurface(){

	}

//	void setGuideLines(SFCurve[] guideLines) {
		this->guideLines = guideLines;
	}

//	void setProductCurve(SFCurve productCurve) {
		this->productCurve = productCurve;
//		lastVertex=productCurve.generateValue();
	}

//	void evaluateLastVertex(float u,float v) {
//		for (int i = 0; i < guideLines.length; i++) {
//			SFValuenf vertex=productCurve.getControlPoint(i);
//			guideLines[i].getVertex(u, vertex);
		}
//		productCurve.compileCurve();
//		productCurve.getVertex(v, lastVertex);
	}

	
//	float getX(float u, float v) {
//		evaluateLastVertex(u,v);
//		return lastVertex.getV()[0];
	}

	
//	float getY(float u, float v) {
//		return lastVertex.getV()[1];
	}

	
//	float getZ(float u, float v) {
//		return lastVertex.getV()[2];
	}
}
;
}
#endif
