#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "shadow/renderer/SFProgramModuleStructures.h"

namespace sf{

class SFRenderable {

  	SFProgramModuleStructures* material;

public:
  	SFRenderable();

  	SFProgramModuleStructures* getMaterialComponent();

  	void setMaterialComponent(SFProgramModuleStructures* material);

};

}
#endif
