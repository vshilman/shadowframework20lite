#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/math/SFVertex2f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/system/SFResource.h"

class SFNormalBasedObjPlaneTexCoordGeometry implements SFDerivedTexCoordFunctionuv{

//	float a,da;
//	float av,bv,cv,dv;

//	SFResource resource=new SFResource(0);

//	SFNormalBasedObjPlaneTexCoordGeometry(float a, float da, float av, float bv, float cv,
//			float dv) {
//		super();
		this->a = a;
		this->da = da;
		this->av = av;
		this->bv = bv;
		this->cv = cv;
		this->dv = dv;
	}

	
//	SFVertex2f getTexCoord(float x, float y, float z, float nx, float ny, float nz) {
//		SFVertex3f dirV=new SFVertex3f(av,bv,cv);
//		SFVertex3f normal=new SFVertex3f(nx,ny,nz);
//		SFVertex3f dirU=normal.cross(dirV);
//		dirU.normalize3f();
//		return new SFVertex2f(a*(dirU.getX() * x + dirU.getY() * y + dirU.getZ()*z)+da, av * x + bv * y + cv * z + dv);
	}

//	SFResource getResource() {
//		return resource;
	}

//	float getAv() {
//		return av;
	}

//	void setAv(float av) {
		this->av = av;
	}

//	float getBv() {
//		return bv;
	}

//	void setBv(float bv) {
		this->bv = bv;
	}

//	float getCv() {
//		return cv;
	}

//	void setCv(float cv) {
		this->cv = cv;
	}

//	float getA() {
//		return a;
	}

//	void setA(float a) {
		this->a = a;
	}

//	float getDa() {
//		return da;
	}

//	void setDa(float da) {
		this->da = da;
	}

//	float getDv() {
//		return dv;
	}

//	void setDv(float dv) {
		this->dv = dv;
	}



}
;
}
#endif
