/*
 * SFSpline.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFSPLINE_H_
#define SFSPLINE_H_

#include "shadow/geometry/SFCurve.h"

namespace sf {

class SFSpline :SFCurve{

	SFCurve* curve;
	int curveSize;
	int curveCount;

	SFValuenf** controlPoints;
	short controlPoints_length;
	SFResource* resource;

public:

	SFSpline(int size);

	virtual ~SFSpline();

	void setSize(int size);


	void setCurve(SFCurve* curve);

	void compileCurve();

	SFResource* getResource();


	void setControlPoint(int index, SFValuenf* vertex);

	int getControlPointSize();


	SFValuenf* getControlPoint(int index);

	void getDev2Dt(float T, SFValuenf* read);

	void getDevDt(float T, SFValuenf* read);


	void getVertex(float T, SFValuenf* read);

	float selectCurve(float T);

	SFValuenf* generateValue();

	float getTMax();

	float getTMin();

};

} /* namespace sf */
#endif /* SFSPLINE_H_ */
