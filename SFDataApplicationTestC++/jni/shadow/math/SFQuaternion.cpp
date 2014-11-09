#include "SFQuaternion.h"



//namespace sf{
//
//	SFQuaternion::SFQuaternion(){
//
//	}
//
//
//	SFQuaternion::SFQuaternion(SFVertex3f direction,double angle) {
//		float cos_=(float)(cos(angle*0.5f));
//		float sin_=(float)(sin(angle*0.5f));
//		v[0]=cos_;
//		v[1]=sin_*direction.getX();
//		v[2]=sin_*direction.getY();
//		v[3]=sin_*direction.getZ();
//	}
//
//	void SFQuaternion::multTo(SFQuaternion q){
//		float w1=v[3]*q.v[3]-v[0]*q.v[0]-v[1]*q.v[1]-v[2]*q.v[2];
//		float x1=v[3]*q.v[0]+v[0]*q.v[3]+v[2]*q.v[1]-v[1]*q.v[2];
//		float y1=v[3]*q.v[1]+v[1]*q.v[3]+v[0]*q.v[2]-v[2]*q.v[0];
//		float z1=v[3]*q.v[2]+v[2]*q.v[3]+v[1]*q.v[0]-v[0]*q.v[1];
//		this->v[0]=x1;
//		this->v[1]=y1;
//		this->v[2]=z1;
//		this->v[3]=w1;
//	}
//
//
//	SFVertex3f* SFQuaternion::applyRotation(SFVertex3f a){
//		float* av=a.getV();
//		SFVertex3f* b=new SFVertex3f(av[0],av[1],av[2]);
//
//		float* bv=b->getV();
//
//		float x1=(float)(v[1]*av[2]-v[2]*av[1]);
//		float y1=(float)(v[2]*av[0]-v[0]*av[2]);
//		float z1=(float)(v[0]*av[1]-v[1]*av[0]);
//
//		float x2=v[1]*z1-v[2]*y1;
//		float y2=v[2]*x1-v[0]*z1;
//		float z2=v[0]*y1-v[1]*x1;
//
//		float wr=1-v[3];
//
//		bv[0]+=x1+wr*x2;
//		bv[1]+=y1+wr*y2;
//		bv[2]+=z1+wr*z2;
//
//		return b;
//	}
//
//	SFMatrix3f* SFQuaternion::getRotationMatrix(){
//		SFMatrix3f* m=new SFMatrix3f();
//
//		m->setA(1 - 2*(v[1]*v[1] + v[2]*v[2]));
//		m->setB(-2*v[2]*v[3]+2*v[0]*v[1]);
//		m->setC(2*v[3]*v[1] +2*v[0]*v[2]);
//
//		m->setD(- 2*v[2]*v[3]+2*v[0]*v[1]);
//		m->setE(- 1 + 2*(v[0]*v[0] + v[2]*v[2]));
//		m->setF(- 2*v[1]*v[2]-2*v[0]*v[3]);
//
//		m->setG(2*v[0]*v[2]-2*v[3]*v[1]);
//		m->setH(2*v[1]*v[1]-2*v[3]*v[0]);
//		m->setI(1 - 2*(v[0]*v[0] + v[1]*v[1]));
//
//		return m;
//	}
//
//}
