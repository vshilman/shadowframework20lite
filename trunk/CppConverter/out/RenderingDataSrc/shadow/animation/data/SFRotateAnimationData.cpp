#ifndef shadow_animation_data_H_
#define shadow_animation_data_H_

#include "shadow/animation/SFAnimation.h"
#include "shadow/animation/SFRotateAnimation.h"
#include "shadow/animation/SFStandardTweeners.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFFloat.h"
#include "shadow/system/data.objects.SFInt.h"
#include "shadow/system/data.objects.SFVertex3fData.h"

namespace sf{
class SFRotateAnimationData extends SFDataAsset<SFAnimation>{

	SFVertex3fData direction=new SFVertex3fData();
	Angle=new SFFloat(0);
	Angle=new SFFloat(0);
	SFInt duration=new SFInt(0);
	SFInt startingTime=new SFInt(0);
	SFLibraryReference<SFNode> node=new SFLibraryReference<SFNode>();

	SFRotateAnimationData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("direction", direction);
		parameters.addObject("startAngle", startAngle);
		parameters.addObject("lastAngle", lastAngle);
		parameters.addObject("duration", duration);
		parameters.addObject("startingTime", startingTime);
		parameters.addObject("node", node);
		setData(parameters);
	}

	
	SFAnimation buildResource() {

		final SFRotateAnimation moveAnimation=new SFRotateAnimation();
		updateResource(moveAnimation);

		return moveAnimation;
	}

	
	void updateResource(SFAnimation resource) {
		final SFRotateAnimation moveAnimation=(SFRotateAnimation)resource;
		moveAnimation.set(direction.getVertex3f(),
				startAngle.getFloatValue(),lastAngle.getFloatValue(),duration.getIntValue(),startingTime.getIntValue(),
				node.getResource(),SFStandardTweeners.CUBIC);
	}

	void setup(SFGraphicsAsset<SFNode> nodeAsset,SFVertex3f direction, 
			float startAngle, float lastAngle, int duration,
			int startingTime) {
		this->node.setDataset(nodeAsset);
		this->direction.getVertex3f().set(direction);
		this->startAngle.setFloatValue(startAngle);
		this->lastAngle.setFloatValue(lastAngle);
		this->duration.setIntValue((int)duration);
		this->startingTime.setIntValue((int)startingTime);

	}

	void setup(String name,SFVertex3f direction, 
			float startAngle, float lastAngle, int duration,
			int startingTime) {
		this->node.setReference(name);
		this->direction.getVertex3f().set(direction);
		this->startAngle.setFloatValue(startAngle);
		this->lastAngle.setFloatValue(lastAngle);
		this->duration.setIntValue((int)duration);
		this->startingTime.setIntValue((int)startingTime);

	}

}
;
}
#endif
