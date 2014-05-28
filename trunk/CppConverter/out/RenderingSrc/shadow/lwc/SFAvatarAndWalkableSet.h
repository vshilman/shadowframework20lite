#ifndef shadow_lwc_H_
#define shadow_lwc_H_

#include "shadow/animation/SFAnimator.h"
#include "shadow/animation/SFPropertyControlTimer.h"
#include "shadow/animation/SFPropertyListener.h"
#include "shadow/lwc/SFAvatar.AvatarProperties.h"

///**
// * The set of 1 avatar and 1 walkable
// * 
// * @author Alessandro
// */
namespace sf{
class SFAvatarAndWalkableSet {

	Avatar avatar;
//	SFIWalkable walkable;

//	SFAvatarAndWalkableSet( SFIWalkable walkable) {
//		super();
		this->walkable = walkable;
	}



//	void setAvatar(SFAvatar avatar) {
		this->avatar = avatar;
//		setupProperties();
	}



//	void setWalkable(SFIWalkable walkable) {
		this->walkable = walkable;
	}

//	SFIWalkable getWalkable() {
//		return walkable;
	}

//	void updateThings(){
//		//TODO: not using is IN, no more
//		if(avatar!=null){
//			walkable.checkIn(avatar.getPosition());		
//			avatar.update();
		}

	}


//	SFAvatar getAvatar() {
//		return avatar;
	}

//	void setupProperties(){

//		SFPropertyControlTimer.getTimer().addProperty(avatar, AvatarProperties.DCA);
//		SFPropertyControlTimer.getTimer().addProperty(avatar, AvatarProperties.FRONT_MOVE);
//		SFPropertyControlTimer.getTimer().addProperty(avatar, AvatarProperties.WXY);
//		SFPropertyControlTimer.getTimer().addProperty(avatar, AvatarProperties.WADD);
//		SFPropertyControlTimer.getTimer().addProperty(avatar, AvatarProperties.WC);
//		SFPropertyControlTimer.getTimer().addProperty(avatar, AvatarProperties.WH);
//		SFPropertyControlTimer.getTimer().addProperty(avatar, AvatarProperties.WHP);
//		SFPropertyControlTimer.getTimer().addProperty(avatar, AvatarProperties.WXY);

//		SFPropertyListener updateThingsListener=new SFPropertyListener() {
			
//			void addProperty(Object id, float value) {
//				updateThings();
			}
		}
//		SFPropertyControlTimer.getTimer().addProperty(updateThingsListener, updateThingsListener);
//		SFPropertyControlTimer.getTimer().setPropertyVel(updateThingsListener, updateThingsListener, 1);

//		SFAnimator.addAnimation(SFPropertyControlTimer.getTimer());
//		//SFAnimationTimer.startTimer();
	}
}
;
}
#endif
