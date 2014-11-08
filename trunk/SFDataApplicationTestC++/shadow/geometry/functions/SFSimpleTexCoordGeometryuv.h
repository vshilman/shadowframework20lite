/*
 * SFSimpleTexCoordGeometryuv.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFSIMPLETEXCOORDGEOMETRYUV_H_
#define SFSIMPLETEXCOORDGEOMETRYUV_H_

#include "SFGenericSurfaceFunction.h"

namespace sf {

class SFSimpleTexCoordGeometryuv : SFGenericSurfaceFunction{

	float du,dv;

public:
	virtual ~SFSimpleTexCoordGeometryuv();

	SFSimpleTexCoordGeometryuv(float du, float dv);

	float getX(float u, float v);

	float getY(float u, float v);

	float getZ(float u, float v);

	float getDu();

	void setDu(float du);

	float getDv();

	void setDv(float dv);
};

} /* namespace sf */
#endif /* SFSIMPLETEXCOORDGEOMETRYUV_H_ */
