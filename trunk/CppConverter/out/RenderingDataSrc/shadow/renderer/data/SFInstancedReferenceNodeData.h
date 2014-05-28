#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/nodes/SFInstancedReferenceNode.h"
#include "shadow/renderer/SFAbstractReferenceNode.h"

namespace sf{
class SFInstancedReferenceNodeData extends SfAbstractReferenceNodeData {

	
	SFAbstractReferenceNode generateReferenceNode() {
		return new SFInstancedReferenceNode();
	}
}
;
}
#endif
