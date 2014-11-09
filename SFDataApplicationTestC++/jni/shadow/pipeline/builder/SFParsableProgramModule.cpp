
#include "SFParsableProgramModule.h"


namespace sf{

SFParsableProgramModule::SFParsableProgramModule() {

}

void SFParsableProgramModule::addComponent(SFProgramComponent* component){
	components.push_back(component);
}

void SFParsableProgramModule::finalize() {

	SFProgramModule* module=clone();

	//SFProgramComponent modules=components.toArray(new SFProgramComponent[components.size()]);
	module->setComponents(components);

	SFPipeline::loadShaderModule(getName(), module);
}


SFBuilderElement* SFParsableProgramModule::newInstance(){
	return new SFParsableProgramModule();
}

}
