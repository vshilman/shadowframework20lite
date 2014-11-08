#ifndef shadow_renderer_SFNode_H_
#define shadow_renderer_SFNode_H_

#include "SFPipelineTransform3f.h"
#include "SFPipelineTransforms.h"
#include "SFGraphicsResource.h"
#include "SFIResource.h"
#include "SFResource.h"
#include "SFTransformKeeper.h"
#include "SFILodSpace.h"
#include "SFModel.h"


namespace sf{
class SFNode : public SFGraphicsResource,public SFTransformKeeper{

	int minLod;
	int maxLod;
  	int lod;

protected:
	static const int MAX_LOD_EVER=10000;

  	SFILodSpace* lodModel;
  	SFModel* model;

  	SFPipelineTransform3f* transform;

  	SFResource resource;

public:
  	SFNode();

  	SFResource* getResource();

  	virtual void addNode(SFNode* node)=0;

  	virtual vector<SFNode*>* getNodes()=0;

  	virtual SFNode* copyNode()=0;

  	bool inLod(int lod);

  	int getMaxLod();

  	void setMaxLod(int maxLod);

  	SFILodSpace* getLodSpace();

  	int getMinLod();

  	void setMinLod(int minLod);

  	void setLodModel(SFILodSpace* lodModel);

  	SFPipelineTransform3f* getTransform();

  	SFModel* getModel();

  	void setModel(SFModel* model);

  	int getLod() ;

  	void setLod(int lod);


};


}
#endif
