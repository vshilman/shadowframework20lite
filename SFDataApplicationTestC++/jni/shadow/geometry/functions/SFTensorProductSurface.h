/*
 * SFTensorProductSurface.h
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#ifndef SFTENSORPRODUCTSURFACE_H_
#define SFTENSORPRODUCTSURFACE_H_

#include "SFGenericSurfaceFunction.h"

#include "shadow/geometry/SFCurve.h"

namespace sf {

class SFTensorProductSurface : SFGenericSurfaceFunction{


	SFCurve** guideLines;
	SFCurve* productCurve;

	SFValuenf lastVertex;

	void evaluateLastVertex(float u,float v);

public:
	virtual ~SFTensorProductSurface();

	SFTensorProductSurface();

	void setGuideLines(SFCurve** guideLines);

	void setProductCurve(SFCurve* productCurve);

	float getX(float u, float v);

	float getY(float u, float v);

	float getZ(float u, float v);
};

} /* namespace sf */
#endif /* SFTENSORPRODUCTSURFACE_H_ */
