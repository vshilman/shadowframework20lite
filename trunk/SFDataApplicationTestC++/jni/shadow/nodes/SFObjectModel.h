#ifndef shadow_nodes_SFObjectModel_H_
#define shadow_nodes_SFObjectModel_H_

#include "shadow/math/SFMatrix3f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFModel.h"
#include "shadow/renderer/SFNode.h"

namespace sf{
class SFObjectModel: public SFNode{

	vector<SFNode*> nodes;

public:
  	SFObjectModel();

  	void addNode(SFNode* node);

  	SFNode* copyNode();

  	bool isDrawable();

  	void init();

  	void destroy();

  	vector<SFNode*>* getNodes();

};

}
#endif
