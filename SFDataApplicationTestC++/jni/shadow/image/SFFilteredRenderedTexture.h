#ifndef shadow_image_H_
#define shadow_image_H_

#include "SFPipeline.h"
#include "SFPipelineGraphics.h"
#include "SFProgram.h"
#include "SFProgramModuleStructures.h"
#include "SFRenderer.h"
#include "SFRenderedTexturesSet.h"
#include "SFResource.h"

namespace sf{
class SFFilteredRenderedTexture : public SFRenderedTexturesSet{

	bool initialized;
  	SFPipelineTexture** textures;
  	short texturesLength;
  	SFRenderedTexture* renderedTexture;

  	SFProgramModuleStructures* steps;
  	SFProgramModuleStructures* materials;
  	short stespSize;
  	SFProgram** programs;
  	SFResource resource;

public:
	
  	SFPipelineTexture* getTexture(int index);

  	SFResource* getResource();
	
  	int getTextureSize();
	
  	void init();
	
  	void update();
	
  	void destroy();

  	void setTextures(SFPipelineTexture** textures,short texturesLength);

  	SFProgramModuleStructures* getLightComponent();

  	void setLights(SFProgramModuleStructures* lightComponent);

  	void setLights(SFProgramModuleStructures lightComponent);

  	SFProgramModuleStructures* getMaterial();

  	void setMaterials(SFProgramModuleStructures* materials);

};

}
#endif
