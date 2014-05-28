#ifndef shadow_animation_data_H_
#define shadow_animation_data_H_

#include "shadow/animation/SFAnimation.h"
#include "shadow/animation/SFPeriodicAnimation.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFInt.h"

namespace sf{
class SFPeriodicAnimationData extends SFDataAsset<SFAnimation>{

	AssetObject<SFAnimation> animation=
			new SFDataAssetObject<SFAnimation>(null);
	SFInt startTime=new SFInt(0);
	SFInt period=new SFInt(0);

	SFPeriodicAnimationData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("startingPosition", startTime);
		parameters.addObject("endingPosition", period);
		parameters.addObject("animation", animation);
		setData(parameters);
	}

	void setup(SFDataAsset<SFAnimation> animation,int startTime,int period){
		this->startTime.setIntValue(startTime);
		this->period.setIntValue(period);
		this->animation.setDataset(animation);
	}

	
	SFAnimation buildResource() {
		SFPeriodicAnimation periodicAnimation=new SFPeriodicAnimation();
		updateResource(periodicAnimation);
		return periodicAnimation;
	}

	
	void updateResource(SFAnimation resource) {

		((SFPeriodicAnimation)(resource)).set(animation.getDataset().getResource(),
				period.getIntValue(),startTime.getIntValue());

	}
}


;
}
#endif
