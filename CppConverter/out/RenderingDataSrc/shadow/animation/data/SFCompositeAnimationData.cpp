#ifndef shadow_animation_data_H_
#define shadow_animation_data_H_

#include "shadow/animation/SFAnimation.h"
#include "shadow/animation/SFCompositeAnimation.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFCompositeAnimationData extends SFDataAsset<SFAnimation>{

	AssetList<SFAnimation> animations;

	SFCompositeAnimationData() {
		animations=new SFDataAssetList<SFAnimation>();
		SFNamedParametersObject* namedParameters=new SFNamedParametersObject();
		namedParameters->addObject("animations", animations);
		setData(namedParameters);
	}

	void addAnimation(SFDataAsset<SFAnimation> animation){
		animations.add(animation);
	}

	
	SFCompositeAnimation buildResource() {
		SFCompositeAnimation animation=new SFCompositeAnimation();

		updateResource(animation);

		return animation;
	}

	
	void updateResource(SFAnimation resource) {
		for (int i = 0; i < animations.size(); i++) {
			((SFCompositeAnimation)resource).add(animations.get(i).getResource());
		}
	}

}
;
}
#endif
