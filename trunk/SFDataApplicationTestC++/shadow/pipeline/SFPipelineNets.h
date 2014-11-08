/*
 * SFPipelineNets.h
 *
 *  Created on: 05/giu/2013
 *      Author: Alessandro
 */

#ifndef SFPIPELINENETS_H_
#define SFPIPELINENETS_H_

#include "SFPrimitiveArray.h"
#include "SFGridModel.h"
#include "SFNetEdge.h"
#include "SFNetPolygon.h"


namespace sf{

class SFPipelineNets{

public:

	virtual ~SFPipelineNets();

	virtual SFNetPolygon* generateSFNetPolygon(SFPrimitiveArray* array,
			SFNetEdge** edges,int edgesLength,SFGridModel* primitiveModel)=0;

};

}

#endif /* SFPIPELINENETS_H_ */
