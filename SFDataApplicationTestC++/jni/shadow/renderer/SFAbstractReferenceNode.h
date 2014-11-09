#ifndef shadow_renderer_SFAbstractReferenceNode_H_
#define shadow_renderer_SFAbstractReferenceNode_H_

#include "shadow/renderer/SFNode.h"

namespace sf{
class SFAbstractReferenceNode : public SFNode {

  	vector<SFNode*> nodes;

public:
  	SFAbstractReferenceNode();
	
  	void addNode(SFNode* node);

  	vector<SFNode*>* getNodes();

  	void removeNode(SFNode* node);

  	SFModel* getModel();
	
  	void init();
	
  	void destroy();

};

}
#endif
