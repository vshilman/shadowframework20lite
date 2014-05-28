#ifndef shadow_animation_data_H_
#define shadow_animation_data_H_

#include "shadow/animation/SFAnimation.h"
#include "shadow/animation/SFMoveAnimation.h"
#include "shadow/animation/SFStandardTweeners.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFInt.h"
#include "shadow/system/data.objects.SFVertex3fData.h"

namespace sf{
class SFMoveAnimationData extends SFDataAsset<SFAnimation>{

	SFVertex3fData startingPosition=new SFVertex3fData();
	SFVertex3fData endingPosition=new SFVertex3fData();
	SFInt duration=new SFInt(0);
	SFInt startingTime=new SFInt(0);
	SFLibraryReference<SFNode> node=new SFLibraryReference<SFNode>();

	SFMoveAnimationData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("startingPosition", startingPosition);
		parameters.addObject("endingPosition", endingPosition);
		parameters.addObject("duration", duration);
		parameters.addObject("startingTime", startingTime);
		parameters.addObject("node", node);
		setData(parameters);
	}

	
	SFAnimation buildResource() {

		final SFMoveAnimation moveAnimation=new SFMoveAnimation();

		updateResource(moveAnimation);
//		node.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFNode>>() {

//			void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
//				moveAnimation.setTransformNode(dataset.getResource());
}
}

		return moveAnimation;
	}

	
	void updateResource(SFAnimation resource) {
		final SFMoveAnimation moveAnimation=(SFMoveAnimation)resource;
		moveAnimation.set(startingPosition.getVertex3f(),
				endingPosition.getVertex3f(),duration.getIntValue(),startingTime.getIntValue(),
				null,SFStandardTweeners.CUBIC);
		moveAnimation.setTransformNode(node.getResource());
	}

	void setup(SFGraphicsAsset<SFNode> nodeAsset,SFVertex3f startingPosition, SFVertex3f endingPosition, int duration,
			int startingTime) {
		this->node.setDataset(nodeAsset);
		this->startingPosition.getVertex3f().set(startingPosition);
		this->endingPosition.getVertex3f().set(endingPosition);
		this->duration.setIntValue((int)duration);
		this->startingTime.setIntValue((int)startingTime);

	}

	void setup(String name,SFVertex3f startingPosition, SFVertex3f endingPosition, int duration,
			int startingTime) {
		this->node.setReference(name);
		this->startingPosition.getVertex3f().set(startingPosition);
		this->endingPosition.getVertex3f().set(endingPosition);
		this->duration.setIntValue((int)duration);
		this->startingTime.setIntValue((int)startingTime);

	}

}
;
}
#endif
