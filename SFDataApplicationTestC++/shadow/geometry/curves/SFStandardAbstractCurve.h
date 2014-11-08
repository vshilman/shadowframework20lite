/*
 * SFStandardAbstractCurve.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFSTANDARDABSTRACTCURVE_H_
#define SFSTANDARDABSTRACTCURVE_H_

#include "shadow/system/SFResource.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/geometry/SFCurve.h"

namespace sf {

class SFStandardAbstractCurve : public SFCurve {

	SFResource resource;

protected:
	SFValuenf** vertices;
	short vertices_length;

public:
	SFStandardAbstractCurve(int n);

	virtual ~SFStandardAbstractCurve();

	void compileCurve();

	void setControlPoint(int index, SFValuenf* vertex);

	SFResource* getResource();

	int getControlPointSize();

	void setControlPoint(SFValuenf* value,int index);

	SFValuenf* getControlPoint(int index);

	SFValuenf* generateValue();

	float getTMin();

	float getTMax();
};

} /* namespace sf */
#endif /* SFSTANDARDABSTRACTCURVE_H_ */
