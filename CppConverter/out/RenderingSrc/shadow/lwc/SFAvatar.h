#ifndef shadow_lwc_H_
#define shadow_lwc_H_

#include "shadow/animation/SFPropertyListener.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFCamera.h"
#include "shadow/renderer/SFViewTransform.h"

///**
// * 
// * A Point in space viewed by a camera.
// * 
// * @author Alessandro
// */
namespace sf{
class SFAvatar implements SFPropertyListener{

//	enum AvatarProperties{
//		DCA,//Camera Avatar Distance
//		WC,//Camera Inclination
//		WADD,//Camera Rotation
//		WXY,//World Orientation
//		WH,//World Inclination
//		WHP,//World Inclination Perp
//		FRONT_MOVE,
	}

	AvatarModel avatarModel;

//	SFCamera controlledCamera;
//	SFVertex3f position=new SFVertex3f();
//	float deltaUp=0.8f;
//	SFVertex3f direction=new SFVertex3f();
//	SFVertex3f directionPerp=new SFVertex3f();
//	SFVertex3f direction2D=new SFVertex3f();
//	SFVertex3f directionPerp2D=new SFVertex3f();

//	//Relative camera 2D orientation, adding to wxy
//	float wxy;
//	//Relative camera 2D orientation, adding to wxy
//	float wh;
//	//Relative camera 2D orientation, adding to wxy
//	float whp;

//	//Distance between the avatar and the Camera
//	float dca;
//	//Inclination of the camera
//	float wc;
//	//Relative camera 2D orientation, adding to wxy
//	float wadd;



//	SFAvatar(SFCamera controlledCamera) {
//		super();
		this->controlledCamera = controlledCamera;
	}

//	void setAvatarModel(SFIAvatarModel avatarModel) {
		this->avatarModel = avatarModel;
	}

//	void setupCamera(float dca,float wc,float wadd){
		this->wc=wc;
		this->wadd=wadd;
		this->dca=dca;
	}

//	synchronized void setupAvatarAngles(SFVertex3f post,float wxy,float wh,float whp){
//		this->position.set(position);
		this->wxy=wxy;
		this->wh=wh;
		this->whp=whp;

//		update();
	}

//	void update() {
//		updateAvatarDirections();
//		updateCamera();
//		avatarModel.updateModel(this);
	}


	AvatarDirections() {
//		float coswxy=(float)Math.cos(wxy);
//		float sinwxy=(float)Math.sin(wxy);
//		float coswh=(float)Math.cos(wh);
//		float sinwh=(float)Math.sin(wh);
//		float coswhp=(float)Math.cos(whp);
//		float sinwhp=(float)Math.sin(whp);

//		direction.set3f(coswxy*coswh,sinwh,sinwxy*coswh);
//		directionPerp.set3f(-sinwxy*coswhp,sinwhp,coswxy*coswhp);
//		direction2D.set3f(coswxy,0,sinwxy);
//		directionPerp2D.set3f(-sinwxy,0,coswxy);
	}

//	void updateCamera(){

//		float k=this->controlledCamera.getDelta()+dca;

//		float wxys=wxy+wadd;
//		float coswxys=(float)Math.cos(wxys);
//		float sinwxys=(float)Math.sin(wxys);
//		float coswc=(float)Math.cos(wc);
//		float sinwc=(float)Math.sin(wc);

//		//TODO : this is not the way to do it
//		controlledCamera.setDistance(100);
//		controlledCamera.setDelta(1.5f);

//		controlledCamera.getDir().set3f(coswxys*coswc,sinwc,sinwxys*coswc);
//		controlledCamera.getLeft().set3f(sinwxys,0,-coswxys);

//		controlledCamera.setUp(controlledCamera.getDir().cross(controlledCamera.getLeft()));

//		controlledCamera.getF().set(position);
//		controlledCamera.getF().add3f(new SFVertex3f(0,deltaUp,0));
//		controlledCamera.getF().addMult(-k, controlledCamera.getDir());

//		controlledCamera.update();
	}


	
//	void addProperty(Object id, float value) {
//		AvatarProperties property=(AvatarProperties)id;
//		switch (property) {
				this->dca+= break;;
				this->wc+= break;;
				this->wadd+= break;;
				this->wxy+= break;;
				this->wh+= break;;
				this->whp+= break;;
//				case FRONT_MOVE: this->position.addMult(value, getDirection()); break;

//			default:
//				break;
		}
	}


//	SFVertex3f getDirection() {
//		return direction;
	}

//	SFVertex3f getDirectionPerp() {
//		return directionPerp;
	}

//	void setDeltaUp(float deltaUp){
		this->deltaUp=deltaUp;
	}



//	SFViewTransform getControlledCamera() {
//		return controlledCamera;
	}

//	void setControlledCamera(SFCamera controlledCamera) {
		this->controlledCamera = controlledCamera;
	}

//	SFVertex3f getPosition() {
//		return position;
	}

//	float getWxy() {
//		return wxy;
	}

//	float getWh() {
//		return wh;
	}

//	float getWhp() {
//		return whp;
	}

//	float getWc() {
//		return wc;
	}

//	float getWadd() {
//		return wadd;
	}

//	float getDca() {
//		return dca;
	}

}
;
}
#endif
