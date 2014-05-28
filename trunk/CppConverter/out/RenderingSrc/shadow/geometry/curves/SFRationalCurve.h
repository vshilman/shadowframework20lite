#ifndef shadow_geometry_curves_H_
#define shadow_geometry_curves_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.SFGenericCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex4f.h"
#include "shadow/system/SFResource.h"

class SFRationalCurve extends SFGenericCurve implements SFCurve{

//	SFCurve curve;
//	SFResource resource=new SFResource(0);

//	float[] weights;
//	SFValuenf vertices[];
//	SFValuenf sampleVertex;

//	SFRationalCurve(SFCurve curve,int size) {
//		super();
		this->curve = curve;
//		vertices=new SFValuenf[size];
	}

//	void setSize(int size){
//		//delete vertices here
//		//than

//		vertices=new SFValuenf[size];

	}

//	void setWeights(float[] weights) {
		this->weights = weights;
	}

	
//	void setControlPoint(int index, SFValuenf vertex) {
//		vertices[index]= vertex;
	}

	
//	int getControlPointSize() {
//		return vertices.length;
	}

	
//	SFValuenf getControlPoint(int index) {
//		return vertices[index];
	}


//	void compileCurve(){
//		for (int i = 0; i < vertices.length; i++) {
//			int size=vertices[i].getSize();
//			SFValuenf value=new SFValuenf(size+1);
//			value.set(vertices[i]);
//			value.mult(weights[i]);
//			value.getV()[size]=weights[i];
//			curve.setControlPoint(i, value);
		}
//		sampleVertex=new SFValuenf(vertices[0].getSize()+1);
//		curve.compileCurve();
	}

//	SFResource getResource() {
//		return resource;
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

	
//	void getVertex(float t, SFValuenf read) {
//		this->curve.getVertex(t, sampleVertex);
//		sampleVertex.mult(1.0f/sampleVertex.getV()[sampleVertex.getSize()-1]);
//		read.set(sampleVertex);
	}


}
;
}
#endif
