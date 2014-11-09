//
//  SFPipelineElement.cpp
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFPipelineElement.h"


namespace sf{

SFPipelineElement::SFPipelineElement() {
	}
    
string SFPipelineElement::getName() {
		return name;
	}
    
void SFPipelineElement::setName(string name) {
		this->name = name;
	}

}
