/*
 * SFCurvedTubeFunction.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFCURVEDTUBEFUNCTION_H_
#define SFCURVEDTUBEFUNCTION_H_

#include "SFGenericSurfaceFunction.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/geometry/SFCurve.h"

#include "math.h"

namespace sf {

class SFCurvedTubeFunction : public SFGenericSurfaceFunction{

	SFCurve* centralCurve;
	SFCurve* rayCurve;

	SFVertex3f Ccv;
	SFVertex3f Vec1v;
	SFVertex3f Vec2v;
	SFVertex3f DVec1v;
	SFVertex3f DVec2v;
	SFVertex3f dCcdv;

	float lastV;
	float cos_;
	float sin_;

public:
	virtual ~SFCurvedTubeFunction();

	SFCurvedTubeFunction();

	SFCurvedTubeFunction(SFCurve* centralCurve,
			SFCurve* rayCurve);

	SFVertex3f getDu();

	SFVertex3f getDv();

	SFVertex3f getPosition();


	void evalAll(float v);

	float getX(float u,float v);

	float getY(float u,float v);

	float getZ(float u,float v);

	float getX();

	float getY();

	float getZ();

	float getDXDv();

	float getDYDv();

	float getDZDv();

	float getDXDu();

	float getDYDu();

	float getDZDu();

	SFCurve* getCentralCurve();

	void setCentralCurve(SFCurve* centralCurve);

	SFCurve* getRayCurve();

	void setRayCurve(SFCurve* rayCurve);



};

} /* namespace sf */
#endif /* SFCURVEDTUBEFUNCTION_H_ */
