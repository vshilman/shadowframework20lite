//
//  SFProgramComponent.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFProgramComponent__
#define SFProgramComponent__

#include <string>
#include <vector>

#include "SFPipelineElement.h"
#include "SFFunction.h"
#include "SFPipelineStructureInstance.h"
#include "SFPipelineGrid.h"
#include "parameters/SFPipelineRegister.h"
#include "parameters/SFParameter.h"

using namespace std;


namespace sf{

class SFProgramComponent : public SFPipelineElement {
	
private:
	string* name;
	vector<SFFunction*> code;
	vector<SFPipelineRegister*> registers;
	vector<SFParameteri*> temps;
    vector<SFPipelineStructureInstance*> structures;
	vector<SFPipelineGrid*> grid;
	vector<SFParameteri*> set;
	
public:
    
    ~SFProgramComponent();
    
	void addRegister(SFPipelineRegister* global);
	
	void addCodeFunction(SFFunction* function);
	
	void addGridInstance(SFPipelineGrid* grid);
    
	void addStructureInstance(SFPipelineStructureInstance* structure);
	
	void addParameter(SFParameter* parameter);
	
	vector<SFPipelineStructureInstance*> getStructures();
	
	vector<SFPipelineGrid*> getGrid();

	void loadShaderParameters(vector<SFParameter*> set);
    
	vector<SFPipelineRegister*> getRegisters();
    
	vector<SFFunction*> getShaderCodeLines();
    
	string* getName();

	void setName(string* name);
	
	vector<SFParameteri*> getParameterArray();
	
	vector<SFParameteri*> getParameterSet();
};

}

#endif /* defined(SFProgramComponent__) */
