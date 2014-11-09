//
//  SFCurve.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFCurve_h
#define _SFCurve_h

#include "shadow/system/SFIResource.h"
#include "shadow/math/SFValuenf.h"

namespace sf{

class SFCurve : public SFIResource{
	
public:
	virtual float getTMin()=0;
	
	virtual float getTMax()=0;
	
	virtual void getVertex(float t,SFValuenf* read)=0;
	
	virtual void getDevDt(float t,SFValuenf* read)=0;
	
	virtual void getDev2Dt(float ts,SFValuenf* read)=0;
	
	virtual SFValuenf* generateValue()=0;

	virtual void compileCurve()=0;

	virtual int getControlPointSize()=0;

	virtual SFValuenf* getControlPoint(int index)=0;

	virtual void setControlPoint(int index,SFValuenf* vertex)=0;

};

}

#endif
