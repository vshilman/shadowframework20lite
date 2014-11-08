//
//  SFProgramModule.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFProgramModule__
#define SFProgramModule__


#include <string>
#include <vector>

#include "SFPipelineElement.h"
#include "SFProgramComponent.h"


using namespace std;


namespace sf{

class SFProgramModule : public SFPipelineElement{
    
protected:
    vector<SFProgramComponent*> components;
	
public:
    SFProgramModule();
    
    virtual ~SFProgramModule();
	
    SFProgramModule(string name);
    
    virtual void setComponents(vector<SFProgramComponent*> components);
    
    virtual vector<SFProgramComponent*> getComponents();
	
    virtual SFProgramModule* clone();
};
}


#endif /* defined(SFProgramModule__) */
