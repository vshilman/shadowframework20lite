/*
 * SFBasisSpline.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFBASISSPLINE_H_
#define SFBASISSPLINE_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/SFResource.h"

namespace sf {

class SFBasisSpline : public SFCurve{

	SFResource* resource;

protected:

	SFValuenf* vertices;
	short vertices_length;
	bool closed;

public:

	SFBasisSpline(bool closed,int size);

	virtual ~SFBasisSpline();

	void setSize(int size);

	SFResource* getResource ( );

	void setControlPoint(int index, SFValuenf vertex);

	int getControlPointSize();

	SFValuenf* getControlPoint(int index);

	SFValuenf* getVertices ( );

	float getTMax();

	float getTMin();

	SFValuenf* generateValue();
};

} /* namespace sf */
#endif /* SFBASISSPLINE_H_ */
