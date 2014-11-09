
//
//  SFPipelineStructureInstance.cpp
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFPipelineStructureInstance.h"


namespace sf{

SFPipelineStructureInstance::SFPipelineStructureInstance(SFPipelineStructure* structure,
		SFParameter* parameters,int parametersLength) {
    this->structure = structure;
    this->parameters=parameters;
    this->parametersLength=parametersLength;
}

SFPipelineStructure* SFPipelineStructureInstance::getStructure() {
    return structure;
}

SFParameter* SFPipelineStructureInstance::getParameters() {
    return parameters;
}

int SFPipelineStructureInstance::size(){
    return structure->size();
}

int SFPipelineStructureInstance::getParametersLength(){
    return parametersLength;
}

}
