#ifndef shadow_animation_H_
#define shadow_animation_H_

#include "java/util/ArrayList.h"

///**
// *
// * @author Alessandro Martinelli
// */
namespace sf{
class SFAnimator {

	ArrayList<SFAnimation> animations=new ArrayList<SFAnimation>();

	Animator(){

	}

	static void addAnimation(SFAnimation animation){
//		animations.add(animation);
	}

	static void removeAnimation(SFAnimation animation){
//		animations.remove(animation);
	}

	static void animate(long time){
//		for (int i = 0; i < animations.size(); i++) {
//			animations.get(i).animate(time);
		}
	}
}
;
}
#endif
