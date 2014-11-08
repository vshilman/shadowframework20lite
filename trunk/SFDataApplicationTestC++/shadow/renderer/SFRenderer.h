#ifndef shadow_renderer_SFRenderer_H_
#define shadow_renderer_SFRenderer_H_

#include "SFGeometry.h"
#include "SFTexture.h"
#include "SFVertex3f.h"
#include "SFPipeline.h"
#include "SFPipelineGraphics.h"
#include "SFProgram.h"
#include "SFGraphicsResource.h"
#include "SFResource.h"
#include "SFCamera.h"
#include "SFNode.h"
#include "SFPipelineRenderingState.h"

namespace sf{

class SFRenderer : public SFGraphicsResource{

	SFProgramModuleStructures* light;
	int lightsLength;
	int* baseLod;//addictional Lod
	short baseLodSize;
	SFPipelineRenderingState* states;//addictional Lod
	int statesLength;
	int stepIndex;
	SFCamera* camera;
  	//lod management
	float* distances;
	int distancesLength;

	SFResource resource;

public:
  	SFRenderer();

  	SFResource* getResource();

  	void setCamera(SFCamera* camera);

  	SFCamera* getCamera();

  	void setLight(SFProgramModuleStructures* light);

  	SFPipelineRenderingState* getStates();

  	void setStates(SFPipelineRenderingState* states);

  	void setBaseLod(int baseLod);
	
  	void init();

	
  	void destroy();

  	/**
  	 * Start point of the rendering algorithm
  	 *
  	 * @param scene
  	 */
  	void render(SFNode* node);

  	void render(SFNode* node,int lod);

  	void renderNodeContent(SFNode* node,int lod);


  	void setupRenderingData(SFModel* model);

	static void setupMaterialData(SFPipelineGraphics::Module module,SFProgramModuleStructures model);

  	void renderGeometry(SFGeometry* geometry,int lod);

  	int lodQuery(SFILodSpace* space);
};

}
#endif
