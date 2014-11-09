/*
 * SFRadialSurfaceFunction.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFRADIALSURFACEFUNCTION_H_
#define SFRADIALSURFACEFUNCTION_H_

#include "SFGenericSurfaceFunction.h"
#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFVertex3f.h"

namespace sf {

class SFRadialSurfaceFunction : SFGenericSurfaceFunction{

	SFCurve* borderCurve;
	SFCurve* rayCurve;
	SFVertex3f r0;
	SFVertex3f r1;
	SFVertex3f pv;
	float A,B;

public:
	virtual ~SFRadialSurfaceFunction();

	SFRadialSurfaceFunction();

	SFRadialSurfaceFunction(SFCurve* borderCurve,
			SFCurve* rayCurve);

	void setBorderCurve(SFCurve* borderCurve);

	void setRayCurve(SFCurve* rayCurve);

	void evaluateAB(float u);

	float getX(float u, float v);

	float getY(float u, float v);


	float getZ(float u, float v);

};

} /* namespace sf */
#endif /* SFRADIALSURFACEFUNCTION_H_ */
