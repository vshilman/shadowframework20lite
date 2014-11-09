//
//  SFSurfaceFunction.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFSurfaceFunction_h
#define _SFSurfaceFunction_h


#include "SFIResource.h"

namespace sf{

class SFSurfaceFunction : public SFIResource{
	
public:
//	enum SFSurfaceInfo{
//		POSITION,
//		NORMAL,
//		DU,
//		DV
//	};
	
	//virtual void updateRectangularModel(SFRectangularGrid<SFValuenf[]> values,float[] us,float vs[],SFSurfaceInfo[] infos)=0;
    
	//virtual void updateParametrizedModel(int position,SFArray<SFValuenf> parameters,SFIndexRange range,SFPrimitiveArray array,SFPrimitiveBlock block,int gridIndex)=0;
	
	//virtual int extractParametrizedModel(SFArray<SFValuenf> parameters,SFIndexRange range,SFPrimitiveArray array,SFPrimitiveBlock block,int gridIndex)=0;

	virtual float getX(float u,float v)=0;

	virtual float getY(float u,float v)=0;

	virtual float getZ(float u,float v)=0;
};

}

#endif
