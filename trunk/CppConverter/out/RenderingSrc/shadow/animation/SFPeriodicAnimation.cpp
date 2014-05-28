#ifndef shadow_animation_H_
#define shadow_animation_H_

#include "shadow/system/SFResource.h"

///**
// * A Decorator which takes any animation and makes it periodic.
// * 
// * @author Alessandro Martinelli
// */
namespace sf{
class SFPeriodicAnimation implements SFAnimation{

	Animation animation;
//	long period;
//	long startingTime;
//	SFResource resource=new SFResource(0);

//	SFPeriodicAnimation() {
//		// TODO Auto-generated constructor stub
	}

//	SFPeriodicAnimation(SFAnimation animation, long period, long startingTime) {
//		super();
//		set(animation, period, startingTime);
	}

//	void set(SFAnimation animation, long period, long startingTime) {
		this->animation = animation;
		this->period = period;
		this->startingTime = startingTime;
	}

	
//	SFAnimation clone() {
//		return new SFPeriodicAnimation(animation, period, startingTime);
	}

//	SFResource getResource() {
//		return resource;
	}


//	SFAnimation getAnimation() {
//		return animation;
	}


//	long getPeriod() {
//		return period;
	}


//	long getStartingTime() {
//		return startingTime;
	}


	
//	void animate(long time) {
//		time=(time-startingTime)%period;
//		animation.animate(time);
	}

}
;
}
#endif
