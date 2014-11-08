//
//  SFPipelineTransform3f.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPipelineTransform3f_H
#define SFPipelineTransform3f_H

#include "SFRigidTransform3farray.h"


namespace sf{

class SFPipelineTransform3f {
    
private:
    SFRigidTransform3fArray* array;
	int index;
	
public:
    
	SFPipelineTransform3f(SFRigidTransform3fArray* array, int index);
	
    //should not delete array, since pipeline transforms are shared
    
	void attachOn(SFPipelineTransform3f transform);
	
	void setPosition(SFVertex3f* vertex);
	
	void setOrientation(SFMatrix3f* matrix);
    
	void getPosition(SFVertex3f* vertex);
	
	void getOrientation(SFMatrix3f* matrix);
	
	void apply();
};

}

#endif /* defined(SFPipelineTransform3f__) */
