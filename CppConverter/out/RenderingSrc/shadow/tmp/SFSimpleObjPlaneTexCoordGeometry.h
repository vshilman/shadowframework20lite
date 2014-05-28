#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/math/SFVertex2f.h"
#include "shadow/system/SFResource.h"

class SFSimpleObjPlaneTexCoordGeometry implements SFDerivedTexCoordFunctionuv{

//	float au,bu,cu,du;
//	float av,bv,cv,dv;

//	SFResource resource=new SFResource(0);

//	SFSimpleObjPlaneTexCoordGeometry(float au, float bu, float cu, float du, float av,
//			float bv, float cv, float dv) {
//		super();
		this->au = au;
		this->bu = bu;
		this->cu = cu;
		this->du = du;
		this->av = av;
		this->bv = bv;
		this->cv = cv;
		this->dv = dv;
	}

	
//	SFVertex2f getTexCoord(float x, float y, float z, float nx, float ny, float nz) {
//		return new SFVertex2f(au * x + bu * y + cu * z + du, av * x + bv * y + cv * z + dv);
	}

//	SFResource getResource() {
//		return resource;
	}

//	protected float getAu() {
//		return au;
	}

//	protected void setAu(float au) {
		this->au = au;
	}

//	protected float getBu() {
//		return bu;
	}

//	protected void setBu(float bu) {
		this->bu = bu;
	}

//	protected float getCu() {
//		return cu;
	}

//	protected void setCu(float cu) {
		this->cu = cu;
	}

//	protected float getAv() {
//		return av;
	}

//	protected void setAv(float av) {
		this->av = av;
	}

//	protected float getBv() {
//		return bv;
	}

//	protected void setBv(float bv) {
		this->bv = bv;
	}

//	protected float getCv() {
//		return cv;
	}

//	protected void setCv(float cv) {
		this->cv = cv;
	}

//	float getDu() {
//		return du;
	}

//	void setDu(float du) {
		this->du = du;
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
