#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/nodes/SFReferenceNode.h"
#include "shadow/renderer/SFAbstractReferenceNode.h"

namespace sf{
class SFReferenceNodeData extends SfAbstractReferenceNodeData {

	
	SFAbstractReferenceNode generateReferenceNode() {
		return new SFReferenceNode();
	}

}
;
}
#endif
