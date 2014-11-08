#ifndef shadow_nodes_H_
#define shadow_nodes_H_


#include "shadow/math/SFMatrix3f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFAbstractReferenceNode.h"
#include "shadow/renderer/SFNode.h"

namespace sf{
class SFReferenceNode : public SFAbstractReferenceNode {

	
public:
  	SFNode* copyNode();

  	void cloneOn(SFReferenceNode* reference);

};

}
#endif
