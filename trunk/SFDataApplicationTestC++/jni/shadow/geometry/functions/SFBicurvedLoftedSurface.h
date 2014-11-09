/*
 * SFBicurvedLoftedSurface.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFBICURVEDLOFTEDSURFACE_H_
#define SFBICURVEDLOFTEDSURFACE_H_

#include "SFGenericSurfaceFunction.h"
#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFVertex3f.h"

namespace sf {

class SFBicurvedLoftedSurface : public SFGenericSurfaceFunction{

	SFCurve* A;
	SFCurve* B;

	float maxTA;
	float maxTB;

	SFVertex3f tmp;
	SFVertex3f tmp2;

public:
	SFBicurvedLoftedSurface();

	virtual ~SFBicurvedLoftedSurface();


	SFBicurvedLoftedSurface(SFCurve* a, SFCurve* b);

	float getX(float u, float v);

	float getY(float u, float v);

	float getZ(float u, float v) ;

	SFCurve* getA();

	void setA(SFCurve* a);

	SFCurve* getB();

	void setB(SFCurve* b);

	float getMaxTA();

	void setMaxTA(float maxTA);

	float getMaxTB();

	void setMaxTB(float maxTB);


};

} /* namespace sf */
#endif /* SFBICURVEDLOFTEDSURFACE_H_ */
