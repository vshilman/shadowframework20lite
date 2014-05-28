#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "java/util/ArrayList.h"

#include "shadow/pipeline/SFPipelineTransform3f.h"
#include "shadow/pipeline/SFPipelineTransforms.h"
#include "shadow/system/SFIResource.h"
#include "shadow/system/SFResource.h"

class SFBone implements SFIResource, SFTransformKeeper{

	ArrayList<SFBone> bones=new ArrayList<SFBone>();
//	SFPipelineTransform3f transform;

//	SFResource resource;

//	SFBone(){
		this->transform=SFPipelineTransforms.generateTrasform();
	}

//	SFPipelineTransform3f getTransform() {
//		return transform;
	}

//	void addNode(SFBone bone) {
//		this->bones.add(bone);
//		bone.transform.attachOn(this->transform);
	}

	
//	SFResource getResource() {
//		return resource;
	}

}
;
}
#endif
