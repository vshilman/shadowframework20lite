

#include "SFParsableProgramComponent.h"


namespace sf{

void SFParsableProgramComponent::finalize() {
	SFPipeline::loadShaderComponent(*getName(), this);
}

}
