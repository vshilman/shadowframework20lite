
#include "SFNode.h"

namespace sf{

	SFNode::SFNode(){
		this->transform=SFPipelineTransforms::generateTrasform();
		this->lodModel=0;
		model=0;
		maxLod=MAX_LOD_EVER;
		minLod=0;
		lod=minLod;
	}

  	SFResource* SFNode::getResource() {
  		return &resource;
	}

  	bool SFNode::inLod(int lod) {
  		return maxLod>=lod && minLod<=lod;
	}

  	int SFNode::getMaxLod() {
  		return maxLod;
	}

  	void SFNode::setMaxLod(int maxLod) {
		this->maxLod = maxLod;
	}

  	SFILodSpace* SFNode::getLodSpace() {
  		return lodModel;
	}

  	int SFNode::getMinLod() {
  		return minLod;
	}

  	void SFNode::setMinLod(int minLod) {
		this->minLod = minLod;
	}

  	void SFNode::setLodModel(SFILodSpace* lodModel) {
		this->lodModel = lodModel;
	}

  	SFPipelineTransform3f* SFNode::getTransform() {
  		return transform;
	}

  	SFModel* SFNode::getModel(){
  		return model;
	}

  	void SFNode::setModel(SFModel* model) {
		this->model = model;
	}

  	int SFNode::getLod() {
  		return lod;
	}

  	void SFNode::setLod(int lod) {
		this->lod = lod;
	}

}
