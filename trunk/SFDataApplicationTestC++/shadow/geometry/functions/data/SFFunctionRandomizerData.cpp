
#include "SFFunctionRandomizerData.h"


namespace sf{

SFFunctionRandomizerData::SFFunctionRandomizerData() {
	seed=new SFInt(9000);
	SFNamedParametersObject* parameters=new SFNamedParametersObject();
	parameters->addObject(seed);
	setData(parameters);
}

SFFunctionRandomizerData::~SFFunctionRandomizerData() {
	delete seed;
}

SFSurfaceFunction* SFFunctionRandomizerData::buildResource() {
	return new SFSurfaceFunctionRandomizer(seed->getIntValue());
}


void SFFunctionRandomizerData::updateResource(SFSurfaceFunction* resource) {
	SFSurfaceFunctionRandomizer* randomizer=(SFSurfaceFunctionRandomizer*)(resource);
	randomizer->setSeed(seed->getIntValue());
}

}
