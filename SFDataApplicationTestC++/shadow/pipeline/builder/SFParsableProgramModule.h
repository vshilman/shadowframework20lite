//
//  SFParsableProgramModule.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFParsableProgramModule__
#define SFParsableProgramModule__

#include <vector>
#include "../SFProgramModule.h"
#include "../SFPipeline.h"
#include "SFBuilderElement.h"

using namespace std;


namespace sf{

class SFParsableProgramModule : public SFProgramModule, public SFBuilderElement{

private:
	vector<SFProgramComponent*> components;
	
public:
	SFParsableProgramModule();
	
	virtual void addComponent(SFProgramComponent* component);
	
	virtual void finalize();
    
	virtual SFBuilderElement* newInstance();
};

}


#endif /* defined(SFParsableProgramModule__) */
