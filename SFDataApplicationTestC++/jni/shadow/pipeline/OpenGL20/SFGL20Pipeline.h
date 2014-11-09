//
//  SFGL20Pipeline.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20Pipeline__
#define SFGL20Pipeline__


#include "../SFPipeline.h"
#include "SFGL20ProgramBuilder.h"
#include "SFGL20PipelineGraphics.h"
#include "SFGL20PipelineMemory.h"
#include "SFGL20TexturePipeline.h"
#include "SFGL20TexturePipeline.h"

#include "SFPrimitiveBlock.h"

namespace sf{
class SFGL20Pipeline {
public:
    static void setup(){
        SFPrimitiveBlock::init();
        
        SFPipeline::setSfProgramBuilder(new SFGL20ProgramBuilder());
		SFPipeline::setSfPipelineGraphics(new SFGL20PipelineGraphics());
		SFPipeline::setSfPipelineMemory(new SFGL20PipelineMemory());
		SFPipeline::setSfTexturePipeline(new SFGL20TexturePipeline());

		//TODO
		//SFPipeline::setSfPipelineNets(SFNetEngine::getEngine());

	}
};
}



#endif /* defined(SFGL20Pipeline__) */
