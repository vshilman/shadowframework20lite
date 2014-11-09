//
//  SFPrimitiveIndices.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPrimitiveIndices__
#define SFPrimitiveIndices__

#include "SFPrimitive.h"


namespace sf{

class SFPrimitiveIndices {
    
private:
	int* primitiveIndices;
    int size;
	
public:
	SFPrimitiveIndices();
    
    ~SFPrimitiveIndices(){
        delete primitiveIndices;
    }
	
	SFPrimitiveIndices(SFPrimitive* primitive);
	
	void set(SFPrimitiveIndices* indices);
    
    int length();
    
	int* getPrimitiveIndices();
    
	void setPrimitiveIndices(int* primitiveIndices);
    
	void setData(SFPrimitiveIndices indices,int firstIndex,int size);
	
	//SFPrimitiveIndices* clone();
};

}

#endif /* defined(SFPrimitiveIndices__) */
