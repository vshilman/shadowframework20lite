//
//  SFRegularGeometricGrid.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFRegularGeometricGrid__
#define SFRegularGeometricGrid__


#include "SFGridCircle.h"

namespace sf{

template <class T>
class SFRegularGeometricGrid{
    
public:
    int n;
	SFGridCircle<T>* circles;
    
	SFRegularGeometricGrid(int n) {
		this->n=n;
		circles=0;
	}
	
    //We wil see this...
    //template <class S>
	//virtual SFRegularGeometricGrid<S>* sameGrid()=0;
    
	SFGridCircle<T>* getCircles() {
		return circles;
	}
    
	int getN() {
		return n;
	}
	
};

}

#endif /* defined(SFRegularGeometricGrid__) */
