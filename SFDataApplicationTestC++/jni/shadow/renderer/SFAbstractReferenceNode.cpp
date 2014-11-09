
#include "shadow/renderer/SFAbstractReferenceNode.h"


namespace sf{

  	SFAbstractReferenceNode::SFAbstractReferenceNode() {

	}

  	void SFAbstractReferenceNode::addNode(SFNode* node) {
  		nodes.push_back(node);
  		node->getTransform()->attachOn(*(this->getTransform()));
  		getResource()->setResource(nodes.size()-1, node->getResource());
  		getResource()->resourceChanged();
	}


  	vector<SFNode*>* SFAbstractReferenceNode::getNodes() {
  		return &nodes;
	}

  	void SFAbstractReferenceNode::removeNode(SFNode* node) {

  		//TODO: the transform from TransformNode va eliminata e pulita
  		//TODO
  		//nodes.find(node);
	}


  	SFModel* SFAbstractReferenceNode::getModel() {
  		return 0;//Reference Nodes has no Model
	}


  	void SFAbstractReferenceNode::init() {
  		//Do nothing
	}


  	void SFAbstractReferenceNode::destroy() {
  		//Its correct: if init isn't doing anything, destroy should not do anything
	}

}
