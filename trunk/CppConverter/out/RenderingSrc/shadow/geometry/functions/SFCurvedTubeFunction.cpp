#ifndef shadow_geometry_functions_H_
#define shadow_geometry_functions_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFVertex3f.h"

class SFCurvedTubeFunction extends SFGenericSurfaceFunction {

//	SFCurve centralCurve;
//	SFCurve rayCurve;

//	SFVertex3f Ccv=new SFVertex3f();
//	SFVertex3f Vec1v=new SFVertex3f();
//	SFVertex3f Vec2v=new SFVertex3f();
//	SFVertex3f DVec1v=new SFVertex3f();
//	SFVertex3f DVec2v=new SFVertex3f();
//	SFVertex3f dCcdv=new SFVertex3f();

//	SFCurvedTubeFunction() {
//		super();
	}

//	SFCurvedTubeFunction(SFCurve centralCurve,
//			SFCurve rayCurve) {
//		super();
		this->centralCurve = centralCurve;
		this->rayCurve = rayCurve;
	}

//	SFVertex3f getDu() {
//		SFVertex3f du=new SFVertex3f(getDXDu(),getDYDu(),getDZDu());
//		du.normalize3f();
//		return du;
	}

//	SFVertex3f getDv() {
//		SFVertex3f dv=new SFVertex3f(getDXDv(),getDYDv(),getDZDv());
//		dv.normalize3f();
//		return dv;
	}

//	SFVertex3f getPosition() {
//		return new SFVertex3f(getX(),getY(),getZ());
	}

//	float lastV=-1;
//	float cos=0;
//	float sin=0;


	All(float v){
//		if(v!=lastV){
//			centralCurve.getVertex(v, Ccv);
//			centralCurve.getDevDt(v, dCcdv);
//			rayCurve.getVertex(v, Vec1v);
//			Vec1v.subtract3f(Ccv);
//			Vec2v.setArray(dCcdv.cross(Vec1v).getV());
//			Vec2v.mult((float)(Math.sqrt(Vec1v.dot3f(Vec1v)/Vec2v.dot3f(Vec2v))));

//			SFVertex3f dCcdv2=new SFVertex3f();
//			centralCurve.getDev2Dt(v, dCcdv2);
//			rayCurve.getDevDt(v, DVec1v);
//			DVec1v.subtract3f(dCcdv);
//			//DVec1v.normalize3f();
//			DVec2v.setArray(dCcdv.cross(DVec1v).getV());
//			DVec2v.add3f(dCcdv2.cross(Vec1v));
//			//DVec2v.normalize3f();

//			lastV=v;
		}
	}

//	float getX(float u,float v) {
//		evalAll(v);
//		cos=(float)(Math.cos(2*Math.PI*u));
//		sin=(float)(Math.sin(2*Math.PI*u));
//		return Ccv.getX()+cos*Vec1v.getX()+sin*Vec2v.getX();
	}

//	float getY(float u,float v) {
//		return Ccv.getY()+cos*Vec1v.getY()+sin*Vec2v.getY();
	}

//	float getZ(float u,float v) {
//		return Ccv.getZ()+cos*Vec1v.getZ()+sin*Vec2v.getZ();
	}


//	float getX() {
//		return Ccv.getX()+cos*Vec1v.getX()+sin*Vec2v.getX();
	}

//	float getY() {
//		return Ccv.getY()+cos*Vec1v.getY()+sin*Vec2v.getY();
	}

//	float getZ() {
//		return Ccv.getZ()+cos*Vec1v.getZ()+sin*Vec2v.getZ();
	}

//	float getDXDv() {
//		return dCcdv.getX()+cos*DVec1v.getX()+sin*DVec2v.getX();
	}

//	float getDYDv() {
//		return dCcdv.getY()+cos*DVec1v.getY()+sin*DVec2v.getY();
	}

//	float getDZDv() {
//		return dCcdv.getZ()+cos*DVec1v.getZ()+sin*DVec2v.getZ();
	}

//	float getDXDu() {
//		return -sin*Vec1v.getX()+cos*Vec2v.getX();
	}

//	float getDYDu() {
//		return -sin*Vec1v.getY()+cos*Vec2v.getY();
	}

//	float getDZDu() {
//		return -sin*Vec1v.getZ()+cos*Vec2v.getZ();
	}

//	SFCurve getCentralCurve() {
//		return centralCurve;
	}

//	void setCentralCurve(SFCurve centralCurve) {
		this->centralCurve = centralCurve;
	}

//	SFCurve getRayCurve() {
//		return rayCurve;
	}

//	void setRayCurve(SFCurve rayCurve) {
		this->rayCurve = rayCurve;
	}



}
;
}
#endif
