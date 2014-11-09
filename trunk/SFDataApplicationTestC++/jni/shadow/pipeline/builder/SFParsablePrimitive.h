//
//  SFParsablePrimitive.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFParsablePrimitive__
#define SFParsablePrimitive__

#include "../SFPrimitive.h"
#include "../SFPipeline.h"
#include "SFBuilderElement.h"


namespace sf{

class SFParsablePrimitive : public SFPrimitive , public SFBuilderElement{
    
private:
    //Do not destroy this, all data will be used to construct the Primitive and will be part of the primitive
	vector<SFProgramComponent*> components;
	vector<SFPrimitiveBlock*> blocks;
	SFGridModel gridModel;
	
public:
	SFParsablePrimitive();
	
	void finalize();
	
	void addComponent(SFPrimitiveBlock* block,SFProgramComponent* component);
	
	void setGridModel(SFGridModel gridModel);

	SFBuilderElement* newInstance();
    
//    SFParsablePrimitive* newInstance(){
//        return new SFParsablePrimitive();
//    }
//
//	static vector<string> allCommands;
//
//	vector<string> getAllCommands() {
//        if(allCommands.size()==0){
//            allCommands.push_back("domain");
//            allCommands.push_back("block");
//            allCommands.push_back("begin");
//            allCommands.push_back("end");
//        }
//		return allCommands;
//	}
};

}

#endif /* defined(SFParsablePrimitive__) */
