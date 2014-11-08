//
//  SFPrimitiveArray.h
//  
//
//  Created by Alessandro Martinelli on 17/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPrimitiveArray_H
#define SFPrimitiveArray_H

#include "SFPrimitiveIndices.h"
#include "../system/SFInitiable.h"
#include "../system/SFValuesArray.h"


namespace sf{

class SFPrimitiveArray: public SFInitiable,public SFArray<SFPrimitiveIndices>{

 public:
    
    virtual ~SFPrimitiveArray(){};
    
    virtual SFPrimitive* getPrimitive()=0;

	virtual SFValuesArray* getPrimitiveData(int gridIndex)=0;

};

}


#endif
