//
//  SFPipelineStructure.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPipelineStructure__
#define SFPipelineStructure__

#include "SFPipelineElement.h"
#include "parameters/SFParameter.h"
#include <vector>
#include <string>

using namespace std;

namespace sf{

class SFPipelineStructure : public SFPipelineElement{
    
private:
	SFParameter* parameters;
    short parametersLength;
	string name;
    
public:
	string getName();
    
    ~SFPipelineStructure(){
        for(unsigned int i=0;i<parametersLength;i++){
            delete [] parameters;
        }
    }
    
	void setName(string name) ;
	
	int size();
	
	SFParameter* getAllParameters();
	
	void setParameters(SFParameter* parameters,int parametersLength);
    
	int floatSize();
};

}

#endif /* defined(SFPipelineStructure__) */
