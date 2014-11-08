//
//  SFGL20GenericProgram.h
//  
//
//  Created by Alessandro Martinelli on 20/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFGL20GenericProgram_h
#define _SFGL20GenericProgram_h

#include "../SFProgram.h"
#include "../SFPipelineGraphics.h"
#include "../../image/SFPipelineTexture.h"
#include "../SFStructureData.h"
#include "../../system/SFInitiable.h"

namespace sf{
class SFGL20GenericProgram : public SFProgram{
    
public:
	virtual void setData(SFPipelineGraphics::Module module,int index, SFStructureData* data)=0;
    
	virtual void setTextureData(SFPipelineGraphics::Module module,int index, SFPipelineTexture* texture)=0;
    
//    virtual SFPrimitive* getPrimitive();
//
//
//    virtual SFProgramModule* getTransforms();
//
//    virtual SFProgramModule* getMaterials();
//
//    virtual SFProgramModule* getLight();
//
//    virtual GLuint getProgram();
};
}

#endif
