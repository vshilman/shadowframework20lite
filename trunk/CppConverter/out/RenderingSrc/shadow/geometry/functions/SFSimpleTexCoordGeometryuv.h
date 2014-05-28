#ifndef shadow_geometry_functions_H_
#define shadow_geometry_functions_H_


class SFSimpleTexCoordGeometryuv extends SFGenericSurfaceFunction{

//	float du,dv;

//	SFSimpleTexCoordGeometryuv(float du, float dv) {
//		super();
		this->du = du;
		this->dv = dv;
	}

	
//	float getX(float u, float v) {
//		return u*du;
	}
	
//	float getY(float u, float v) {
//		return v*dv;
	}

	
//	float getZ(float u, float v) {
//		return 0;
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
