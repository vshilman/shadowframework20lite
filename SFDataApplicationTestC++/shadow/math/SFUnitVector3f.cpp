#include "SFUnitVector3f.h"



namespace sf{

	SFUnitVector3f::SFUnitVector3f():SFVertex2f(0.0,0.0){
	}

	void SFUnitVector3f::getVertex3f(SFVertex3f* write){
		float cosa=(float)(cos(v[0]));
		float sina=(float)(sin(v[0]));
		float cosb=(float)(cos(v[1]));
		float sinb=(float)(sin(v[1]));
		write->set3f(cosa,sina*cosb,sina*sinb);
	}

	void SFUnitVector3f::setVertex3f(SFVertex3f* read){
		read->normalize3f();
		float cosa=read->getX();
		float sina=(float)(sqrt(1-cosa*cosa));

		v[0]=(float)(atan2(sina, cosa));//SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(cosa, sina);
		float sinaRec=1.0f/sina;
		float cosb=read->getY()*sinaRec;
		float sinb=read->getZ()*sinaRec;
		v[1]=(float)(atan2(sinb, cosb));
	}

}
