//
//  SFPipelineStructureInstance.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPipelineStructureInstance__
#define SFPipelineStructureInstance__

#include "SFPipelineStructure.h"
#include "parameters/SFParameter.h"
#include <vector>

using namespace std;


namespace sf{

class SFPipelineStructureInstance {
    
private:
	SFPipelineStructure* structure;
	SFParameter* parameters;
	int parametersLength;
	
public:
	SFPipelineStructureInstance(SFPipelineStructure* structure,
			SFParameter* parameters,int parametersLength);
	
    ~SFPipelineStructureInstance(){
    	delete parameters;
    	//for(int i=0;i<parametersLength;i++){
    	//    [i];
    	//}
    }
    
	SFPipelineStructure* getStructure();
    
	SFParameter* getParameters();
	
	int getParametersLength();

	int size();
};

}

#endif /* defined(SFPipelineStructureInstance__) */
