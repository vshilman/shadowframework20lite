#ifndef shadow_animation_H_
#define shadow_animation_H_

#include "shadow/renderer/SFTransformKeeper.h"

//abstract class SFTransformNodeAnimation implements SFAnimation{

//	SFTweener tweener;
//	SFTransformKeeper transformNode;
//	long duration;
//	long startingTime;

//	SFTransformNodeAnimation() {
	}

//	SFTransformNodeAnimation(SFTransformKeeper transformNode) {
//		super();
		this->transformNode=transformNode;
	}

//	SFTransformNodeAnimation(SFTransformKeeper transformNode, long duration, long startingTime,SFTweener tweener) {
//		super();
		this->tweener = tweener;
		this->transformNode = transformNode;
		this->duration = duration;
		this->startingTime = startingTime;
	}

//	SFTransformNodeAnimation(SFTransformKeeper transformNode,
//			long duration, long startingTime) {
//		super();
		this->transformNode = transformNode;
		this->duration = duration;
		this->startingTime = startingTime;
	}

//	void set(){

	}

//	SFTransformKeeper getTransformNode() {
//		return transformNode;
	}

//	void setTransformNode(SFTransformKeeper transformNode) {
		this->transformNode = transformNode;
	}

	
//	abstract SFTransformNodeAnimation clone();

//	protected abstract void applyTransform(double T);


//	protected SFTweener getTweener() {
//		return tweener;
	}

//	protected void setTweener(SFTweener tweener) {
		this->tweener=tweener;
	}

//	protected void setDuration(long duration) {
		this->duration = duration;
	}

//	long getDuration() {
//		return duration;
	}

//	protected void setStartingTime(long startingTime) {
		this->startingTime = startingTime;
	}

//	protected long getStartingTime() {
//		return startingTime;
	}

//	void animate(long time) {

//		if(getTransformNode()!=null){
//			if(time>=startingTime && time<=startingTime+duration){

//				double T=(time-startingTime)/(1.0f*duration);

//				if(tweener!=null)
//					T=tweener.tweenValue(T);
//				applyTransform(T);
			}
		}
	}
}
;
}
#endif
