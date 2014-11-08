//
//  SFPipelineMemory.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFPipelineMemory_h
#define _SFPipelineMemory_h

#include "SFPrimitive.h"
#include "SFPipelineStructure.h"
#include "SFPrimitiveArray.h"
#include "SFRigidTransform3fArray.h"
#include "SFStructureArray.h"


namespace sf{

class SFPipelineMemory {
    
public:
    virtual ~SFPipelineMemory(){
    }
    
	virtual SFRigidTransform3fArray* generateRigidTransformsArray()=0;

	virtual SFValuesArray* generateValues(int n)=0;
    
	virtual SFStructureArray* generateStructureData(SFPipelineStructure* structure)=0;
    
	virtual SFPrimitiveArray* generatePrimitiveArray(SFPrimitive* primitive)=0;
    
	virtual SFPrimitiveArray* generatePrimitiveArrayView(SFPrimitiveArray* array,SFPrimitive* primitive)=0;
    
    virtual SFPrimitiveArray* getMixArray(SFPrimitiveArray** arrays,int arraysSize,SFPrimitive* mixPrimitive)=0;
    
	virtual void destroyRigidTransformsArray(SFRigidTransform3fArray* array)=0;
    
	virtual void destroyStructureData(SFStructureArray* array)=0;
    
	virtual void destroyPrimitiveArray(SFPrimitiveArray* array)=0;
    
};

}

#endif
