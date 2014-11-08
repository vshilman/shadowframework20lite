/*
 * SFSurfaceFunctionRandomizer.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFSURFACEFUNCTIONRANDOMIZER_H_
#define SFSURFACEFUNCTIONRANDOMIZER_H_

#include "SFGenericSurfaceFunction.h"
#include "shadow/math/SFRandomizer.h"

namespace sf {

class SFSurfaceFunctionRandomizer : public SFGenericSurfaceFunction {

	SFRandomizer randomizer;

public:
	virtual ~SFSurfaceFunctionRandomizer();

	SFSurfaceFunctionRandomizer(int seed);

	float getValue(float u, float v);

	void setSeed(int seed);

	float getX(float u, float v);

	float getY(float u, float v);

	float getZ(float u, float v);
};

} /* namespace sf */
#endif /* SFSURFACEFUNCTIONRANDOMIZER_H_ */
