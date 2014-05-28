#ifndef shadow_animation_H_
#define shadow_animation_H_

#include "shadow/math/SFQuaternion.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFTransformKeeper.h"
#include "shadow/system/SFResource.h"

namespace sf{
class SFRotateAnimation extends SFTransformNodeAnimation implements SFAnimation{

//	SFVertex3f direction;
	Angle;
	Angle;
//	SFResource resource=new SFResource(0);

//	SFRotateAnimation() {
//		// TODO Auto-generated constructor stub
	}

//	SFRotateAnimation(SFVertex3f direction, float firstAngle, float lastAngle, long duration,
//			long startingTime, SFTransformKeeper transformNode) {
//		super(transformNode,duration,startingTime);
		this->direction = direction;
		this->firstAngle = firstAngle;
		this->lastAngle = lastAngle;
	}

//	SFRotateAnimation(SFVertex3f direction, float firstAngle, float lastAngle, long duration,
//			long startingTime, SFTransformKeeper transformNode, SFTweener tweener) {
//		super(transformNode,duration,startingTime,tweener);
		this->direction = direction;
		this->firstAngle = firstAngle;
		this->lastAngle = lastAngle;
	}

//	void set(SFVertex3f direction, float firstAngle, float lastAngle, long duration,
//			long startingTime, SFTransformKeeper transformNode, SFTweener tweener){
//		setTransformNode(transformNode);
//		setDuration(duration);
//		setStartingTime(startingTime);
//		setTweener(tweener);
		this->direction = direction;
		this->firstAngle = firstAngle;
		this->lastAngle = lastAngle;
	}

	
//	SFRotateAnimation clone() {
//		return new SFRotateAnimation(direction,firstAngle,lastAngle,getDuration(),getStartingTime(),
//				getTransformNode(),getTweener());
	}

//	SFResource getResource() {
//		return resource;
	}

	
//	protected void applyTransform(double T) {
//		double angle=firstAngle*(1-T)+lastAngle*T;

//		SFQuaternion quaternion = new SFQuaternion(this->direction,angle);

//		getTransformNode().getTransform().setOrientation(quaternion.getRotationMatrix());
	}

}
;
}
#endif
