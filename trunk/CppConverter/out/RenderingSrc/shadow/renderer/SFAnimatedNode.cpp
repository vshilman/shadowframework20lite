#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "java/util/ArrayList.h"
#include "java/util/Iterator.h"
#include "java/util/List.h"

#include "shadow/animation/SFTransformNodeAnimation.h"
#include "shadow/pipeline/SFPipelineTransform3f.h"

namespace sf{
class SFAnimatedNode extends SFNode{

//	SFNode contentNode;
	Animation> animations=new ArrayList<SFTransformNodeAnimation>();

//	SFAnimatedNode() {
//		super();
	}

//	SFAnimatedNode(SFNode contentNode) {
//		super();
		this->contentNode = contentNode;
	}

//	void addAnimation(SFTransformNodeAnimation animation){
//		this->animations.add(animation);
	}

//	SFNode getContentNode() {
//		return contentNode;
	}

//	void setContentNode(SFNode contentNode) {
		this->contentNode = contentNode;
	}

	
//	void addNode(SFNode node) {
//		contentNode.addNode(node);
	}

	
//	SFAnimatedNode copyNode() {
//		SFAnimatedNode animatedNode=new SFAnimatedNode();
//		animatedNode.contentNode=contentNode.copyNode();

//		//TODO
//		//for (SFTransformNodeAnimation animation : this->animations) {
//		//	
		}


//		return null;
	}

	
//	void destroy() {
//		contentNode.destroy();
	}

	
//	SFModel getModel() {
//		return contentNode.getModel();
	}

	
//	SFPipelineTransform3f getTransform() {
//		return contentNode.getTransform();
	}

	
//	void init() {
//		contentNode.init();
	}

	
//	Iterator<SFNode> iterator() {
//		return contentNode.iterator();
	}
}
;
}
#endif
