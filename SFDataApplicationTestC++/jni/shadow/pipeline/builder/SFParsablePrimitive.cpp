
#include "SFParsablePrimitive.h"



namespace sf{

SFParsablePrimitive::SFParsablePrimitive() {
	this->gridModel=0;
}

void SFParsablePrimitive::finalize() {
	SFPrimitive* primitive=new SFPrimitive("",&gridModel);

	//SFProgramComponent[] modules=components.toArray(new SFProgramComponent[components.size()]);
	//SFPrimitiveBlock[] registers=blocks.toArray(new SFPrimitiveBlock[blocks.size()]);
	primitive->setPrimitiveElements(blocks, components);

	primitive->setName(getName());
	SFPipeline::loadPrimitive(getName(), primitive);
}

void SFParsablePrimitive::addComponent(SFPrimitiveBlock* block,SFProgramComponent* component){
	components.push_back(component);
	blocks.push_back(block);
}

void SFParsablePrimitive::setGridModel(SFGridModel gridModel) {
	this->gridModel = gridModel;
}

SFBuilderElement* SFParsablePrimitive::newInstance() {
	return new SFParsablePrimitive();
}

}
