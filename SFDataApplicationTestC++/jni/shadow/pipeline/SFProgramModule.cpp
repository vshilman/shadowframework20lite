//
//  SFProgramModule.cpp
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFProgramModule.h"



namespace sf{
	SFProgramModule::SFProgramModule() {
	}

	SFProgramModule::~SFProgramModule(){
        for(unsigned int i=0;i<components.size();i++){
            delete components[i];
        }
    }

	SFProgramModule::SFProgramModule(string name) {
		setName(name);
	}

	void SFProgramModule::setComponents(vector<SFProgramComponent*> components) {
		this->components = components;
	}

	vector<SFProgramComponent*> SFProgramModule::getComponents() {
		return components;
	}

	SFProgramModule* SFProgramModule::clone() {
		SFProgramModule* module=new SFProgramModule(getName());
		module->components=components;
		return module;
	}
}
