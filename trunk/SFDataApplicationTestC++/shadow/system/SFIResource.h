/*
 * SFIResource.h
 *
 *  Created on: 11/set/2013
 *      Author: Alessandro
 */

#ifndef SFIRESOURCE_H_
#define SFIRESOURCE_H_

#include "SFResource.h"

namespace sf {

class SFIResource {
public:
	virtual ~SFIResource(){};
	virtual SFResource* getResource()=0;
};

} /* namespace sf */
#endif /* SFIRESOURCE_H_ */
