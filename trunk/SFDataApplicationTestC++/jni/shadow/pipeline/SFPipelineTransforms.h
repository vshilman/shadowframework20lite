//
//  SFPipelineTransforms.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPipelineTransforms__
#define SFPipelineTransforms__

#include "SFPipelineTransform3f.h"
#include "SFRigidTransform3fArray.h"
#include "SFPipeline.h"


namespace sf{

class SFPipelineTransforms {
    
private:
	static SFPipelineTransforms pipelineTransforms;
	
	SFRigidTransform3fArray* rigidTransforms;
	
    SFPipelineTransforms(){
		rigidTransforms=(SFRigidTransform3fArray*)0;
	}
    
    ~SFPipelineTransforms(){
        if(rigidTransforms!=0){
            SFPipeline::getSfPipelineMemory()->destroyRigidTransformsArray(rigidTransforms);
        }
    }
    
public:
    
	static SFPipelineTransform3f* generateTrasform();
};

}

#endif /* defined(SFPipelineTransforms__) */
