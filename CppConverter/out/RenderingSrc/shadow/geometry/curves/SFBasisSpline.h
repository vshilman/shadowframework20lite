#ifndef shadow_geometry_curves_H_
#define shadow_geometry_curves_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/SFResource.h"

///* Note : extracted from SFBasisSpline2
// */
//abstract class SFBasisSpline implements SFCurve{

//	protected SFValuenf[] vertices;

//	protected boolean closed;

//	SFResource resource=new SFResource(0);

//	SFBasisSpline(boolean closed,int size) {
//		super(); 
		this->closed = closed;
//		vertices=new SFValuenf[size];
	}

//	void setSize(int size){
//		//delete vertices here
//		//than

//		vertices=new SFValuenf[size];
	}

//	SFResource getResource() {
//		return resource;
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

//	SFValuenf[] getVertices() {
//		return vertices;
	}

	
//	float getTMax() {
//		return 1;
	}

	
//	float getTMin() {
//		return 0;
	}

	
//	SFValuenf generateValue() {
//		return new SFValuenf(vertices[0].getV().length);
	}

}
;
}
#endif
