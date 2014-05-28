#ifndef shadow_animation_H_
#define shadow_animation_H_

#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFTransformKeeper.h"
#include "shadow/system/SFResource.h"

///**
// * Generate a move animation.
// * 
// * @author Alessandro
// */
namespace sf{
class SFMoveAnimation extends SFTransformNodeAnimation implements SFAnimation{

//	SFVertex3f startingPosition;
//	SFVertex3f endingPosition;

//	SFResource resource=new SFResource(0);

//	SFMoveAnimation() {

	}

//	SFMoveAnimation(SFVertex3f startingPosition, SFVertex3f endingPosition, long duration,
//			long startingTime, SFTransformKeeper transformNode) {
//		super(transformNode,duration,startingTime);
		this->startingPosition = startingPosition;
		this->endingPosition = endingPosition;
	}

//	SFMoveAnimation(SFVertex3f startingPosition, SFVertex3f endingPosition, long duration,
//			long startingTime, SFTransformKeeper transformNode, SFTweener tweener) {
//		super(transformNode,duration,startingTime,tweener);
		this->startingPosition = startingPosition;
		this->endingPosition = endingPosition;
	}

//	void set(SFVertex3f startingPosition, SFVertex3f endingPosition, long duration,
//			long startingTime, SFTransformKeeper transformNode, SFTweener tweener){
//		setTransformNode(transformNode);
//		setDuration(duration);
//		setStartingTime(startingTime);
//		setTweener(tweener);
		this->startingPosition = startingPosition;
		this->endingPosition = endingPosition;
	}

//	SFResource getResource() {
//		return resource;
	}

	
//	SFTransformNodeAnimation clone() {
//		return new SFMoveAnimation(startingPosition, endingPosition, getDuration(),
//				getStartingTime(), getTransformNode(),getTweener());
	}

	
//	protected void applyTransform(double T) {
//		SFVertex3f position=new SFVertex3f();
//		position.set(startingPosition);
//		position.mult(((float)T));
//		position.addMult((1-(float)T),endingPosition);
//		getTransformNode().getTransform().setPosition(position);
	}

}
;
}
#endif
