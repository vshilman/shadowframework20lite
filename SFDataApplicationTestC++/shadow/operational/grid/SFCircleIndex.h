//
//  SFCircleIndex.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFCircleIndex_h
#define _SFCircleIndex_h

class SFCircleIndex {
public:
    int circleIndex;
	int edgeIndex;
	int inEdgeIndex;

	SFCircleIndex(){
		circleIndex=0;
		edgeIndex=0;
		inEdgeIndex=0;
	}
};

#endif
