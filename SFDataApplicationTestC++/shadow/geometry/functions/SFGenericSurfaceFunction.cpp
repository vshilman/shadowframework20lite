/*
 * SFGenericSurfaceFunction.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/functions/SFGenericSurfaceFunction.h"

namespace sf {

SFGenericSurfaceFunction::~SFGenericSurfaceFunction(){

}

SFResource* SFGenericSurfaceFunction::getResource() {
	return &resource;
}

} /* namespace sf */
