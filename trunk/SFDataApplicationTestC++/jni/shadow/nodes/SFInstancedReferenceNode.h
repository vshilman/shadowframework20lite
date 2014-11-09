#ifndef shadow_nodes_H_
#define shadow_nodes_H_

#include "shadow/renderer/SFAbstractReferenceNode.h"
#include "shadow/renderer/SFNode.h"

namespace sf{

class SFInstancedReferenceNode : public SFAbstractReferenceNode{

public:
	SFNode* copyNode();
};

}
#endif
