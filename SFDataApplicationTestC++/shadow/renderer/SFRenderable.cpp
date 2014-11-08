
#include "shadow/renderer/SFRenderable.h"

namespace sf{

	SFRenderable::SFRenderable() {
		material=0;
	}

  	SFProgramModuleStructures* SFRenderable::getMaterialComponent() {
  		return material;
	}

  	void SFRenderable::setMaterialComponent(SFProgramModuleStructures* material) {
		this->material = material;
	}

}
