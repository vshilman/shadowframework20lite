#include "shadow/nodes/SFReferenceNode.h"

namespace sf{

  	SFNode* SFReferenceNode::copyNode() {
  		SFReferenceNode* reference=new SFReferenceNode();
  		cloneOn(reference);
  		return reference;
	}

  	void SFReferenceNode::cloneOn(SFReferenceNode* reference) {
  		for(unsigned int i=0;i<getNodes()->size();i++){
  			reference->addNode(getNodes()->at(i)->copyNode());
  		}
  		SFVertex3f tmpV=SFVertex3f();
  		SFMatrix3f tmpM=SFMatrix3f();
  		this->transform->getPosition(&tmpV);
  		reference->transform->setPosition(&tmpV);
  		this->transform->getOrientation(&tmpM);
  		reference->transform->setOrientation(&tmpM);
	}

}
