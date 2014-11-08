/*
 * SFGenericSurfaceFunction.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFGENERICSURFACEFUNCTION_H_
#define SFGENERICSURFACEFUNCTION_H_

#include "shadow/system/SFIResource.h"
#include "shadow/geometry/SFSurfaceFunction.h"

namespace sf {

class SFGenericSurfaceFunction : public  SFSurfaceFunction {

	SFResource resource;

public:
	virtual ~SFGenericSurfaceFunction();

	SFResource* getResource();

};

} /* namespace sf */
#endif /* SFGENERICSURFACEFUNCTION_H_ */
