//
//  SFProgramComponent.cpp
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFProgramComponent.h"


namespace sf{

    SFProgramComponent::~SFProgramComponent(){
        for(unsigned int i=0;i<code.size();i++){
            delete code[i];
        }
        //TODO : temps should not be necessary, but verify
        //for(int i=0;i<temps.size();i++){
        //    delete temps[i];
        //}
        for(unsigned int i=0;i<structures.size();i++){
            delete structures[i];
        }
        for(unsigned int i=0;i<grid.size();i++){
            delete grid[i];
        }
    }

	void SFProgramComponent::addRegister(SFPipelineRegister* global){
		set.push_back(global);
		registers.push_back(global);
	}

	void SFProgramComponent::addCodeFunction(SFFunction* function){
		code.push_back(function);
	}

	void SFProgramComponent::addGridInstance(SFPipelineGrid* grid){
		this->grid.push_back(grid);
	}

	void SFProgramComponent::addStructureInstance(SFPipelineStructureInstance* structure){
		structures.push_back(structure);
	}

	void SFProgramComponent::addParameter(SFParameter* parameter){
		set.push_back(parameter);
		temps.push_back(parameter);
	}

	vector<SFPipelineStructureInstance*> SFProgramComponent::getStructures() {
		return structures;
	}

	vector<SFPipelineGrid*> SFProgramComponent::getGrid() {
		return grid;
	}


	void SFProgramComponent::loadShaderParameters(vector<SFParameter*> set){
		set.insert(set.end(),registers.begin(),registers.end());
	}

	vector<SFPipelineRegister*> SFProgramComponent::getRegisters(){
		return registers;
	}

	vector<SFFunction*> SFProgramComponent::getShaderCodeLines(){
		return code;
	}

	string* SFProgramComponent::getName() {
		return name;
	}

	void SFProgramComponent::setName(string* name) {
		this->name = name;
	}

	vector<SFParameteri*> SFProgramComponent::getParameterArray(){
		vector<SFParameteri*> cmpParameters=getParameterSet();
		return cmpParameters;
	}

	vector<SFParameteri*> SFProgramComponent::getParameterSet(){
		if(temps.size()!=0){
			set.insert(set.end(),temps.begin(),temps.end());
			temps.clear();
		}

		return set;
	}
}
