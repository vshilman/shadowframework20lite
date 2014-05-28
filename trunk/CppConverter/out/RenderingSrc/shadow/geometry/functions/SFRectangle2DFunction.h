#ifndef shadow_geometry_functions_H_
#define shadow_geometry_functions_H_


class SFRectangle2DFunction  extends SFGenericSurfaceFunction{

//	float x,y,w,h;

//	SFRectangle2DFunction(float x, float y, float w, float h) {
//		super();
		this->x = x;
		this->y = y;
		this->w = w;
		this->h = h;
	}

	
//	float getX(float u, float v) {
//		return x+u*w;
	}

	
//	float getY(float u, float v) {
//		return y+v*h;
	}

	
//	float getZ(float u, float v) {
//		return 0;
	}

//	float getX() {
//		return x;
	}

//	void setX(float x) {
		this->x = x;
	}

//	float getY() {
//		return y;
	}

//	void setY(float y) {
		this->y = y;
	}

//	float getW() {
//		return w;
	}

//	void setW(float w) {
		this->w = w;
	}

//	float getH() {
//		return h;
	}

//	void setH(float h) {
		this->h = h;
	}

}
;
}
#endif
