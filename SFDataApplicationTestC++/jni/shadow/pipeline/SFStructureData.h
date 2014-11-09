//
//  SFStructureData.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFStructureData__
#define SFStructureData__

#include "SFPipelineStructure.h"
#include "../math/SFValuenf.h"


namespace sf{
class SFStructureData {
    
private:
	SFPipelineStructure* structure;
	SFValue** values;
    int valuesSize;
    
public:
	SFStructureData(SFPipelineStructure* structure);
    
    ~SFStructureData(){
        for(int i=0;i<valuesSize;i++){
            delete values[i];
        }
        delete values;
    }
    
	int size();
    
	SFPipelineStructure* getStructure();
    
	SFValue* getValue(int index);
    
	SFValue** getValues();
};
}

#endif /* defined(SFStructureData__) */
