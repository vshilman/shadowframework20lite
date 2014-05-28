#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "shadow/pipeline/SFPipelineTransform3f.h"
#include "shadow/pipeline/SFPipelineTransforms.h"
#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/SFIResource.h"
#include "shadow/system/SFResource.h"

///**
// * Generic Interface for a Scene-graph Node
// * 
// * @author Alessandro Martinelli
// */
//abstract class SFNode implements Iterable<SFNode>,SFGraphicsResource,SFTransformKeeper,SFIResource{

	AX_LOD_EVER=10000000;

	AX_LOD_EVER;
//	int minLod=0;

//	int lod;

//	SFILodSpace lodModel=null;
//	SFModel model=null;
//	protected SFPipelineTransform3f transform;

//	SFResource resource=new SFResource(10);

//	SFNode(){
		this->transform=SFPipelineTransforms.generateTrasform();
	}

//	SFResource getResource() {
//		return resource;
	}

//	/**
//	 * Add a son node to this node
//	 * @param node the node to be added
//	 * @throws SFNodeException when node cannot be added to this node
//	 */
//	abstract void addNode(SFNode node);

//	/**
//	 * Provide a copy of this Node. Copied element should use the same
//	 * renderable Model, but must have a separate transform.
//	 * Node copying should allow the transfer of 
//	 * @return
//	 */
//	abstract SFNode copyNode();

//	boolean inLod(int lod) {
//		return maxLod>=lod && minLod<=lod;
	}

//	int getMaxLod() {
//		return maxLod;
	}

//	void setMaxLod(int maxLod) {
		this->maxLod = maxLod;
	}

//	SFILodSpace getLodSpace() {
//		return lodModel;
	}

//	int getMinLod() {
//		return minLod;
	}

//	void setMinLod(int minLod) {
		this->minLod = minLod;
	}

//	void setLodModel(SFILodSpace lodModel) {
		this->lodModel = lodModel;
	}

//	/**
//	 * A Transform which any renderer should apply to 
//	 * this node and to all its sons node. 
//	 * @return
//	 */
//	SFPipelineTransform3f getTransform() {
//		return transform;
	}

//	/**
//	 * Provide the Model element for this Node if this node can be rendered.
//	 * If getModel()==null this node can't be drawn
//	 * 
//	 * @return a Model which can be rendered
//	 */
//	SFModel getModel(){
//		return model;
	}

//	void setModel(SFModel model) {
		this->model = model;
	}

//	int getLod() {
//		return lod;
	}

//	void setLod(int lod) {
		this->lod = lod;
	}


}
;
}
#endif
