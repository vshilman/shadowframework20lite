//
//  SFProgramDataModel.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFProgramDataModel_h
#define _SFProgramDataModel_h

#include "../SFPipelineGraphics.h"
#include "../../math/SFValuenf.h"
#include "../SFStructureData.h"
#include "../../image/SFPipelineTexture.h"
#include "SFGL20ValuenfArray.h"

namespace sf{
class SFProgramDataModel {
    
public:
	void setTransformData(float* transform);
    
	void setIndexedData(SFPrimitiveIndices indices,
                               SFGL20ValuenfArray** datas,
                               SFPrimitive primitive);
    
	void sendVertex(SFValuenf value);
    
	void setData(SFPipelineGraphics::Module module,int index, SFStructureData data);
    
	void setTextureData(SFPipelineGraphics::Module module,int index, SFPipelineTexture* texture);
    
};
}

#endif
