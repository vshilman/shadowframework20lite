
#include "shadow/nodes/SFObjectModel.h"

namespace sf{


	SFObjectModel::SFObjectModel() {
		setModel(new SFModel());
		getResource()->setResource(0, getModel()->getTransformComponent().getResource());
		getResource()->setResource(1, getModel()->getMaterialComponent().getResource());
	}


	void SFObjectModel::addNode(SFNode* node) {
		nodes.push_back(node);
	}


	SFNode* SFObjectModel::copyNode() {
		SFObjectModel* model=new SFObjectModel();
		model->setModel(this->getModel());
		for(unsigned int i=0;i<nodes.size();i++){
			model->addNode(nodes[i]);
		}
		SFVertex3f tmpV=SFVertex3f();
		SFMatrix3f tmpM=SFMatrix3f();
		this->transform->getPosition(&tmpV);
		model->transform->setPosition(&tmpV);
		this->transform->getOrientation(&tmpM);
		model->transform->setOrientation(&tmpM);
		return model;
	}

	bool SFObjectModel::isDrawable(){
		return this->getModel()->getRootGeometry()!=0;
	}


	void SFObjectModel::init() {
		//Do nothing
	}


	void SFObjectModel::destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}

	vector<SFNode*>* SFObjectModel::getNodes() {
		return &nodes;
	}


}
