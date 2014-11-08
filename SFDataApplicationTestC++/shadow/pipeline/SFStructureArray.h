//
//  SFStructureArray.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFStructureArray_h
#define _SFStructureArray_h

#include "../system/SFArray.h"
#include "../system/SFInitiable.h"
#include "SFStructureData.h"


namespace sf{
class SFStructureArray : public SFArray<SFStructureData>,public SFInitiable{
    
public:
    
    virtual ~SFStructureArray(){}
    
	/** writes a <E> with the content of the element at a given position
	 * @param index the index at which element is
	 * read
	 * @param element the element where to store data
	 * */
	virtual void getParameterValue(int index,int parametersIndex,SFValuenf* element) = 0;
	
	/** Writes the content of a given element at the given position
	 * @param index the index at which element is written
	 * @param element the element where data are read
	 */
	virtual void setParameterValue(int index,int parametersIndex,SFValuenf* element) = 0;
	
	virtual SFPipelineStructure* getPipelineStructure()=0;
};
}


#endif
