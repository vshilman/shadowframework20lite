//
//  SFQuadGrid.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFQuadGrid_h
#define _SFQuadGrid_h

namespace sf{

template <class T>
class SFQuadGrid {
    
public:

	virtual ~SFQuadGrid(){};

	virtual T getValue(int i, int j)=0;
    
	virtual void setValue(int i, int j, T value)=0;
    
	virtual int getWidth()=0;
	
	virtual int getHeight()=0;
};

}

#endif
