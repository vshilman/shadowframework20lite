/*
 * SFRectangle2DFunction.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFRECTANGLE2DFUNCTION_H_
#define SFRECTANGLE2DFUNCTION_H_

#include "SFGenericSurfaceFunction.h"

namespace sf {

class SFRectangle2DFunction : public SFGenericSurfaceFunction {

	float x,y,w,h;

public:
	virtual ~SFRectangle2DFunction();

	SFRectangle2DFunction(float x, float y, float w, float h);

	float getX(float u, float v);

	float getY(float u, float v);

	float getZ(float u, float v);

	float getX();

	void setX(float x);

	float getY();

	void setY(float y);

	float getW();

	void setW(float w);

	float getH();

	void setH(float h);
};

} /* namespace sf */
#endif /* SFRECTANGLE2DFUNCTION_H_ */
