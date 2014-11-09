/*
 * SFTransformedCurve.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFTRANSFORMEDCURVE_H_
#define SFTRANSFORMEDCURVE_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFTransform3f.h"

namespace sf {

class SFTransformedCurve : public SFCurve{

	SFCurve* curve;
	SFTransform3f transform;
	SFResource resource;

public:

	SFTransformedCurve();

	SFTransformedCurve(SFCurve* curve, SFTransform3f transform);

	virtual ~SFTransformedCurve();

	void compileCurve();

	SFValuenf* getControlPoint(int index);

	int getControlPointSize();

	void setControlPoint(int index, SFValuenf vertex);

	SFResource* getResource();

	void setCurve(SFCurve* curve);

	void setTransform(SFTransform3f transform);

	SFValuenf* generateValue();


	void getDev2Dt(float ts, SFValuenf* read);


	void getDevDt(float t, SFValuenf* read);

	float getTMax();

	float getTMin();

	void getVertex(float t, SFValuenf* read);

};

} /* namespace sf */
#endif /* SFTRANSFORMEDCURVE_H_ */
