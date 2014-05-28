#ifndef shadow_animation_H_
#define shadow_animation_H_

#include "java/util/ArrayList.h"

#include "shadow/system/SFResource.h"


///**
// * A property Controller operating through time.
// * Will take effect each 'actuatingTime', changing a property according
// * to its assigned 'vel'. 
// * 
// * 
// * @author Alessandro
// */
namespace sf{
class SFPropertyControlTimer extends SFPeriodicAnimation{

//	static SFPropertyControlTimer timer=new SFPropertyControlTimer();

	static SFPropertyControlTimer getTimer() {
//		return timer;
	}

//	static final long period=1000;
//	static final long actuatingTime=100;
	Actuation=-1;

	Animation implements SFAnimation{
//		SFResource resource=new SFResource(0);
		
//		void animate(long time) {
//			long actuating=time/actuatingTime;
//			if(actuating!=lastActuation){
//				timer.execute();
			}
//			lastActuation=actuating;
		}
		
//		SFAnimation clone() {
//			return this;
		}


//		SFResource getResource() {
//			return resource;
		}
	}


	class PropertyController{
//		float vel;
//		SFPropertyListener property;
//		Object object;

//		PropertyController(float vel, SFPropertyListener property,Object object) {
//			super();
			this->vel = vel;
			this->property = property;
			this->object=object;
		}

//		float getVel() {
//			return vel;
		}

//		void setVel(float vel) {
			this->vel = vel;
		}

	}

	ArrayList<PropertyController> properties=new ArrayList<SFPropertyControlTimer.PropertyController>();

//	SFPropertyControlTimer() {
//		super(new SFControllerAnimation(), period, 0);
//		// TODO Auto-generated constructor stub
	}

//	void execute(){
//		//System.err.println("Executing "+properties.size());
//		for (int i = 0; i < properties.size(); i++) {

//			PropertyController propertyController=properties.get(i);
//			float value=propertyController.vel*actuatingTime;

//			if(value!=0){
//				//System.err.println("value "+value+" "+propertyController.object);
//				propertyController.property.addProperty(propertyController.object, value);
			}
		}
	}

//	void addProperty(SFPropertyListener propertyListener,Object property){
//		properties.add(new PropertyController(0, propertyListener,property));
	}

//	void setPropertyVel(SFPropertyListener propertyListener,Object property,float vel){

//		//TODO this way, its bad!
//		for (int i = 0; i < properties.size(); i++) {
//			if(properties.get(i).property==propertyListener && properties.get(i).object==property){
//				System.err.println("property Vel "+vel+" "+property);
//				properties.get(i).setVel(vel);
			}
		}
	}
}
;
}
#endif
