
#include "SFBuilderStructure.h"

#include "SFParsableProgramComponent.h"

namespace sf{

vector<SFParameter*> SFBuilderStructure::loadingParameters;

SFBuilderStructure::~SFBuilderStructure(){
	for(unsigned int i=0;i<loadingParameters.size();i++){
		delete loadingParameters[i];
	}
}

void SFBuilderStructure::addParameter(SFParameter* parameter){
	loadingParameters.push_back(parameter);
}

void SFBuilderStructure::finalize() {
	addParameters(loadingParameters);
	loadingParameters.clear();
	SFPipeline::loadStructure(getName(), this);
}

SFBuilderElement* SFBuilderStructure::newInstance(){
	return new SFParsableProgramComponent();
}

}
