/*
 * SFNetPolygon.h
 *
 *  Created on: 05/giu/2013
 *      Author: Alessandro
 */

#ifndef SFNETPOLYGON_H_
#define SFNETPOLYGON_H_

#include "../system/SFUpdatable.h"
#include "SFNetUpdate.h"
#include "SFNetEdge.h"
#include "SFPrimitiveArray.h"


namespace sf{

class SFNetPolygon :public SFUpdatable{

	SFNetEdge* edges;
	int edgesLength;
	SFNetPolygon* subPolygons;
	unsigned short subPolygonsLength;
	SFPrimitiveArray* array;
	SFNetUpdate* update_;
	void* updateData;

public:
	virtual ~SFNetPolygon();

	SFNetPolygon();

	SFNetPolygon(SFNetEdge* edges,int length);

	SFNetEdge* getEdges();

	int getEdgesLength();

	void setEdges(SFNetEdge* edges,int length);

	SFNetPolygon* getSubPolygons();

	int getSubPolygonsLength();

	void setSubPolygons(SFNetPolygon* subPolygons,int length);

	SFNetUpdate* getUpdate();

	void setUpdate(SFNetUpdate* update);

	void init();

	void update();

	void setUpdateData(void* updateData);

	void* getUpdateData();

	SFPrimitiveArray* getArray();

	void setArray(SFPrimitiveArray* array);
};

}

#endif /* SFNETPOLYGON_H_ */
