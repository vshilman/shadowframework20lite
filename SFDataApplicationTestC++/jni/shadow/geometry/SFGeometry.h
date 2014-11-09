/*
 * SFGeometry.h
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#ifndef SFGEOMETRY_H_
#define SFGEOMETRY_H_

#include "SFGraphicsResource.h"
#include "SFPrimitive.h"

namespace sf {

class SFGeometry : public SFGraphicsResource{

	static const int LOD_HINT_DISCARD=-1;

	int rendering_hint;
	int available_lods;//more a constant then

	SFGeometry* fatherGeometry;

	vector<SFGeometry*> sonGeometries;

	bool changed;

public:
	SFGeometry();
	virtual ~SFGeometry();

	virtual SFPrimitive* getPrimitive()=0;

	virtual void drawGeometry(int lod)=0;

	int getRendering_hint();

	void setRendering_hint(int rendering_hint);

	int getAvailable_lods();

	void setAvailable_lods(int available_lods);

	int getSonsCount();

	int addSon(SFGeometry* son);

	SFGeometry* getSon(int index);

	SFGeometry* getFatherGeometry();

	void setFatherGeometry(SFGeometry* fatherGeometry);

	virtual void compile()=0;

	virtual void update()=0;

	virtual void decompile()=0;

	void rebuild();

};


} /* namespace sf */
#endif /* SFGEOMETRY_H_ */
