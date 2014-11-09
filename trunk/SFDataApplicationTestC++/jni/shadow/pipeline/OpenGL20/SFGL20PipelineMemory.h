//
//  SFGL20PipelineMemory.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20PipelineMemory__
#define SFGL20PipelineMemory__


#include "../SFPipelineMemory.h"
#include "SFGL20PrimitiveArray.h"
#include "SFGL20StructureArray.h"
#include "SFGL20RigidTransforms3fArray.h"
#include "SFGL20ValuenfArray.h"


namespace sf{
class SFGL20PipelineMemory : public SFPipelineMemory{
    
public:
	SFPrimitiveArray* generatePrimitiveArray(SFPrimitive* primitive) {
		return new SFGL20PrimitiveArray(primitive);
	}
	

	SFValuesArray* generateValues(int n) {
		return (SFValuesArray*)(new SFGL20ValuenfArray(n));
	}

	
	SFStructureArray* generateStructureData(SFPipelineStructure* structure) {
		return new SFGL20StructureArray(structure);
	}
	
	
	SFGL20RigidTransforms3fArray* generateRigidTransformsArray() {
		return new SFGL20RigidTransforms3fArray();
	}
	
	
	SFPrimitiveArray* generatePrimitiveArrayView(SFPrimitiveArray* array, SFPrimitive* primitive) {
		return ((SFGL20PrimitiveArray*)array)->getView(primitive);
	}
	
	
	SFPrimitiveArray* getMixArray(SFPrimitiveArray** arrays,int arraysSize,SFPrimitive* mixPrimitive) {
		return SFGL20PrimitiveArray::mixArrays(arrays,arraysSize,mixPrimitive);
	}
	
	
	void destroyPrimitiveArray(SFPrimitiveArray* array) {
        delete array;
	}
	
	
	void destroyRigidTransformsArray(SFRigidTransform3fArray* array) {
        delete array;
	}
	
	
	void destroyStructureData(SFStructureArray* array) {
        delete array;
	}
};
}

#endif /* defined(SFGL20PipelineMemory__) */
